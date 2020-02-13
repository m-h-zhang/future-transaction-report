package com.abnamor.task.reader;

public interface Reader<I, O > {
	
	 O read(I input) throws Exception;
}
