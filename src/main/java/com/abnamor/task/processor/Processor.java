package com.abnamor.task.processor;

public interface Processor<I, O> {
	
	O process(I item) throws Exception;

}
