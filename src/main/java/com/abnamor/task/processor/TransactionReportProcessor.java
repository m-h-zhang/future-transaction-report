package com.abnamor.task.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.abnamor.model.FutureTransactionKey;
import com.abnamor.model.Report;

@Service
public class TransactionReportProcessor implements Processor<Map<FutureTransactionKey, Long>, Report> {
	
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TransactionReportProcessor.class);
	
	 @Value("#{'${report.header.names}'.split(',')}")
	 private Collection<Object> headers;
	 
	 

	 public Report process(Map<FutureTransactionKey, Long> reportDataMap) throws Exception{
		
		 LOGGER.info("Report header: " + headers );
		 Collection<Collection<Object>> reportData = new ArrayList<>( reportDataMap.size() );
		 reportDataMap.forEach( (key, value) -> {
			
			 	Collection<Object> row = Arrays.asList( key.getClientInfo(), key.getProductInfo(), value );
			 	reportData.add(row);
			 	LOGGER.info("Add report Row: " + row );
		 }); 		 		
		 return new Report(this.headers, reportData);		
	}
	 
	
}
