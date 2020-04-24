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
import pers.instance.Evaporation;
import pers.pre.change.LIST;

public class Evapdao {
	
	/**
	 * Insert the content store in the String buffer into the databases
	 * @param con: the connection to the database
	 * @param bfString: the String Buffer where store the content needed to be added into database
	 */
	public static void insertIntoEvap(Connection con, String bfString) {
		String sql = "INSERT INTO evap VALUES "+"(?,?,?,?,?)";
		List<Integer> li = inFile.splitString(bfString.split(" "));
		Evaporation evap = null;
		if(li.get(7)==32766) evap = new Evaporation(li.get(0),li.get(4),li.get(5),li.get(6),li.get(8));
		else evap = new Evaporation(li.get(0),li.get(4),li.get(5),li.get(6),li.get(7));
		
		 try {
			 PreparedStatement ppst;
			   con.setAutoCommit(false);
			   ppst = con.prepareStatement(sql);
			   ppst.setInt(1, evap.getRegion());
			   ppst.setInt(2, evap.getYear());
			   ppst.setInt(3, evap.getMonth());
			   ppst.setInt(4, evap.getDay());
			   ppst.setInt(5, evap.getEvaporation());
			   
			   ppst.executeUpdate();
			   
			   con.commit();
			   con.setAutoCommit(true);
			   
		   } catch (SQLException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
	}
	
	/**
	 * query all the item in current table 
	 * @return the list of result where store the content
	 */
	public static List<Evaporation> query(int region){
		Connection con = DBconn.getConnection();
		
		
		String sql = "SELECT * FROM evap WHERE region = "+region;
		List<Evaporation> evap= new ArrayList<>();
		try {
			Statement statement = con.createStatement();
			ResultSet rset = statement.executeQuery(sql);
			LIST.date.clear();
			while(rset.next()) {
				 int year = rset.getInt("year");
				 int month = rset.getInt("month");
				 int day = rset.getInt("day");
				 int evaporation = rset.getInt("evaporation");
				 
				 LIST.date.add(year+"/"+month+"/"+day);
				 evap.add(new Evaporation(region,year,month,day,evaporation));
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
	 * update the table with the item: evap
	 * @param evap: the item needed to be updated
	 */
//	public static void update(Evaporation evap) {
//		String ud = "UPDATE evap SET evaporation = " + evap.getEvaporation()
//					+" WHERE year = ? AND month = ? AND day = ?";
//		
//		Connection con =DBconn.getConnection();
//		PreparedStatement ppst = null;
//		try {
//			con.setAutoCommit(false);
//			ppst = con.prepareStatement(ud);
//			
//			ppst.setInt(1, evap.getYear());
//			ppst.setInt(2, evap.getMonth());
//			ppst.setInt(3, evap.getDay());
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
	
	/**
	 * find the same day and month in different year
	 * @param evapor: the input 
	 * @return a list of result
	 */
	public static List<Evaporation> query_unknown(Evaporation evapor,int region){
		Connection con = DBconn.getConnection();
		
		String sql = "SELECT * FROM evap WHERE month = ? AND day = ? AND region = "+region;
		List<Evaporation> evap= new ArrayList<Evaporation>();
		PreparedStatement ppst = null;
		
		try {
			ppst = con.prepareStatement(sql);
			ppst.setInt(1, evapor.getMonth());
			ppst.setInt(2, evapor.getDay());
			
			ResultSet rset = ppst.executeQuery();
			
			while(rset.next()) {
				 int year = rset.getInt("year");
				 int month = rset.getInt("month");
				 int day = rset.getInt("day");
				 int evaporation = rset.getInt("evaporation");
				 
				 evap.add(new Evaporation(region,year,month,day,evaporation));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconn.close(con);
		}
		
		return evap;
		
		
	}
	
}
