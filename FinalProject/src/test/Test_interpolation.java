package test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pers.db.rFile;
import pers.pre.clean.Clean;
import pres.util.tool.Lagrange;

public class Test_interpolation {

	public static void main(String[] args) {
		List<List<String>> list = rFile.readFile(new File("airdata.txt"));
		int len = list.get(0).size();
		List<List<Double>> table = new ArrayList<>();
		List<Double> test = new ArrayList<>();
		/**Turn the String into Double**/
		for(int i=1;i<len;i++) {
			List<Double> row = new ArrayList<>();
			
			for(int j=0;j<list.size();j++) {
				row.add(Double.valueOf(list.get(j).get(i)));
			}
			table.add(row);
		}
		test = table.get(0);
		random(test);
		interpolation(test);
		
	}
	public static Map<Integer,Double> random(List<Double> list) {
		Random r =new Random(1);
		Map<Integer,Double> ranMap = new HashMap<>();
		for(int i=0;i<10;i++) {
			int ran = r.nextInt(list.size()-1);
			System.out.println(ran+"  "+list.get(ran));
			ranMap.put(ran, list.get(ran));
			list.set(ran, 32766.0);
		}
		return ranMap;
	}
	
	public static void interpolation(List<Double> list) {
		System.out.println("After interpolation");
		for(int i=0;i<list.size();i++) {
			if(list.get(i)==32766)
				System.out.println(i+"  "+Clean.interpolation(list, i));
		}
	}

}
