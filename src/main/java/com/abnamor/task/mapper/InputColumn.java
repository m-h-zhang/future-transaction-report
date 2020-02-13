package com.abnamor.task.mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public enum InputColumn {	
	
	CLIENT_TYPE,
	CLIENT_NUMBER, 
	ACCOUNT_NUMBER, 
	SUBACCOUNT_NUMBER, 
	PRODUCT_GROUP_CODE,
	EXCHANGE_CODE, 
	SYMBOL, 
	EXPIRATION_DATE,
	QUANTITY_LONG, 
	QUANTITY_SHORT;
	
	private static Logger LOGGER = LoggerFactory.getLogger(InputColumn.class);
	
	
	private int startIndex;
	
	private int endIndex;	
	
	
	public final static String INPUT_COLUMN_FILE = "input/input_columns.txt";
	
	
	static
	
	{		
		load();		
	}    
	
	
	public static void load() {
		
		try
		{
			try (  InputStream inputStream = InputColumn.class.getClassLoader().getResourceAsStream(INPUT_COLUMN_FILE );
					   InputStreamReader inputStreamReader = new InputStreamReader(inputStream );
					   BufferedReader bufferedReader = new BufferedReader( inputStreamReader );
					)
				{				
					 String line = null;
					 while ( ( line = bufferedReader.readLine() ) != null  ) {	
						 
						 StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
						 if ( stringTokenizer.hasMoreElements() ) {
							 String name = ((String)stringTokenizer.nextElement()).trim();
							 InputColumn inputColumn = InputColumn.valueOf(name);	
							 inputColumn.startIndex = Integer.parseInt(((String)stringTokenizer.nextElement()).trim()) - 1;
							 inputColumn.endIndex = Integer.parseInt(((String)stringTokenizer.nextElement()).trim());						 					
						 }				 
					 }
				}
		}catch (IOException ioe) {
			String errorMsg = "failed ot load InputColumn enum from" + INPUT_COLUMN_FILE;
			LOGGER.error(errorMsg);
			throw new RuntimeException( errorMsg );
		}
	}
	
	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}	
	
}
