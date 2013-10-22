package com.smartcampus.soap.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

public class GetUserLocation {

	Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
	public UserLocationPOJOList getLocationList(){
		
		UserLocationPOJOList userMain=new UserLocationPOJOList();
		try {
			
			//List<UserLocationPOJO> userLocationList=new ArrayList<>();
			String getLocationQuery="select * from LatLong";
			connection = ConnectionSource.getConnection();
			
			preparedStatement=connection.prepareStatement(getLocationQuery);	
			resultSet=preparedStatement.executeQuery();
			resultSet.beforeFirst();
			while(resultSet.next()){
				UserLocationPOJO userLocation=new UserLocationPOJO();
				userLocation.setUsername(resultSet.getString("username"));
				userLocation.setLatitude(resultSet.getString("latitude"));
				
				userLocation.setLongitude(resultSet.getString("longitude"));
				userLocation.setTimeEntered(resultSet.getString("timeEntered"));
				//userLocationList.add(userLocation);
				userMain.getLocationList().add(userLocation);
			}
			DbUtils.closeQuietly(connection, preparedStatement, resultSet);
			//return userLocation;
			return userMain;
			  
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}finally{
			DbUtils.closeQuietly(connection, preparedStatement, resultSet);
		}
		
	}
	
	
	public static void main(String a[]){
		
		GetUserLocation getUserLocation=new GetUserLocation();
		UserLocationPOJOList userLocationPOJOList=getUserLocation.getLocationList();
		List<UserLocationPOJO> listLocations=userLocationPOJOList.getLocationList();
		
		for(UserLocationPOJO userlocation:listLocations)
			System.out.print(userlocation);
		
	}
	
}
