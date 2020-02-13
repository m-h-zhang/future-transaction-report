package com.abnamor.job;

public interface Job<I, O> {
	
	void run() throws Exception;

}
