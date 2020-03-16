package com.intuit.travelapi.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CurrencyConvReqParams {
	@Valid
    @NotNull(message="city cannot be missing or empty")
	private String city;
	@Valid
    @NotNull(message="home_currency cannot be missing or empty")
	private String home_currency;
	
	//@Positive
	@Valid
    @NotNull(message="Amount cannot be negative")
	private double amount;
	
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	public String getHome_currency() {
		return home_currency;
	}
	public void setHome_currency(String home_currency) {
		this.home_currency = home_currency;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "CurrencyConvReqParams [city=" + city + ", home_currency=" + home_currency + ", amount=" + amount + "]";
	}
}
