package com.smartcampus.soap;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.smartcampus.soap.database.Delete;
import com.smartcampus.soap.database.GetUserLocation;
import com.smartcampus.soap.database.Insert;
import com.smartcampus.soap.database.InsertLocation;
import com.smartcampus.soap.database.UserLocationPOJOList;
import com.smartcampus.soap.database.Validate;


@WebService
public class LocationCatalog {

	GetUserLocation getUserLocation=new GetUserLocation();
	InsertLocation insertLocation=new InsertLocation();
	Validate validate=new Validate();
	Insert insert=new Insert();
	Delete delete=new Delete();
	
	@WebMethod
	@WebResult(name="UserLocation")
	public UserLocationPOJOList getUserLocation(){
		UserLocationPOJOList userLocationPOJOList=getUserLocation.getLocationList();
		return userLocationPOJOList;
	}
	
	/*
	 * This Method is used to Insert user Current Location to LatLong table.
	 * Call In BraodCast Receiver to insert Location every time you get the Location.
	 */
	public String insertLocation(String username,String latitude,String longitude,String time){
		return insertLocation.insertUserLocation(username, latitude, longitude, time);
	}
	
	/*
	 * Method for User Registration
	 */
	public String registerUser(String username,String password,String phoneNumber,String email,String first_name,String last_name,String user_type,String gender){
		return insert.registration(username, password, phoneNumber, email, first_name, last_name, user_type, gender);
	}
	
	/*
	 * Called When user Leaves the GeoFenced Location
	 */
	public String userExit(String username,String place,String timeOUT){
		return insert.insertTimeOUT(username, place, timeOUT);
	}
	
	/*
	 * Called When User Enter GeoFence
	 */
	public String userEnter(String username,String place,String timeIN){
		return insert.insertTimeIN(username, place, timeIN);
	}
	/*
	 * Validate User
	 */
	public String validateLogin(String username,String password){
		return validate.validateLogin(username, password);
	}
	/*
	 * Delete User
	 */
	public String deleteUser(String username){
		return delete.deleteUser(username);
	}
	
}
