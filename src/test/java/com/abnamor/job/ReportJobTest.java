package com.abnamor.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.abnamor.task.aggregator.FutureTransactionAggregator;
import com.abnamor.task.mapper.InputColumns;
import com.abnamor.task.mapper.TextLineMapper;
import com.abnamor.task.processor.TransactionReportProcessor;
import com.abnamor.task.reader.TextFileReader;
import com.abnamor.task.writer.CsvWriter;

class ReportJobTest {
	
	ReportJob reportJob;
	File outputFile; 

	@BeforeEach
	void setUp() throws Exception {
		reportJob = new ReportJob();		
		String outputFileName = "output.csv";
		outputFile = new File(outputFileName);
		if (outputFile.exists()) {
			outputFile.delete();
		}	
		ReflectionTestUtils.setField(reportJob, "inputFileName", "input.txt" );
		ReflectionTestUtils.setField(reportJob, "outputFileName",  outputFileName);
		ReflectionTestUtils.setField(reportJob, "fileReader", new TextFileReader() );		
		TextLineMapper textLineMapper = new TextLineMapper();
		InputColumns inputColumns =  new InputColumns("input/input_columns.txt", ",");				
		ReflectionTestUtils.setField(textLineMapper, "inputColumns", inputColumns );
		ReflectionTestUtils.setField(reportJob, "lineMapper",  textLineMapper);
		
		ReflectionTestUtils.setField(reportJob, "aggregator", new FutureTransactionAggregator() );
		
		TransactionReportProcessor transactionReportProcessor = new TransactionReportProcessor();		 
		Object value =  Arrays.asList(new String[] {"c1", "c2", "c3"});
		ReflectionTestUtils.setField(transactionReportProcessor, "headers", value );		
		ReflectionTestUtils.setField(reportJob, "reportProcessor", transactionReportProcessor );
		CsvWriter csWriter = new CsvWriter();		
		ReflectionTestUtils.setField(csWriter, "columnBreak", "," ); 
		ReflectionTestUtils.setField(csWriter, "lineBreak", "\n" );		
		ReflectionTestUtils.setField(reportJob, "reportWriter", csWriter ); 

	}

	@AfterEach
	void tearDown() throws Exception {
		reportJob = null;
		if (outputFile.exists()) {
			outputFile.delete();
		}
	}

	@Test
	void testFail() throws Exception {
		ReflectionTestUtils.setField(reportJob, "reportWriter", null ); 
		assertThrows(NullPointerException.class, () -> reportJob.run());
	}
	
	@Test
	void testRun() throws Exception {
		 reportJob.run();
		 assertTrue(outputFile.exists());
		 assertTrue( outputFile.length() > 0);
		 
		 
		 
		 try( BufferedReader bufferedReader = new BufferedReader( new FileReader(outputFile))){	   
			 assertEquals("c1,c2,c3,", bufferedReader.readLine() );
			 assertEquals("CL-4321-0002-0001,SGX-FU-NK-20100910,12,", bufferedReader.readLine() );
		 }
		  
		  
	}

}
