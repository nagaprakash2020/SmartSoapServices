package com.smartcampus.soap.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.dbutils.DbUtils;

public class Delete {

	

	public String deleteUser(String username){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			String deleteUser = "delete from user where username=?";
			
			connection = ConnectionSource.getConnection();
			preparedStatement = connection.prepareStatement(deleteUser);
			preparedStatement.setString(1, username);
			int i=preparedStatement.executeUpdate();
			connection.close();
			preparedStatement.close();
			if(i>0){
				
				return "Success";
			}else{
				return "failed";
			}
			
		} catch (Exception e) {
			return e.toString();
		} finally{
			/*
			 * DbUtils provides a good way to close connections.
			 * These jars are available in apache commons
			 */
			DbUtils.closeQuietly(connection);
			DbUtils.closeQuietly(preparedStatement);
		}
	}
	public static void main(String a[]){
		Delete d=new Delete();
		System.out.println(d.deleteUser("dummy1"));
	}
}