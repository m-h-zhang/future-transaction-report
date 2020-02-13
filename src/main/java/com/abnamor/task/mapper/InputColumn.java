package com.abnamor.task.mapper;

public class InputColumn {	
	
	private String name;
	
	private int startIndex;
	
	private int length;	
	

	public InputColumn(String name, int startIndex, int length) {
		super();
		this.name = name;
		this.startIndex = startIndex;
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getLength() {
		return length;
	}    

}
