package com.abnamor.task.aggregator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abnamor.model.FutureTransaction;
import com.abnamor.model.FutureTransactionKey;

class FutureTransactionAggregatorTest {
	
	private FutureTransactionAggregator futureTransactionAggregator;
	
	private FutureTransaction futureTransaction;

	@BeforeEach
	void setUp() throws Exception {
		futureTransactionAggregator = new FutureTransactionAggregator();
		FutureTransactionKey futureTransactionKey = new FutureTransactionKey("CL", "4321", "0002", "0001", "0002", "SGX", "NK", "20100910");
		futureTransaction = new FutureTransaction(futureTransactionKey, 20, 17);
	}

	@AfterEach
	void tearDown() throws Exception {
		futureTransactionAggregator = null;
	}

	@Test
	void testAggregate() throws Exception {
		FutureTransactionKey key = futureTransaction.getFutureTransactionKey();
		futureTransactionAggregator.aggregate(futureTransaction);		
		assertEquals(3L,  futureTransactionAggregator.get().get(key));		
		FutureTransaction newTransaction  = new FutureTransaction(key, 40000, 80000);
		futureTransactionAggregator.aggregate(newTransaction);
		assertEquals(-39997L, futureTransactionAggregator.get().get(key));
	}
	
}

	  
