package com.intuit.travelapi.entity;

public class Sys {
	 private String type;
	 private String id;
	 private String country;
	 private String sunrise;
	 private String sunset;


	 // Getter Methods 

	 public String getType() {
	  return type;
	 }

	 public String getId() {
	  return id;
	 }

	 public String getCountry() {
	  return country;
	 }

	 public String getSunrise() {
	  return sunrise;
	 }

	 public String getSunset() {
	  return sunset;
	 }

	 // Setter Methods 

	 public void setType(String type) {
	  this.type = type;
	 }

	 public void setId(String id) {
	  this.id = id;
	 }

	 public void setCountry(String country) {
	  this.country = country;
	 }

	 public void setSunrise(String sunrise) {
	  this.sunrise = sunrise;
	 }

	 public void setSunset(String sunset) {
	  this.sunset = sunset;
	 }
}
