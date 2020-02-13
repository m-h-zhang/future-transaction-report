package com.abnamor.task.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextFileReaderTest {
	
	private InputStream inputStream;
	private String line1;
	private String line2;
	
	
	private TextFileReader textFileReader;

	@BeforeEach
	void setUp() throws Exception {
		textFileReader = new TextFileReader();
		String s = line1 + "\n" + line2;
		inputStream = new ByteArrayInputStream(s.getBytes(Charset.forName("UTF-8")));
	}

	

	@Test
	void testRead() throws Exception {
	 
		Stream<String> lines = textFileReader.read(inputStream);
		assertEquals(2, lines.count());			
	}
	
	@AfterEach
	void tearDown() throws Exception {
		inputStream = null;
		line1 = null;
		line2 = null;
		textFileReader = null;
	}

}
