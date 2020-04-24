package pers.db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.Region;

/**
 * 
 * @author mch
 * load the data in file into Database
 */

public class inFile {
	public final static String prefix = "data/";
	public final static String pressure = "SURF_CLI_CHN_MUL_DAY-PRS-10004-";
	public final static String temp = "SURF_CLI_CHN_MUL_DAY-TEM-12001-";
	public final static String humidity = "SURF_CLI_CHN_MUL_DAY-RHU-13003-";
	public final static String precipitation = "SURF_CLI_CHN_MUL_DAY-PRE-13011-";
	public final static String evaporation = "SURF_CLI_CHN_MUL_DAY-EVP-13240-";
	public final static String windy = "SURF_CLI_CHN_MUL_DAY-WIN-11002-";
	public final static String sunduration = "SURF_CLI_CHN_MUL_DAY-SSD-14032-";
	public final static String suffix = ".txt";
	
	/**
	 * split single line, extract the numeric data from it
	 * @param bfString: an array contains the numeric data
	 * @return a List object contains the numeric data extracted in that line
	 */
	public static List<Integer> splitString(String[] bfString){
		   List<Integer> row = new ArrayList<Integer>();
		   
		   for(int i=0;i<bfString.length;i++) {
			   
			   if(!bfString[i].equals("")) row.add(Integer.parseInt(bfString[i]));
		   
		   }
		   return row;
		   
	 }
	
	/**
	 * read the file and transform into array of String 
	 * @param type
	 * @return a array string contains the numeric data
	 */
	public static String[] readFile(String type) {
		String[] bfString = new String[100000];
		int position = 0;
		int y = 2010; int m = 1;
		try {
			for(; y < 2018; y++) {
				for(;m < 13; m++) {
					BufferedReader dr =null;
					
					if(m < 10) {
						dr = new BufferedReader(new FileReader(prefix+type+y+"0"+m+suffix));
						System.out.println(prefix+type+y+"0"+m+suffix);
					}
					else {
						dr = new BufferedReader(new FileReader(prefix+type+y+m+suffix));
						System.out.println(prefix+type+y+m+suffix);
					}
					
					String line=null;
					
					while((line=dr.readLine())!=null) {
						
						int region = splitString(line.split(" ")).get(0);
						if(!(region==Region.Anhui)&&!(region==Region.Beijing)&&
						   !(region==Region.Fujian)&&!(region==Region.Gansu)&&
						   !(region==Region.Guangdong)&&!(region==Region.Guangxi)&&
						   !(region==Region.Guangxi)&&!(region==Region.Guizhou)&&
						   !(region==Region.Hainan)&&!(region==Region.Hebei)&&
						   !(region==Region.Heilongjiang)&&!(region==Region.HeNan)&&
						   !(region==Region.Hubei)&&!(region==Region.Innermongolia)&&
						   !(region==Region.Jiangsu)&&!(region==Region.Jiangxi)&&
						   !(region==Region.JiLin)&&!(region==Region.Liaoning)&&
						   !(region==Region.Qinghai)&&!(region==Region.Shaanxi)&&
						   !(region==Region.Shandong)&&!(region==Region.Shanghai)&&
						   !(region==Region.Shanxi)&&!(region==Region.Sichuan)&&
						   !(region==Region.Sinkiang)&&!(region==Region.Tianjing)&&
						   !(region==Region.Yunnan)) continue;
						bfString[position]=line;
						
						position++;
					}
					dr.close();
					
				}
				m = 1;	
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println(type+y+m+suffix+" not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bfString;
		
	}
	
	
}
