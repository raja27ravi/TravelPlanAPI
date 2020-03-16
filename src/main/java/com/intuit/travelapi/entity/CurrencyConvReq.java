package com.intuit.travelapi.entity;

public class CurrencyConvReq {
	public String homeCurrencyCode;
	public String destCurrencyCode;
	public String getHomeCurrencyCode() {
		return homeCurrencyCode;
	}
	public void setHomeCurrencyCode(String homeCurrencyCode) {
		this.homeCurrencyCode = homeCurrencyCode;
	}
	public String getDestCurrencyCode() {
		return destCurrencyCode;
	}
	public void setDestCurrencyCode(String destCurrencyCode) {
		this.destCurrencyCode = destCurrencyCode;
	}
}
