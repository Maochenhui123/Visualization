package pers.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class rFile {
	
	public static List<String> splitString(String bfString){
		   List<String> row = new ArrayList<>();
		   String[] bf = bfString.split("\t");
		   for(int i=0;i<bf.length;i++) {
			   if(!bf[i].equals(" ")) row.add(bf[i]);
		   
		   }
		   return row;
		   
	 }
	
	public static List<List<String>> readFile(File file) {
		
		List<List<String>> content = new ArrayList<>();
		try {
			BufferedReader dr = new BufferedReader(new FileReader(file));
			String line = null;
			List<String> list;
			while((line=dr.readLine())!=null) {
				list = splitString(line);
				content.add(list);
			}
			
			dr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

}

