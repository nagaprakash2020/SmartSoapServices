package com.smartcampus.soap.database;


public class UserLocationPOJO {
	
	private String username;
	private String latitude;
	private String longitude;
	private String TimeEntered;
	
	public UserLocationPOJO(String username,String latitude,String longitude,String TimeEntered){
		this.username=username;
		this.latitude=latitude;
		this.longitude=longitude;
		this.TimeEntered=TimeEntered;
	}
	public UserLocationPOJO(){}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getTimeEntered() {
		return TimeEntered;
	}
	public void setTimeEntered(String timeEntered) {
		TimeEntered = timeEntered;
	}
	@Override
	public String toString() {
		return "UserLocationPOJO [username=" + username + ", latitude="
				+ latitude + ", longitude=" + longitude + ", TimeEntered="
				+ TimeEntered + "]";
	}
	
}
