package pers.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * @author mch
 * Database connection class
 */
public class DBconn {
	final static String JDBC_DRIVER="com.mysql.jdbc.Driver";
	final static String DB_URL="jdbc:mysql://localhost:3306/weather";
	final static String USER = "root";
	final static String PASSWORD = "mch19971213";
	
	public static Connection getConnection() {

        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER).newInstance();
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return connection;
        } catch (Exception e) {
            System.out.println("Database cannot be connected!");
            e.printStackTrace();
        }
        return null;
    }
	
	public static void close(Connection c) {
        if (c != null) {
            try {
                c.close();
                System.out.println("Database connection closed.");
            } catch (Exception e) {
                /** ignore close errors **/
                System.err.println("Database cannot be closed!");
                e.printStackTrace();
            }
        }
    }
}
