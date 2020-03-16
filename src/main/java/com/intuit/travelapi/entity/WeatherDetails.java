package com.intuit.travelapi.entity;

public class WeatherDetails {
	
	private CloudsWeather[] weather;
	 private String base;
	 private MainParams main;
	 private float visibility;
	 private Wind wind;

	 private Clouds clouds;
	 private float dt;
	
	 private Sys sys;
	 private float timezone;
	 private float id;
	 private String name;
	


	 // Getter and setter Methods

	

	public CloudsWeather[] getWeather() {
			return weather;
		}

		public void setWeather(CloudsWeather[] weather) {
			this.weather = weather;
		}

	
	 public String getBase() {
	  return base;
	 }

	 public MainParams getMain() {
	  return main;
	 }

	 public float getVisibility() {
	  return visibility;
	 }

	 public Wind getWind() {
	  return wind;
	 }

	 public Clouds getClouds() {
	  return clouds;
	 }

	 public float getDt() {
	  return dt;
	 }

	 public Sys getSys() {
	  return sys;
	 }

	 public float getTimezone() {
	  return timezone;
	 }

	 public float getId() {
	  return id;
	 }

	 public String getName() {
	  return name;
	 }

	 public void setBase(String base) {
	  this.base = base;
	 }

	 public void setMain(MainParams main) {
	  this.main = main;
	 }

	 public void setVisibility(float visibility) {
	  this.visibility = visibility;
	 }

	 public void setWind(Wind wind) {
	  this.wind = wind;
	 }

	 public void setClouds(Clouds clouds) {
	  this.clouds = clouds;
	 }

	 public void setDt(float dt) {
	  this.dt = dt;
	 }

	 public void setSys(Sys sys) {
	  this.sys = sys;
	//  sysObject.setCountry(getSys().getCountry());
	 }

	 public void setTimezone(float timezone) {
	  this.timezone = timezone;
	 }

	 public void setId(float id) {
	  this.id = id;
	 }

	 public void setName(String name) {
	  this.name = name;
	 }

	/*
	 * public void setCod(float cod) { this.cod = cod; }
	 */
}
