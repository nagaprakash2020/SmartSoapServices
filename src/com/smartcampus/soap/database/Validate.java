package com.smartcampus.soap.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbutils.DbUtils;

public class Validate {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public String validateLogin(String username, String password) {

		String selectPass = "select password from user where username=?";
		try{
		    connection = ConnectionSource.getConnection();
		    preparedStatement=connection.prepareStatement(selectPass);
	    	preparedStatement.setString(1, username);
	    	 resultSet=preparedStatement.executeQuery();
			    resultSet.last();
			    String pass=resultSet.getString("password");
			    DbUtils.closeQuietly(connection, preparedStatement, resultSet);
			    if(pass.equals(password)){
			    	return "Success";
			    }else{
			    	return "username password did not matched";
			    }
		}catch(Exception e){
			if(e.toString().contains("empty result set")){
				return "No username exist";
			}			
			return e.toString();
		}finally{
			DbUtils.closeQuietly(connection, preparedStatement, resultSet);
		}
	}

	public static void main(String a[]){
		Validate validate=new Validate();
		System.out.println(validate.validateLogin("prakash", "pass"));
	}
}
