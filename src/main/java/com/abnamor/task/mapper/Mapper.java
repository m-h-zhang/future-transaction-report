package com.abnamor.task.mapper;

public interface Mapper <I, O >{ 
		 
	O map(I item)  throws Exception;
}
