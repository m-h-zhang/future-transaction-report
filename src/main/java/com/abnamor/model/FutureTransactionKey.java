package com.abnamor.model;

import java.util.Objects;

public class FutureTransactionKey {
	
	private String clientType;
	private String clientNumber;
	private String accountNumber;
	private String subAccountNumber;
	
	private String exchangeCode;
	private String productGroupCode;
	private String symbol;
	private String expirationDate;	
	
	
	public FutureTransactionKey(String clientType, String clientNumber, String accountNumber, String subAccountNumber,
			String exchangeCode, String productGroupCode, String symbol, String expirationDate) {
		this.clientType = clientType;
		this.clientNumber = clientNumber;
		this.accountNumber = accountNumber;
		this.subAccountNumber = subAccountNumber;
		this.exchangeCode = exchangeCode;
		this.productGroupCode = productGroupCode;
		this.symbol = symbol;
		this.expirationDate = expirationDate;
	}
		
	
	public String getClientType() {
		return clientType;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getSubAccountNumber() {
		return subAccountNumber;
	}

	public String getExchangeCode() {
		return exchangeCode;
	}

	public String getProductGroupCode() {
		return productGroupCode;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getExpirationDate() {
		return expirationDate;
	}
	
	 
	public String getClientInfo() {
		return this.clientType + "-" + this.clientNumber + "-" + this.accountNumber + "-" + this.subAccountNumber;
	}
 
	
	public String getProductInfo() {
		return this.exchangeCode + "-" + this.productGroupCode + "-" + this.symbol + "-" +  this.expirationDate;
	}
	
	public String getClientProductInfo() {
		return getClientInfo() + " : " + getProductInfo();
	}
	
	  

	@Override
	public boolean equals(Object object ) {
		if (object == this) { 
			return true;
		}
        if (!(object instanceof FutureTransactionKey)) {
            return false;
        }
        FutureTransactionKey futureTransactionKey = (FutureTransactionKey) object;
        return Objects.equals(this.clientNumber, futureTransactionKey.getClientNumber()) &&
        	   Objects.equals(this.accountNumber, futureTransactionKey.getAccountNumber()) &&
        	   Objects.equals(this.clientType, futureTransactionKey.getClientType()) &&
        	   Objects.equals(this.exchangeCode, futureTransactionKey.getExchangeCode()) &&
        	   Objects.equals(this.expirationDate, futureTransactionKey.getExpirationDate()) &&
        	   Objects.equals(this.productGroupCode, futureTransactionKey.getProductGroupCode()) &&
        	   Objects.equals(this.subAccountNumber, futureTransactionKey.getSubAccountNumber()) &&
        	   Objects.equals(this.symbol, futureTransactionKey.getSymbol());
	}
	
	@Override
	public int hashCode() {
		 return Objects.hash(clientType,  clientNumber, accountNumber, subAccountNumber, exchangeCode, productGroupCode, symbol, expirationDate);		
	}


	@Override
	public String toString() {
		return "FutureTransactionKey [clientType=" + clientType + ", clientNumber=" + clientNumber + ", accountNumber="
				+ accountNumber + ", subAccountNumber=" + subAccountNumber + ", exchangeCode=" + exchangeCode
				+ ", productGroupCode=" + productGroupCode + ", symbol=" + symbol + ", expirationDate=" + expirationDate
				+ "]";
	}
	
	
	
}
