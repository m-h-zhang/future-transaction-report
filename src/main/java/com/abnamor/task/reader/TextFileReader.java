package com.abnamor.task.reader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TextFileReader implements Reader<InputStream, Stream<String>> {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TextFileReader.class);
	
	public Stream<String> read(InputStream inputStream) throws Exception{
		LOGGER.info( "start read()");		
		Stream<String> stream = null;
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		stream = bufferedReader.lines();	
		LOGGER.info( "end read()");	
		return stream;
	}

}
