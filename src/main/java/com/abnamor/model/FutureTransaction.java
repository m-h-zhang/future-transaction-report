package com.abnamor.model;

public class FutureTransaction {
	
	private FutureTransactionKey futureTransactionKey;	
	private long quantityLong;
	private long quantityShort;	
	
	
	public FutureTransaction(FutureTransactionKey futureTransactionKey, long quantityLong, long quantityShort) {
		super();
		this.futureTransactionKey = futureTransactionKey;
		this.quantityLong = quantityLong;
		this.quantityShort = quantityShort;
	}
	
	
	public long getQuantityLong() {
		return quantityLong;
	}
	
	public long getQuantityShort() {
		return quantityShort;
	}


	public FutureTransactionKey getFutureTransactionKey() {
		return futureTransactionKey;
	}


	@Override
	public String toString() {
		return "FutureTransaction [futureTransactionKey=" + futureTransactionKey + ", quantityLong=" + quantityLong
				+ ", quantityShort=" + quantityShort + "]";
	}
	

}
