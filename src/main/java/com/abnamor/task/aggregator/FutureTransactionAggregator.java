package com.abnamor.task.aggregator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.abnamor.model.FutureTransaction;
import com.abnamor.model.FutureTransactionKey;


@Component
public class FutureTransactionAggregator implements Aggregator<FutureTransaction, Map<FutureTransactionKey, Long>>{
	
	  private final static  Logger LOGGER = LoggerFactory.getLogger(FutureTransactionAggregator.class);
	
	 private Map<FutureTransactionKey, Long> transactionMap = new ConcurrentHashMap<>();
	
	 @Override
	 public void aggregate(FutureTransaction futureTransaction) throws Exception{				
		
		long amount = futureTransaction.getQuantityLong() - futureTransaction.getQuantityShort();		
		LOGGER.info( "Before aggregating for " + futureTransaction.getFutureTransactionKey().getClientProductInfo() + ",  amount=" + amount + ", existing total Amount=" +  this.transactionMap.get(futureTransaction.getFutureTransactionKey()));
		this.transactionMap.merge(futureTransaction.getFutureTransactionKey(), amount, Long::sum);
		LOGGER.info( "After aggregating, new total amount is " + this.transactionMap.get(futureTransaction.getFutureTransactionKey()));
	
	 }
	 
	 @Override
	 public Map<FutureTransactionKey, Long> get(){
		 return this.transactionMap;
	 }

}
