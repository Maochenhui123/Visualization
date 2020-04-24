package pres.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pers.db.DBconn;
import pers.db.inFile;
import pers.instance.Humidity;
import pers.pre.change.LIST;

public class Humiditydao {
	
	/**
	 * Insert the content store in the String buffer into the databases
	 * @param con: the connection to the database
	 * @param bfString: the String Buffer where store the content needed to be added into database
	 */
	public static void insertIntoHum(Connection con, String bfString) {
		String sql = "INSERT INTO hum VALUES "+"(?,?,?,?,?)";
		List<Integer> li = inFile.splitString(bfString.split(" "));
		Humidity hum = null;

		hum = new Humidity(li.get(0),li.get(4),li.get(5),li.get(6),li.get(7));
		
		 try {
			 PreparedStatement ppst;
			   con.setAutoCommit(false);
			   ppst = con.prepareStatement(sql);
			   ppst.setInt(1, hum.getRegion());
			   ppst.setInt(2, hum.getYear());
			   ppst.setInt(3, hum.getMonth());
			   ppst.setInt(4, hum.getDay());
			   ppst.setInt(5, hum.getHumidity());
			   
			   ppst.executeUpdate();
			   
			   con.commit();
			   con.setAutoCommit(true);
			   
//			   System.out.println("successfully insert");
		   } catch (SQLException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
	}
	
	/**
	 * query all the item in current table 
	 * @return the list of result where store the content
	 */
	public static List<Humidity> query(int region){
		Connection con = DBconn.getConnection();
		
		
		String sql = "SELECT * FROM hum WHERE region = "+region;
		List<Humidity> hum= new ArrayList<Humidity>();
		try {
			Statement statement = con.createStatement();
			ResultSet rset = statement.executeQuery(sql);
			LIST.date.clear();
			while(rset.next()) {
				 int year = rset.getInt("year");
				 int month = rset.getInt("month");
				 int day = rset.getInt("day");
				 int humidity = rset.getInt("humidity");
				 LIST.date.add(year+"/"+month+"/"+day);
				 hum.add(new Humidity(region,year,month,day,humidity));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconn.close(con);
		}
		
		return hum;
	}
	
	/**
	 * find the same day and month in different year
	 * @param evapor: the input 
	 * @return a list of result
	 */
	public static List<Humidity> query_unknown(Humidity hum, int region){
		Connection con = DBconn.getConnection();
		
		String sql = "SELECT * FROM hum WHERE month = ? AND day = ? AND region = "+region;
		List<Humidity> humidity= new ArrayList<>();
		PreparedStatement ppst = null;
		
		try {
			ppst = con.prepareStatement(sql);
			ppst.setInt(1, hum.getMonth());
			ppst.setInt(2, hum.getDay());
			
			ResultSet rset = ppst.executeQuery();
			
			while(rset.next()) {
				 int year = rset.getInt("year");
				 int month = rset.getInt("month");
				 int day = rset.getInt("day");
				 int humidi = rset.getInt("humidity");
				 
				 humidity.add(new Humidity(region,year,month,day,humidi));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconn.close(con);
		}
		
		return humidity;
	}
	
	/**
	 * update the table with the given item hum
	 * @param hum: the item needed to be updated
	 */
//	public static void update(Humidity hum) {
//		String ud = "UPDATE hum SET humidity = " + hum.getHumidity()
//					+" WHERE year = ? AND month = ? AND day = ?";
//		
//		Connection con =DBconn.getConnection();
//		PreparedStatement ppst = null;
//		try {
//			con.setAutoCommit(false);
//			ppst = con.prepareStatement(ud);
//			
//			ppst.setInt(1, hum.getYear());
//			ppst.setInt(2, hum.getMonth());
//			ppst.setInt(3, hum.getDay());
//			
//			ppst.executeUpdate();
//			con.commit();
//			con.setAutoCommit(true);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			DBconn.close(con);
//		}
//	}
}
