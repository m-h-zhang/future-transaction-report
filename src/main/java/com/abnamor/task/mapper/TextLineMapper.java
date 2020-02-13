package com.abnamor.task.mapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.abnamor.model.FutureTransaction;
import com.abnamor.model.FutureTransactionKey;


@Service
public class TextLineMapper implements Mapper<String, FutureTransaction> {	
	 

	
	private final static Logger LOGGER = LoggerFactory.getLogger(TextLineMapper.class);
	
	@Override
	public FutureTransaction map(String line) throws Exception {
	
		FutureTransactionKey futureTransactionKey = new FutureTransactionKey(   getValue(line, InputColumn.CLIENT_TYPE), 
																				getValue(line, InputColumn.CLIENT_NUMBER), 
																				getValue(line, InputColumn.ACCOUNT_NUMBER), 
																				getValue(line, InputColumn.SUBACCOUNT_NUMBER),  
																				getValue(line, InputColumn.EXCHANGE_CODE),
																				getValue(line, InputColumn.PRODUCT_GROUP_CODE),
																				getValue(line, InputColumn.SYMBOL),
																				getValue(line, InputColumn.EXPIRATION_DATE) ) ;
		
		String strQuantityLong = getValue(line, InputColumn.QUANTITY_LONG);		
		long quantityLong = StringUtils.isBlank(strQuantityLong) ? 0 : Integer.parseInt(strQuantityLong);
		String strQuantityShort = getValue(line, InputColumn.QUANTITY_SHORT);	;		
		long quantityShort = StringUtils.isBlank(strQuantityShort) ? 0 : Integer.parseInt(strQuantityShort);			
		FutureTransaction futureTransaction = new FutureTransaction(futureTransactionKey, quantityLong, quantityShort);	
		LOGGER.info( "Mapped  line string [" + line + "] to  futureTransaction :" + futureTransaction.toString() );
		return futureTransaction;
	}
	
	
	
	private String getValue( String line, InputColumn inputColumn ) {
		
		return line.substring(inputColumn.getStartIndex(), inputColumn.getEndIndex()).trim();
	}
		
}