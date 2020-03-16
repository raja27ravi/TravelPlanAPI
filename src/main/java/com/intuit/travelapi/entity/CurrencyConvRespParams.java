package com.intuit.travelapi.entity;

public class CurrencyConvRespParams {
	
	private String city;
	private String home_currency;
	private String destination_currency;
	private double home_amount;
	private double destination_amount;
	//private String errorMessage;


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
	public String getDestination_currency() {
		return destination_currency;
	}
	public void setDestination_currency(String destination_currency) {
		this.destination_currency = destination_currency;
	}
	public double getHome_amount() {
		return home_amount;
	}
	public void setHome_amount(double home_amount) {
		this.home_amount = home_amount;
	}
	public double getDestination_amount() {
		return destination_amount;
	}
	public void setDestination_amount(double destination_amount) {
		this.destination_amount = destination_amount;
	}
	@Override
	public String toString() {
		return "CurrencyConvRespParams [city=" + city + ", home_currency=" + home_currency + ", destination_currency="
				+ destination_currency + ", home_amount=" + home_amount + ", destination_amount=" + destination_amount
				+ "]";
	}

}
