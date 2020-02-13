package com.abnamor.task.mapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abnamor.model.FutureTransaction;
import com.abnamor.model.FutureTransactionKey;


@Service
public class TextLineMapper implements Mapper<String, FutureTransaction> {	
	 

	@Autowired
	private InputColumns inputColumns;
	
	private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TextLineMapper.class);
	
	@Override
	public FutureTransaction map(String line) throws Exception {
	
		FutureTransactionKey futureTransactionKey = new FutureTransactionKey(mapFieldValue(line,"CLIENT_TYPE"), 
				                                                             mapFieldValue(line,"CLIENT_NUMBER"), 
				                                                             mapFieldValue(line,"ACCOUNT_NUMBER"), 
				                                                             mapFieldValue(line,"SUBACCOUNT_NUMBER"), 
				                                                             mapFieldValue(line,"EXCHANGE_CODE"),
				                                                             mapFieldValue(line,"PRODUCT_GROUP_CODE"),
				                                                             mapFieldValue(line,"SYMBOL"), 
				                                                             mapFieldValue(line,"EXPIRATION_DATE")) ;
		
		String strQuantityLong = mapFieldValue(line,"QUANTITY_LONG");		
		long quantityLong = StringUtils.isBlank(strQuantityLong) ? 0 : Integer.parseInt(strQuantityLong);
		String strQuantityShort = mapFieldValue(line,"QUANTITY_SHORT");		
		long quantityShort = StringUtils.isBlank(strQuantityShort) ? 0 : Integer.parseInt(strQuantityShort);			
		FutureTransaction futureTransaction = new FutureTransaction(futureTransactionKey, quantityLong, quantityShort);	
		LOGGER.info( "Mapped  line string [" + line + "] to  futureTransaction :" + futureTransaction.toString() );
		return futureTransaction;
	}
	
	private String mapFieldValue( String line, String columnName ) {
		
		InputColumn inputColumn = getInpuColumns().getInputColumn( columnName );
		if ( inputColumn == null ) {
			String errorMessage = "Failed to map column [" + columnName + "] from inputColumns in ReportLineMapper, inputColumns:  "  + getInpuColumns();
			LOGGER.error ( errorMessage);
			throw new RuntimeException( errorMessage );			
		}else {
			return StringUtils.trimToEmpty(line.substring( inputColumn.getStartIndex(), inputColumn.getStartIndex() + inputColumn.getLength() ));
		}		
	}
	
	protected InputColumns getInpuColumns() {
		return this.inputColumns;
	}

}