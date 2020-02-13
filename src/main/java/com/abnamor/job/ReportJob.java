package com.abnamor.job;

import java.io.File;
import java.io.InputStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.abnamor.model.FutureTransaction;
import com.abnamor.model.Report;
import com.abnamor.task.aggregator.FutureTransactionAggregator;
import com.abnamor.task.mapper.Mapper;
import com.abnamor.task.processor.TransactionReportProcessor;
import com.abnamor.task.reader.Reader;
import com.abnamor.task.writer.CsvWriter;

@Service
public class ReportJob implements Job<InputStream, File> {

	@Autowired
	private Reader<InputStream, Stream<String>> fileReader;

	@Autowired
	private Mapper<String, FutureTransaction> lineMapper;

	@Autowired
	private FutureTransactionAggregator aggregator;

	@Autowired
	private TransactionReportProcessor reportProcessor;

	@Autowired
	private CsvWriter reportWriter;
	
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

			}
		});

		Report report = reportProcessor.process(aggregator.get());

		File outputFile = new File( outputFileName);

		this.reportWriter.write(report, outputFile);	
		
		LOGGER.info("finish  run(), outputFileName=" + outputFileName);

	}
	
	 

}
