package com.intuit.travelapi.entity;

public class MainParams {
	 private String temp;
	 private String feels_like;
	 private String temp_min;
	 private String temp_max;
	 private String pressure;
	 private String humidity;


	 // Getter Methods 

	 public String getTemp() {
	  return temp;
	 }

	 public String getFeels_like() {
	  return feels_like;
	 }

	 public String getTemp_min() {
	  return temp_min;
	 }

	 public String getTemp_max() {
	  return temp_max;
	 }

	 public String getPressure() {
	  return pressure;
	 }

	 public String getHumidity() {
	  return humidity;
	 }

	 // Setter Methods 

	 public void setTemp(String temp) {
	  this.temp = temp;
	 }

	 public void setFeels_like(String feels_like) {
	  this.feels_like = feels_like;
	 }

	 public void setTemp_min(String temp_min) {
	  this.temp_min = temp_min;
	 }

	 public void setTemp_max(String temp_max) {
	  this.temp_max = temp_max;
	 }

	 public void setPressure(String pressure) {
	  this.pressure = pressure;
	 }

	 public void setHumidity(String humidity) {
	  this.humidity = humidity;
	 }
}
