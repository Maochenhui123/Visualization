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
import pers.instance.Sunlightdur;
import pers.pre.change.LIST;

public class Sunlightdurdao {
	
	/**
	 * Insert the content store in the String buffer into the databases
	 * @param con: the connection to the database
	 * @param bfString: the String Buffer where store the content needed to be added into database
	 */
	public static void insertIntoSd(Connection con, String bfString) {
		String sql = "INSERT INTO sd VALUES "+"(?,?,?,?,?)";
		List<Integer> li = inFile.splitString(bfString.split(" "));
		Sunlightdur sun = null;

		sun = new Sunlightdur(li.get(0),li.get(4),li.get(5),li.get(6),li.get(7));
		
		 try {
			 PreparedStatement ppst;
			 con.setAutoCommit(false);
			 ppst = con.prepareStatement(sql);
			 ppst.setInt(1, sun.getRegion());
			 ppst.setInt(2, sun.getYear());
			 ppst.setInt(3, sun.getMonth());
			 ppst.setInt(4, sun.getDay());
			 ppst.setInt(5, sun.getSunlightdur());
			   
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
	public static List<Sunlightdur> query(int region){
		Connection con = DBconn.getConnection();
		
		
		String sql = "SELECT * FROM sd WHERE region = "+region;
		List<Sunlightdur> sunlight= new ArrayList<Sunlightdur>();
		try {
			Statement statement = con.createStatement();
			ResultSet rset = statement.executeQuery(sql);
			LIST.date.clear();
			while(rset.next()) {
				 int year = rset.getInt("year");
				 int month = rset.getInt("month");
				 int day = rset.getInt("day");
				 int sundur = rset.getInt("sun_duration");
				 LIST.date.add(year+"/"+month+"/"+day);
				 
				 sunlight.add(new Sunlightdur(region,year,month,day,sundur));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconn.close(con);
		}
		
		return sunlight;
	}
	
	/**
	 * find the same day and month in different year
	 * @param hum: the input 
	 * @return a list of result
	 */
	public static List<Sunlightdur> query_unknown(Sunlightdur sd, int region){
		Connection con = DBconn.getConnection();
		
		String sql = "SELECT * FROM sd WHERE month = ? AND day = ? AND region = "+region;
		List<Sunlightdur> s= new ArrayList<>();
		PreparedStatement ppst = null;
		
		try {
			ppst = con.prepareStatement(sql);
			ppst.setInt(1, sd.getMonth());
			ppst.setInt(2, sd.getDay());
			
			ResultSet rset = ppst.executeQuery();
			
			while(rset.next()) {
				 int year = rset.getInt("year");
				 int month = rset.getInt("month");
				 int day = rset.getInt("day");
				 int sun = rset.getInt("sun_duration");
				 
				 s.add(new Sunlightdur(region,year,month,day,sun));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconn.close(con);
		}
		
		return s;
	}
	
	/**
	 * update the table with given item: sd
	 * @param sd: the item needed to be updated
	 */
//	public static void update(Sunlightdur sd) {
//		String ud = "UPDATE sd SET sun_duration = " + sd.getSunlightdur()
//					+" WHERE year = ? AND month = ? AND day = ?";
//		Connection con =DBconn.getConnection();
//		PreparedStatement ppst = null;
//		try {
//			con.setAutoCommit(false);
//			ppst = con.prepareStatement(ud);
//			
//			ppst.setInt(1, sd.getYear());
//			ppst.setInt(2, sd.getMonth());
//			ppst.setInt(3, sd.getDay());
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
//		
//	}
}
