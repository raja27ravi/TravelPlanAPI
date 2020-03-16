package com.intuit.travelapi.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class DestinationInfoReqParams {
	@Valid
    @NotNull(message="city name cannot be missing or empty")
	private String city;
	@Valid
    @NotNull(message="home_currency cannot be missing or empty")
	private String home_currency;
	@Valid
    @NotNull(message="travel_date cannot be missing or empty")
	private String travel_date;
	
	public DestinationInfoReqParams() {
		
		//no arg constructor
	}
	
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

	public String getTravel_date() {
		return travel_date;
	}

	public void setTravel_date(String travel_date) {
		this.travel_date = travel_date;
	}

	public DestinationInfoReqParams(String city, String home_currency, String travel_date) {
		//super();
		this.city = city;
		this.home_currency = home_currency;
		this.travel_date = travel_date;
	}

	@Override
	public String toString() {
		return "DestinationInfoReqParams [city=" + city + ", home_currency=" + home_currency + ", travel_date="
				+ travel_date + "]";
	}
	
	
	

}
