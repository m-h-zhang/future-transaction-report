package com.abnamor.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Report{
	
	private Collection<Object> headers;
	
	private Collection<Collection<Object>> data;

	public Report(Collection<Object> headers, Collection<Collection<Object>> data) {
		super();
		this.headers = headers;
		this.data = data;
	}

	public Collection<Object> getHeaders() {
		return headers;
	}

	public Collection<Collection<Object>> getData() {
		return data;
	}    

}
