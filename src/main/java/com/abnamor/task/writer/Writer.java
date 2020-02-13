package com.abnamor.task.writer;

public interface Writer<I, P> {
	
	 void write(I report, P output) throws Exception;

}
