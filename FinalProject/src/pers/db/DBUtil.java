package pers.db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DBUtil {
	   /**
	    * get all the value from the table
	    * @param rset
	    * @param col
	    * @param filterValue
	    * @return all the value
	    * @throws SQLException
	    */
	   public static List<List<Integer>> getFilteredResult(ResultSet rset, int col, String filterValue) throws SQLException{

	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        List<Integer> row;
	        int colNum = rset.getMetaData().getColumnCount();
	        
	        while(rset.next()) {
	        	row=new ArrayList<Integer>();
	        	for(int i=0;i<colNum;i++) {
	        		row.add(rset.getInt(i));
	        	}
	        	result.add(row);
	        }
	        
	        return result;
	   
	   }
	   
	   /**
	    * Find the maximum value within the table
	    * @param table
	    * @param attribute
	    * @return: the maximam value
	    */
	   public static int maxValue(String table, String attribute) {
		   String sql = "SELECT MAX("+ attribute+") FROM "+ table;
		   Connection con = DBconn.getConnection();
		   int max = 32766;
		   try {
			Statement stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			
			if(rset.next()) max = rset.getInt(1);
			
			return max;
		   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }finally {
			   DBconn.close(con);
		   }
		   
		   return max;
	   }
	   
	   /**
	    * Find the minimum value within the table
	    * @param table
	    * @param attribute
	    * @return: the minimum value within the table
	    */
	   public static int minValue(String table, String attribute) {
		   String sql = "SELECT MIN("+ attribute+") FROM "+ table;
		   Connection con = DBconn.getConnection();
		   int min = 32766;
		   try {
			Statement stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			
			if(rset.next()) min = rset.getInt(1);
			
			return min;
		   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }finally {
			   DBconn.close(con);
		   }
		   
		   return min;
	   }	   

}
