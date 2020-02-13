package com.abnamor.task.mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class InputColumns 
{
	
	private Map<String, InputColumn> inputColumns;
 
	
	public InputColumns( @Value("${input.column.file}") String formatFile, @Value("${input.column.separator}") String separator) throws IOException {
		  
		this.inputColumns = new HashMap<>();
		loadInputColumnFile(inputColumns, formatFile, separator);
		
	}
	
	private void loadInputColumnFile( Map<String, InputColumn> inputColumns,  String formatFile, String separator ) throws IOException{	
				
		try (  InputStream inputStream = getClass().getClassLoader().getResourceAsStream( formatFile );
			   InputStreamReader inputStreamReader = new InputStreamReader(inputStream );
			   BufferedReader bufferedReader = new BufferedReader( inputStreamReader );
			){
			
			int index = 0;
			
			 String line = null;
			 while ( ( line = bufferedReader.readLine() ) != null  ) {	
				 
				 StringTokenizer stringTokenizer = new StringTokenizer(line, separator);
				 if ( stringTokenizer.hasMoreElements() ) {
					 String name = ((String)stringTokenizer.nextElement()).replaceAll(" ", "_");
					 if ( stringTokenizer.hasMoreElements() ) {
						 int  length = Integer.parseInt(((String)stringTokenizer.nextElement()).trim());
						 InputColumn inputColumn = new InputColumn( name, index, length );
						 inputColumns.put( name, inputColumn );
						 index +=length;
						 
					 }					
				 }				 
			 }
		}
	}

	public  InputColumn getInputColumn( String columnName ){
		return this.inputColumns.get( columnName );
	}
	
	@Override
	public String toString() {
		return this.inputColumns.toString();
	}
	

}
