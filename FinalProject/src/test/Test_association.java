package test;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import pers.db.rFile;
import pers.pre.change.Change;
import pers.pre.change.LIST;
import pers.pre.change.PCA;
import pers.pre.clean.Clean;
import pers.util.association.Association;

public class Test_association {
	
	public static void main(String[] args) {
		List<List<String>> content = rFile.readFile(new File("airdata.txt"));
		String[] col = {"AQI_","PM25_","PM10_","SO2_","NO2_","CO_","O3_"};
		int[] attri = {1,2,3,4,5,6};
		int Main = 0;
		List<List<Double>>list = Clean.cleandata(content,1);
		
		LIST.Norm = list;
		PCA pca = new PCA();
		
		pca.reduction(LIST.Norm);
//		List<List<Integer>> table = new ArrayList<>();
//		//cluster
//		for(int i=0;i<list.size();i++) {
//			List<Integer> row = Change.change(list.get(i));
//			table.add(row);
//		}
//		Association.getAssociation(table, col,attri,Main);
	}
	
	public static void show(List<List<Double>> object) {
		for(int i=0;i<object.size();i++) {
			for(int j=0;j<object.get(i).size();j++) {
				if(object.get(i).get(j)==1)
					System.out.println(i+" : "+j);
			}
			System.out.println();
		}
	}
	

}
