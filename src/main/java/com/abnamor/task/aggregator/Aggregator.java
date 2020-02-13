package com.abnamor.task.aggregator;

public interface Aggregator<I, O> {
	
	void aggregate(I item) throws Exception;
	
	O get();

}
