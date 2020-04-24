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
import pers.instance.Wind;
import pers.pre.change.LIST;

public class Winddao {
	
	/**
	 * Insert the content store in the String buffer into the databases
	 * @param con: the connection to the database
	 * @param bfString: the String Buffer where store the content needed to be added into database
	 */
	public static void insertIntoWind(Connection con, String bfString) {
		String sql = "INSERT INTO wind VALUES "+"(?,?,?,?,?,?)";
		List<Integer> li = inFile.splitString(bfString.split(" "));
		Wind wind = null;

		wind = new Wind(li.get(0),li.get(4),li.get(5),li.get(6),li.get(9),li.get(8));
		
		 try {
			   PreparedStatement ppst;
			   con.setAutoCommit(false);
			   ppst = con.prepareStatement(sql);
			   ppst.setInt(1, wind.getRegion());
			   ppst.setInt(2, wind.getYear());
			   ppst.setInt(3, wind.getMonth());
			   ppst.setInt(4, wind.getDay());
			   ppst.setInt(5, wind.getWindspeed());
			   ppst.setInt(6, wind.getWinddirection());
			   
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
	public static List<Wind> query(int region){
		Connection con = DBconn.getConnection();
		
		
		String sql = "SELECT * FROM wind WHERE region = "+region;
		List<Wind> wind= new ArrayList<Wind>();
		try {
			Statement statement = con.createStatement();
			ResultSet rset = statement.executeQuery(sql);
			LIST.date.clear();
			while(rset.next()) {
				 int year = rset.getInt("year");
				 int month = rset.getInt("month");
				 int day = rset.getInt("day");
				 int windspeed = rset.getInt("wind_speed");
				 int winddirection = rset.getInt("wind_direction");
				 LIST.date.add(year+"/"+month+"/"+day);
				 wind.add(new Wind(region,year,month,day,windspeed,winddirection));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconn.close(con);
		}
		
		return wind;
	}
	
	/**
	 * find the same day and month in different year
	 * @param evapor: the input 
	 * @return a list of result
	 */
	public static List<Wind> query_unknown(Wind wind, int region){
		Connection con = DBconn.getConnection();
		
		String sql = "SELECT * FROM wind WHERE month = ? AND day = ? AND region = "+region;
		List<Wind> w= new ArrayList<>();
		PreparedStatement ppst = null;
		
		try {
			ppst = con.prepareStatement(sql);
			ppst.setInt(1, wind.getMonth());
			ppst.setInt(2, wind.getDay());
			
			ResultSet rset = ppst.executeQuery();
			
			while(rset.next()) {
				 int year = rset.getInt("year");
				 int month = rset.getInt("month");
				 int day = rset.getInt("day");
				 int ws = rset.getInt("wind_speed");
				 int wd = rset.getInt("wind_direction");
				 
				 w.add(new Wind(region,year,month,day,ws,wd));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconn.close(con);
		}
		
		return w;
	}
	
	/**
	 * update the table with the input attribute wind
	 * @param wind: the item needed to be updated
	 */
//	public static void update(Wind wind) {
//		String ud = "UPDATE wind SET wind_speed = " + wind.getWindspeed()
//					+" WHERE year = ? AND month = ? AND day = ?";
//		
//		Connection con =DBconn.getConnection();
//		PreparedStatement ppst = null;
//		try {
//			con.setAutoCommit(false);
//			ppst = con.prepareStatement(ud);
//			
//			ppst.setInt(1, wind.getYear());
//			ppst.setInt(2, wind.getMonth());
//			ppst.setInt(3, wind.getDay());
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
