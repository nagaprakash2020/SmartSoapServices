package com.smartcampus.soap.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbutils.DbUtils;


public class InsertLocation {

	Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

	public String insertUserLocation(String username,String latitude,String longitude,String time)
	{
		try{
			String countUsername="select COUNT(username) from LatLong where username=?";
		  connection = ConnectionSource.getConnection();
		  preparedStatement = connection.prepareStatement(countUsername);
		  preparedStatement.setString(1,username);	
		  resultSet=preparedStatement.executeQuery();
		  resultSet.last();
		  int count=resultSet.getInt("count(username)");
		  if(count>0)
		  {
			  String updateLocation="update LatLong set latitude=?,longitude=?,timeEntered=? where username=?";
			  
		    	//Update Record with username 'name'
		    	preparedStatement=connection.prepareStatement(updateLocation);
		    	preparedStatement.setString(1, latitude);
		    	preparedStatement.setString(2, longitude);
		    	preparedStatement.setString(3, time);
		    	preparedStatement.setString(4, username);
		    	
		    	DbUtils.closeQuietly(connection, preparedStatement, resultSet);
		    	return "Updated record";
		  }else{
			//No record with username 'name' exist in table so, insert New record
			    String insertRecord="insert into LatLong values(?,?,?,?)";
		    	preparedStatement=connection.prepareStatement(insertRecord);
		    	preparedStatement.setString(1, username);
		    	preparedStatement.setString(2, latitude);
		    	preparedStatement.setString(3, longitude);
		    	preparedStatement.setString(4, time);
		    	
		    	DbUtils.closeQuietly(connection, preparedStatement, resultSet);
		    	return "New Record Inserted";
		  }
		}
		catch(Exception e){
			return e.toString();
		}finally{
			DbUtils.closeQuietly(connection, preparedStatement, resultSet);
		}
		}
	
	public static void main(String a[]){
		InsertLocation insertLocation=new InsertLocation();
		System.out.println(insertLocation.insertUserLocation("user6", "26.372934", "-80.102499", "3:52:32 Jan-11-2013"));
	}
	
}

