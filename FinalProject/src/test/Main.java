package test;

import java.sql.Connection;

import pers.db.DBconn;
import pers.db.inFile;
import pres.dao.Tempdao;

public class Main {

	public static void main(String[] args) {
		String[] bfString;
		int i = 0; 
		
		Connection con = DBconn.getConnection();
//		bfString = inFile.readFile(inFile.evaporation);
//		while(bfString[i] != null) {
//			Evapdao.insertIntoEvap(con, bfString[i]);
//			System.out.println(bfString[i]);
//			i++;
//		}
//		
//		i=0;
//		bfString = inFile.readFile(inFile.humidity);
//		while(bfString[i] != null) {
//			Humiditydao.insertIntoHum(con, bfString[i]);
//			System.out.println(bfString[i]);
//			i++;
//		}
////		
//		i=0;
//		bfString = inFile.readFile(inFile.precipitation);
//		while(bfString[i]!=null) {
//			Precipitationdao.insertIntoPrecp(con, bfString[i]);
//			System.out.println(bfString[i]);
//			i++;
//		}
	
//		i=0;
//		bfString = inFile.readFile(inFile.sunduration);
//		while(bfString[i] != null) {
//			Sunlightdurdao.insertIntoSd(con, bfString[i]);
//			System.out.println(bfString[i]);
//			i++;
//		}
//		
//		i=0;
//		bfString = inFile.readFile(inFile.pressure);
//		while(bfString[i] != null) {
//			Pressuredao.insertIntoPres(con, bfString[i]);
//			System.out.println(bfString[i]);
//			i++;
//		}
//		
		i=0;
		bfString = inFile.readFile(inFile.temp);
		while(bfString[i] != null) {
			Tempdao.insertIntoTemp(con, bfString[i]);
			System.out.println(bfString[i]);
			i++;
		}
//		
//		i=0;
//		bfString = inFile.readFile(inFile.windy);
//		while(bfString[i] != null) {
//			Winddao.insertIntoWind(con, bfString[i]);
//			System.out.println(bfString[i]);
//			i++;
//		}
		DBconn.close(con);
//		System.out.println(DBUtil.minValue("evap", "evaporation"));	
	}

}
