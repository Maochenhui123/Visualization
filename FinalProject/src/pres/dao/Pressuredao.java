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
import pers.instance.Pressure;
import pers.pre.change.LIST;

public class Pressuredao {
	
	/**
	 * Insert the content store in the String buffer into the databases
	 * @param con: the connection to the database
	 * @param bfString: the String Buffer where store the content needed to be added into database
	 */
	public static void insertIntoPres(Connection con, String bfString) {
		String sql = "INSERT INTO pres VALUES "+"(?,?,?,?,?,?,?)";
		List<Integer> li = inFile.splitString(bfString.split(" "));
		Pressure pressure = null;

		pressure = new Pressure(li.get(0),li.get(4),li.get(5),li.get(6),li.get(7),li.get(8),li.get(9));
		
		 try {
			   PreparedStatement ppst;
			   con.setAutoCommit(false);
			   ppst = con.prepareStatement(sql);
			   ppst.setInt(1, pressure.getRegion());
			   ppst.setInt(2, pressure.getYear());
			   ppst.setInt(3, pressure.getMonth());
			   ppst.setInt(4, pressure.getDay());
			   ppst.setInt(5, pressure.getAve_pressure());
			   ppst.setInt(6, pressure.getHigh_pressure());
			   ppst.setInt(7, pressure.getLow_pressure());
			   
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
	public static List<Pressure> query(int region){
		Connection con = DBconn.getConnection();
		
		
		String sql = "SELECT * FROM pres WHERE region = "+region;
		List<Pressure> pres= new ArrayList<Pressure>();
		try {
			Statement statement = con.createStatement();
			ResultSet rset = statement.executeQuery(sql);
			LIST.date.clear();
			while(rset.next()) {
				 int year = rset.getInt("year");
				 int month = rset.getInt("month");
				 int day = rset.getInt("day");
				 int ave_pres = rset.getInt("ave_pressure");
				 int high_pres = rset.getInt("high_pressure");
				 int low_pres = rset.getInt("low_pressure");
				 LIST.date.add(year+"/"+month+"/"+day);
				 pres.add(new Pressure(region,year,month,day,ave_pres,high_pres,low_pres));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconn.close(con);
		}
		
		return pres;
	}
	
	/**
	 * find the same day and month in different year
	 * @param hum: the input 
	 * @return a list of result
	 */
	public static List<Pressure> query_unknown(Pressure pres, int region){
		Connection con = DBconn.getConnection();
		
		String sql = "SELECT * FROM pres WHERE month = ? AND day = ? AND region = "+region;
		List<Pressure> p= new ArrayList<>();
		PreparedStatement ppst = null;
		
		try {
			ppst = con.prepareStatement(sql);
			ppst.setInt(1, pres.getMonth());
			ppst.setInt(2, pres.getDay());
			
			ResultSet rset = ppst.executeQuery();
			
			while(rset.next()) {
				 int year = rset.getInt("year");
				 int month = rset.getInt("month");
				 int day = rset.getInt("day");
				 int ave_pre = rset.getInt("ave_pressure");
				 int high_pre = rset.getInt("high_pressure");
				 int low_pre = rset.getInt("low_pressure");
				 p.add(new Pressure(region,year,month,day,ave_pre,high_pre,low_pre));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconn.close(con);
		}
		return p;
	}
	/**
	 * update the table with the given item: pres
	 * @param pres: the item needed to be updated
	 */
//	public static void update(Pressure pres) {
//		String ud = "UPDATE pres SET ave_pressure = " + pres.getAve_pressure()
//					+" WHERE year = ? AND month = ? AND day = ?";
//		
//		Connection con =DBconn.getConnection();
//		PreparedStatement ppst = null;
//		try {
//			con.setAutoCommit(false);
//			ppst = con.prepareStatement(ud);
//			
//			ppst.setInt(1, pres.getYear());
//			ppst.setInt(2, pres.getMonth());
//			ppst.setInt(3, pres.getDay());
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
