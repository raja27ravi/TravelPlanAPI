package com.intuit.travelapi.entity;

public class DestinationInfoRespParams {

	private String city;
	private String description;
	private String exchange_rate;
	private WeatherDetails weatherDetails;


	


	public String getExchange_rate() {
		return exchange_rate;
	}

	public void setExchange_rate(String d) {
		this.exchange_rate = d;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public WeatherDetails getWeatherDetails() {
		return weatherDetails;
	}

	public void setWeatherDetails(WeatherDetails weatherDetails) {
		this.weatherDetails = weatherDetails;
	}

	public DestinationInfoRespParams() {
		
	}
	
	public DestinationInfoRespParams(String description) {
	
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DestinationInfoRespParams [description=" + description + "]";
	}
	
	
	
	
}
