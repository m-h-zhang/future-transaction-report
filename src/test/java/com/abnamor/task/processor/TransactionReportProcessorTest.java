package com.abnamor.task.processor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.abnamor.model.FutureTransactionKey;
import com.abnamor.model.Report;

class TransactionReportProcessorTest {
	
	private TransactionReportProcessor transactionReportProcessor;
	

	@BeforeEach
	void setUp() throws Exception {
		transactionReportProcessor = new TransactionReportProcessor();		 
		Object value =  Arrays.asList(new String[] {"c1", "c2", "c3"});
		ReflectionTestUtils.setField(transactionReportProcessor, "headers", value );

		 
	}

	@AfterEach
	void tearDown() throws Exception {
		transactionReportProcessor = null;
	}

	@Test
	void testProcess() throws Exception {
		Map<FutureTransactionKey, Long> reportDataMap = new HashMap<FutureTransactionKey, Long>();		
		FutureTransactionKey futureTransactionKey = new FutureTransactionKey("CL", "4321", "0002", "0001", "SGX", "FU", "NK", "20100910");
		reportDataMap.put(futureTransactionKey, -100L);		
		Report report = transactionReportProcessor.process(reportDataMap);
		assertEquals(3, report.getHeaders().size());
		assertEquals(1, report.getData().size());
		Collection<Object> dataRow = report.getData().iterator().next();
		assertEquals(3, dataRow.size());	
		
		Iterator<Object> dataRowIterator = dataRow.iterator();
		assertEquals("CL-4321-0002-0001", dataRowIterator.next());
		assertEquals("SGX-FU-NK-20100910", dataRowIterator.next());
		assertEquals( - 100L, dataRowIterator.next());
		
		
	}

}
