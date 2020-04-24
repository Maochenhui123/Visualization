package data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import pers.db.DBconn;

public class Database_preprocessed {
	@SuppressWarnings("unused")
	private static void create(Connection con) {
    	String pressure = "CREATE TABLE IF NOT EXISTS PRES ("
    					  +"region INT(5) NOT NULL,"
    					  +"year INT(5) NOT NULL,"
    					  +"month INT(3) NOT NULL,"
    					  +"day INT(3) NOT NULL,"
    					  +"ave_pressure DOUBLE(7) NOT NULL,"
    					  +"high_pressure DOUBLE(7) NOT NULL,"
    					  +"low_pressure DOUBLE(7) NOT NULL);";
    	String temp = "CREATE TABLE IF NOT EXISTS TEMP ("
				  	  +"region INT(5) NOT NULL,"
				  	  +"year INT(5) NOT NULL,"
				  	  +"month INT(3) NOT NULL,"
				  	  +"day INT(3) NOT NULL,"
				  	  +"ave_temp DOUBLE(7) NOT NULL,"
				  	  +"high_temp DOUBLE(7) NOT NULL,"
				  	  +"low_temp DOUBLE(7) NOT NULL"
				  	  +");";
    	String humidity = "CREATE TABLE IF NOT EXISTS HUM ("
			  	  		  +"region INT(5) NOT NULL,"
			  	  		  +"year INT(5) NOT NULL,"
			  	  		  +"month INT(3) NOT NULL,"
			  	  		  +"day INT(3) NOT NULL,"
			  	  		  +"humidity DOUBLE(7) NOT NULL);";
    	String precipitation = "CREATE TABLE IF NOT EXISTS PRECP ("
	  	  		  			   +"region INT(5) NOT NULL,"
	  	  		  			   +"year INT(5) NOT NULL,"
	  	  		  			   +"month INT(3) NOT NULL,"
	  	  		  			   +"day INT(3) NOT NULL,"
	  	  		  			   +"precipitation DOUBLE(7) NOT NULL);";
    	String evaporation = "CREATE TABLE IF NOT EXISTS EVAP ("
		  			   		 +"region INT(5) NOT NULL,"
		  			   		 +"year INT(5) NOT NULL,"
		  			   		 +"month INT(3) NOT NULL,"
		  			   		 +"day INT(3) NOT NULL,"
		  			   		 +"evaporation DOUBLE(7) NOT NULL);";
    	String wind = "CREATE TABLE IF NOT EXISTS WIND ("
			   		  +"region INT(5) NOT NULL,"
			   		  +"year INT(5) NOT NULL,"
			   		  +"month INT(3) NOT NULL,"
			   		  +"day INT(3) NOT NULL,"
			   		  +"wind_speed DOUBLE(7) NOT NULL,"
			   		  +"wind_direction INT(7) NOT NULL);";
    	String sunduration = "CREATE TABLE IF NOT EXISTS SD ("
			   		 	  +"region INT(5) NOT NULL,"
			   		 	  +"year INT(5) NOT NULL,"
			   		 	  +"month INT(3) NOT NULL,"
			   		 	  +"day INT(3) NOT NULL,"
			   		 	  +"sun_duration DOUBLE(7) NOT NULL);";
    	
    	try {
    		Statement stmt = con.createStatement();
    		con.setAutoCommit(false);
			stmt.executeUpdate(pressure);
			stmt.executeUpdate(temp);
			stmt.executeUpdate(humidity);
			stmt.executeUpdate(precipitation);
			stmt.executeUpdate(evaporation);
			stmt.executeUpdate(wind);
			stmt.executeUpdate(sunduration);
			con.commit();
			DBconn.close(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
