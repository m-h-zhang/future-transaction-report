package com.abnamor.task.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.abnamor.model.Report;

@Service
public class CsvWriter implements Writer<Report, File>{


	private final static  Logger LOGGER = LoggerFactory.getLogger(CsvWriter.class);

	@Value("${csv.column.break}")
	private String columnBreak;
	
	@Value("${csv.line.break}")
	private String lineBreak;
	
	@Override
	public void write( Report report, File file) throws Exception {
		 try (FileWriter writer = new FileWriter(file)){
			 writeRow(report.getHeaders(), writer);			 
			
			 report.getData().stream().forEach( row -> {
				 										 writeRow(row, writer);
			                                            });
			 writer.flush();
		 }
		 
		 LOGGER.info("End of writing report to file " + file.getAbsolutePath() );
		
	}
	
	private void writeRow(Collection<Object> collection, FileWriter writer)  {
		collection.forEach( column -> {
			                 try {
								writer.append(column.toString());
								writer.append(columnBreak);
			                 }catch(IOException ioe) {
			                	 throw new RuntimeException(ioe);
			                 }			                 
							});
		try {		
			writer.append(lineBreak);
		 }catch(IOException ioe) {
        	 throw new RuntimeException(ioe);
         }	
		
	}
	
	  

}
