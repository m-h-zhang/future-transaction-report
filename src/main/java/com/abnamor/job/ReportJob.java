package com.abnamor.job;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.abnamor.model.FutureTransaction;
import com.abnamor.model.FutureTransactionKey;
import com.abnamor.model.Report;
import com.abnamor.task.aggregator.Aggregator;
import com.abnamor.task.mapper.Mapper;
import com.abnamor.task.processor.Processor;
import com.abnamor.task.reader.Reader;
import com.abnamor.task.writer.Writer;

@Service
public class ReportJob implements Job<InputStream, File> {

	@Autowired
	private Reader<InputStream, Stream<String>> fileReader;

	@Autowired
	private Mapper<String, FutureTransaction> lineMapper;

	@Autowired
	private Aggregator<FutureTransaction, Map<FutureTransactionKey, Long>> aggregator;

	@Autowired
	private Processor<Map<FutureTransactionKey, Long>, Report> reportProcessor;

	@Autowired
	private Writer<Report, File> reportWriter;
	
	@Value("${input.file.name}")
	private String inputFileName;
	
	@Value("${output.file.name}")
	private String outputFileName;

	private final static Logger LOGGER = LoggerFactory.getLogger(ReportJob.class);

	@Override
	public void  run() throws Exception {
		 
		LOGGER.info("start run(), inputFileName=" + inputFileName);
		Stream<String> stream = fileReader.read(ReportJob.class.getClassLoader().getResourceAsStream(inputFileName));
		stream.forEach(line -> {
			try {
				FutureTransaction futureTransaction = lineMapper.map(line);
				aggregator.aggregate(futureTransaction);
			} catch (Exception e) {
				LOGGER.error("failed to aggregate the line: " + line, e);
				throw new RuntimeException(e);
			}
		});

		Report report = reportProcessor.process(aggregator.get());

		File outputFile = new File( outputFileName);

		this.reportWriter.write(report, outputFile);	
		
		LOGGER.info("finish  run(), outputFileName=" + outputFileName);

	}
	
	 

}
