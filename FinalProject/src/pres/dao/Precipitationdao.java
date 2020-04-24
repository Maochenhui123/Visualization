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
import pers.instance.Precipitation;
import pers.pre.change.LIST;

public class Precipitationdao {
	
	/**
	 * Insert the content store in the String buffer into the databases
	 * @param con: the connection to the database
	 * @param bfString: the String Buffer where store the content needed to be added into database
	 */
	public static void insertIntoPrecp(Connection con, String bfString) {
		String sql = "INSERT INTO precp VALUES "+"(?,?,?,?,?)";
		List<Integer> li = inFile.splitString(bfString.split(" "));
		Precipitation precipitation = null;
		precipitation = new Precipitation(li.get(0),li.get(4),li.get(5),li.get(6),li.get(9));
		
		 try {
			 PreparedStatement ppst;
			   con.setAutoCommit(false);
			   ppst = con.prepareStatement(sql);
			   ppst.setInt(1, precipitation.getRegion());
			   ppst.setInt(2, precipitation.getYear());
			   ppst.setInt(3, precipitation.getMonth());
			   ppst.setInt(4, precipitation.getDay());
			   ppst.setInt(5, precipitation.getPrecipitation());
			   
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
	public static List<Precipitation> query(int region){
		Connection con = DBconn.getConnection();
		
		
		String sql = "SELECT * FROM precp WHERE region = "+region;
		List<Precipitation> evap= new ArrayList<Precipitation>();
		try {
			Statement statement = con.createStatement();
			ResultSet rset = statement.executeQuery(sql);
			LIST.date.clear();
			while(rset.next()) {
				 int year = rset.getInt("year");
				 int month = rset.getInt("month");
				 int day = rset.getInt("day");
				 int evaporation = rset.getInt("precipitation");
				 LIST.date.add(year+"/"+month+"/"+day);
				 evap.add(new Precipitation(region,year,month,day,evaporation));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconn.close(con);
		}
		
		return evap;
	}
	
	/**
	 * find the same day and month in different year
	 * @param hum: the input 
	 * @return a list of result
	 */
	public static List<Precipitation> query_unknown(Precipitation precp, int region){
		Connection con = DBconn.getConnection();
		
		String sql = "SELECT * FROM precp WHERE month = ? AND day = ? AND region = "+region;
		List<Precipitation> p= new ArrayList<>();
		PreparedStatement ppst = null;
		
		try {
			ppst = con.prepareStatement(sql);
			ppst.setInt(1, precp.getMonth());
			ppst.setInt(2, precp.getDay());
			
			ResultSet rset = ppst.executeQuery();
			
			while(rset.next()) {
				 int year = rset.getInt("year");
				 int month = rset.getInt("month");
				 int day = rset.getInt("day");
				 int pre = rset.getInt("precipitation");
				 
				 p.add(new Precipitation(region,year,month,day,pre));
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
	 * update the table with the given item: precp
	 * @param precp: the item needed to be updated
	 */
//	public static void update(Precipitation precp) {
//		String ud = "UPDATE precp SET precipitation = " + precp.getPrecipitation()
//					+" WHERE year = ? AND month = ? AND day = ?";
//		
//		Connection con =DBconn.getConnection();
//		PreparedStatement ppst = null;
//		try {
//			con.setAutoCommit(false);
//			ppst = con.prepareStatement(ud);
//			
//			ppst.setInt(1, precp.getYear());
//			ppst.setInt(2, precp.getMonth());
//			ppst.setInt(3, precp.getDay());
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
