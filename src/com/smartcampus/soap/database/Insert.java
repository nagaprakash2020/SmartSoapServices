package com.smartcampus.soap.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbutils.DbUtils;
/*
 * This Class has three methods
 * 1. To insert the TimeIN
 * 2.TimeOut
 * 3.registration - to create new user in User Table
 * 
 */
public class Insert {

	
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
		
		//String selectDB="USE FauDB;";
		String placeCount="Select gender from Counting where place=?";
		String insertLocation="insert into Counting values(?,?,?,?,?,?)";
		String getCount="select male from Counting where place='library'";
		
		/*
		 * Use when User Enter GeoFenced Location
		 */	
		public String insertTimeIN(String username,String place,String timeIN){
			try{
				
			    connection = ConnectionSource.getConnection();
			    
			    	String insertTimeIn="insert into Counting values(?,?,?,?)";
			    	preparedStatement=connection.prepareStatement(insertTimeIn);
			    	preparedStatement.setString(1, username);
			    	preparedStatement.setString(2, place);
			    	preparedStatement.setString(3, timeIN);
			    	preparedStatement.setString(4, "NUll");
			    	preparedStatement.executeUpdate();
			    	connection.close();
			    	preparedStatement.close();
			    	return username +"is in" + place;
				}catch(Exception e){
				return e.toString();
			}finally{
				DbUtils.closeQuietly(connection, preparedStatement, resultSet);
			}
		}// End of insertTimeIN function
			
			/*
			 * Call this method when user exits GeoFenced Location
			 */		
		public String insertTimeOUT(String username,String place,String timeOUT){
			try{
				
			    connection = ConnectionSource.getConnection();
			    
			    	String insertTimeIn="UPDATE Counting SET timeout=? WHERE username=? and place=? and timeOUT=?";
			    	preparedStatement=connection.prepareStatement(insertTimeIn);
			    	preparedStatement.setString(1, timeOUT);
			    	preparedStatement.setString(2, username);
			    	preparedStatement.setString(3, place);
			    	preparedStatement.setString(4, "NULL");
			    	preparedStatement.executeUpdate();
			    	connection.close();
			    	preparedStatement.close();
			    	return "TimeOut value of "+username+" updated";
				}catch(Exception e){
				return e.toString();
			}finally{
				DbUtils.closeQuietly(connection, preparedStatement, resultSet);
			}
		}// End of insertTimeOut function
		
		/*
		 * This method creates a new 
		 * user by inserting a new record to USER table
		 * 
		 */
		public String registration(String userName,String password,String phoneNumber, String email,
				String first_name,String last_name,String user_type,String gender){
			
			try{
				
				String selectUserName="select COUNT(username) from user where username=?";
			    connection = ConnectionSource.getConnection();
			    
			    preparedStatement=connection.prepareStatement(selectUserName);
			    preparedStatement.setString(1, userName);
			    resultSet=preparedStatement.executeQuery();
			    resultSet.last();
			    int count=resultSet.getInt("count(username)");
			    System.out.print(count);
			    if(count>1)
			    {
			    	return "UserName Already Exist";
			    }else{
			    	//insert new user to User table
			    	String insertUser="INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			    	
			    	//Insert New Record with Id 'userName'
			    	preparedStatement=connection.prepareStatement(insertUser);
			    	preparedStatement.setString(1, userName);
			    	preparedStatement.setString(2, password);
			    	preparedStatement.setString(3, phoneNumber);
			    	preparedStatement.setString(4, email);
			    	preparedStatement.setString(5, first_name);
			    	preparedStatement.setString(6, last_name);
			    	preparedStatement.setString(7, user_type);
			    	preparedStatement.setString(8, gender);
			    	preparedStatement.executeUpdate();
			    	connection.close();
			    	preparedStatement.close();
			    	return "Success";
			    	
			    }
			 }catch(Exception e){
				 if(e.toString().contains("Duplicate entry"))
				 return "Please Choose Different Username";
				 else
				 return e.toString();
			 }finally{
					DbUtils.closeQuietly(connection, preparedStatement, resultSet);
				}
		}//End of Registration Method
		
		
		
		public static void main(String a[]){
			Insert insert=new Insert();
			System.out.println(insert.registration("user42", "pass", "1212324565", "user4@fau.edu", "user", "four", "professor", "f"));
			
		}
}
