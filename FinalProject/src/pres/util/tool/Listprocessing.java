package pres.util.tool;

import java.util.ArrayList;
import java.util.List;

import pers.pre.change.LIST;

public class Listprocessing {
	
	
	public static List<List<Double>> getAttribute(List<List<Double>> table,int[] attribute){
		List<List<Double>> requiredAttribute = new ArrayList<>();
		
		for(int i=0;i<attribute.length;i++) {
			requiredAttribute.add(table.get(attribute[i]));
		}
		
		return requiredAttribute;
	}
	
	public static List<List<Double>> getAttribute(String[] attribute){
		List<List<Double>> requiredAttribute = new ArrayList<>();
		
		for(int i=0;i<attribute.length;i++) {
			requiredAttribute.add(LIST.matchNormal(attribute[i]));
		}
		System.out.println(requiredAttribute.size());
		return requiredAttribute;
	}
}
