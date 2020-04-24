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
import pers.instance.Temperature;
import pers.pre.change.LIST;

public class Tempdao {
	
	/**
	 * Insert the content store in the String buffer into the databases
	 * @param con: the connection to the database
	 * @param bfString: the String Buffer where store the content needed to be added into database
	 */
	public static void insertIntoTemp(Connection con, String bfString) {
		String sql = "INSERT INTO temp VALUES "+"(?,?,?,?,?,?,?)";
		List<Integer> li = inFile.splitString(bfString.split(" "));
		Temperature temp = null;

		temp = new Temperature(li.get(0),li.get(4),li.get(5),li.get(6),li.get(7),li.get(8),li.get(9));
		
		 try {
			   PreparedStatement ppst;
			   con.setAutoCommit(false);
			   ppst = con.prepareStatement(sql);
			   ppst.setInt(1, temp.getRegion());
			   ppst.setInt(2, temp.getYear());
			   ppst.setInt(3, temp.getMonth());
			   ppst.setInt(4, temp.getDay());
			   ppst.setInt(5, temp.getAve_temp());
			   ppst.setInt(6, temp.getHigh_temp());
			   ppst.setInt(7, temp.getLow_temp());
			   
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
	public static List<Temperature> query(int region){
		Connection con = DBconn.getConnection();
		
		
		String sql = "SELECT * FROM temp WHERE region = "+region;
		List<Temperature> temp= new ArrayList<Temperature>();
		try {
			Statement statement = con.createStatement();
			ResultSet rset = statement.executeQuery(sql);
			LIST.date.clear();
			while(rset.next()) {
				 int year = rset.getInt("year");
				 int month = rset.getInt("month");
				 int day = rset.getInt("day");
				 int ave_pres = rset.getInt("ave_temp");
				 int high_pres = rset.getInt("high_temp");
				 int low_pres = rset.getInt("low_temp");
				 LIST.date.add(year+"/"+month+"/"+day);
				 temp.add(new Temperature(region,year,month,day,ave_pres,high_pres,low_pres));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconn.close(con);
		}
		
		return temp;
	}
	
	/**
	 * find the same day and month in different year
	 * @param hum: the input 
	 * @return a list of result
	 */
	public static List<Temperature> query_unknown(Temperature temp, int region){
		Connection con = DBconn.getConnection();
		
		String sql = "SELECT * FROM temp WHERE month = ? AND day = ? AND region = "+region;
		List<Temperature> t= new ArrayList<>();
		PreparedStatement ppst = null;
		
		try {
			ppst = con.prepareStatement(sql);
			ppst.setInt(1, temp.getMonth());
			ppst.setInt(2, temp.getDay());
			
			ResultSet rset = ppst.executeQuery();
			
			while(rset.next()) {
				 int year = rset.getInt("year");
				 int month = rset.getInt("month");
				 int day = rset.getInt("day");
				 int ave_temp = rset.getInt("ave_temp");
				 int high_temp = rset.getInt("high_temp");
				 int low_temp = rset.getInt("low_temp");
				 t.add(new Temperature(region,year,month,day,ave_temp,high_temp,low_temp));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconn.close(con);
		}
		return t;
	}
	
	/**
	 * update the table with the given item: temp
	 * @param temp: the item needed to be updated
	 */
//	public static void update(Temperature temp) {
//		String ud = "UPDATE temp SET ave_temperature = " + temp.getAve_temp()
//					+" WHERE year = ? AND month = ? AND day = ?";
//		
//		Connection con =DBconn.getConnection();
//		PreparedStatement ppst = null;
//		try {
//			con.setAutoCommit(false);
//			ppst = con.prepareStatement(ud);
//			
//			ppst.setInt(1, temp.getYear());
//			ppst.setInt(2, temp.getMonth());
//			ppst.setInt(3, temp.getDay());
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
