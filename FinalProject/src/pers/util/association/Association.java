package pers.util.association;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pers.pre.change.Change;
import pers.pre.change.KMeans;
import pers.pre.change.LIST;
import pers.pre.change.PCA;
import pres.util.tool.Sort;

public class Association {
	
	/**
	 * Get the association rules for the given DataSet
	 * @param table: Data after preprocessing
	 * @param col: The name of each columns
	 * @param attribute: the index of attribute that want to observe the interaction with the Main attribute
	 * @param Main: The main index of the attribute
	 */
	public static void getAssociation(List<List<Integer>> table,String[] col,int[] attribute,int Main) {
		long startTime = System.currentTimeMillis();
		/**The containers for further calculation**/
		List<List<String>> list = new ArrayList<>();
		
		/**Iterate the table to store the content into the containers: list**/
		for(int i=0;i<table.get(0).size();i++) {
			List<String> integration = new ArrayList<>();
			for(int j=0;j<attribute.length;j++) {
				integration.add(col[attribute[j]]+table.get(attribute[j]).get(i));
			}
			List<String> item = new ArrayList<>(integration);
			
			/**
			 * store the content of associative attributes 
			 * where user want to observe the dependence with the main attribute
			 **/
			LIST.rest.add(item);
			integration.add(col[Main]+table.get(Main).get(i));
			list.add(integration);
		}
		
		/**count the frequency for each items in the list**/
		FP_growth.freqCount(list);
		
		/**Sort according to the frequency**/
		for(int i = 0; i < list.size(); i++)
			Sort.bubbleSort(list.get(i));
		
		/**construct the Tree using the sorted list**/
		FP_growth.init(list);
		
		List<Tid> item = new ArrayList<>();
		List<Node> l = new ArrayList<>();
		
		/**from Level 0 to Level 4, find the association rules associate with it**/
		for(int i=0;i<5;i++) {
			/**Retrieve the tree to find the node in the leaf**/
			Tree.retrieveNode(FP_growth.ini, col[Main]+i, l);
			/**Construct the Fp-tree**/
			Node fp = FP_growth.fpTree(l,item);
			
			Map<String,Integer> map = new HashMap<>();
			/**count the frequency for the conditional rules**/
			FP_growth.freqSet(item,fp,col[Main]+i,map);
			
			/**calculate support based on the frequency**/
			Map<String,Integer> support = support(map, list);
			/**calculate the confidence based on the support**/
			Map<String,Double> conf = new HashMap<>();
			confidence(map,support,conf);
			
			System.out.println(conf);
			
			/**Put the result into List**/
			LIST.all.put(col[Main]+i, split(conf));
			item.clear();
			l.clear();
		}
		
		/**cut the tree from the root**/
		FP_growth.ini.cutTree();
		FP_growth.freqMap.clear();
		
		long endTime_FP = System.currentTimeMillis();
		
		save(Main,table.get(0).size(),col,table.get(Main));
		
		List<List<Double>> requiredAttribute = getAttribute(LIST.Norm,attribute);
		PCA pca = new PCA();
		List<List<Double>> reducedTable = pca.reduction(requiredAttribute);
		
		/**Cluster the multidimensional series**/
		if(reducedTable.size()>1)
			KMeans.All(reducedTable);
		else if(reducedTable.size() == 1) 
			LIST.integration = Change.change(reducedTable.get(0));
		
		System.out.println("Run time for FP_growth: "+(endTime_FP-startTime)+"ms");
	}
	
	/**
	 * Get the association rules for weather DataSet
	 * @param region: the region code in China
	 * @param attribute: the attribute name where want to observe the dependence with the main attribute
	 * @param main: The main attribute
	 */
	public static void getAssociation(int region,String[] attribute,String main){
		/**preprocessing the dataset into form that is available for Fp-growth algorithm**/
		Change.evapChange(region);
		Change.precpChange(region);
		Change.humChange(region);
		Change.presChange(region);
		Change.sundurChange(region);
		Change.tempChange(region);
		Change.windSpeedChange(region);
		Change.windDirectionChange(region);
		
		/**Name the main attribute**/
		String head = LIST.match(main);
		/**container for the List**/
		List<List<String>> list = new ArrayList<>();
		
		long startTime = System.currentTimeMillis();
		/**Iterate the table to store the content into the containers: list**/
		for(int i=0;i<LIST.evaporation.size();i++) {
			List<String> integration = new ArrayList<>();
			
			for(int j=0;j<attribute.length;j++) {
				LIST.match(attribute[j], i, integration);
			}
			
			List<String> item = new ArrayList<>(integration);
			LIST.rest.add(item);
			LIST.match(main, i, integration);
			list.add(integration);
		}
		
		/**count the frequency for each items in the list**/
		FP_growth.freqCount(list);
		
		/**Sort the list**/
		for(int i = 0; i < list.size(); i++)
			Sort.bubbleSort(list.get(i));
		
		/**Construct the tree based on the sorted list**/
		FP_growth.init(list);
		
		List<Tid> item = new ArrayList<>();
		List<Node> l = new ArrayList<>();
		
		/**from Level 0 to Level 4, find the association rules associate with it**/
		for(int i=0;i<5;i++) {
			/**Retrieve the tree to find the node in the leaf**/
			Tree.retrieveNode(FP_growth.ini, head+i, l);
			/**Construct the FP-tree**/
			Node fp = FP_growth.fpTree(l,item);
			
			Map<String,Integer> map = new HashMap<>();
			
			/**count the frequency for the prefix rules**/
			FP_growth.freqSet(item,fp,head+i,map);
			/**Calculate the support based on the frequency set**/
			Map<String,Integer> support = support(map, list);
			/**Calculate the confidence based on the support set**/
			Map<String,Double> conf = new HashMap<>();
			confidence(map,support,conf);
			System.out.println(conf);
			
			LIST.all.put(head+i, split(conf));
			item.clear();
			l.clear();
		}
		long endTime_FP = System.currentTimeMillis();
		save(main,LIST.precipitation.size());
		
		/**cut the tree from the root**/
		FP_growth.ini.cutTree();
		FP_growth.freqMap.clear();
		
		List<List<Double>> requiredAttribute = getAttribute(attribute);
		PCA pca = new PCA();
		List<List<Double>> reducedTable = pca.reduction(requiredAttribute);
		
		/**Cluster the multidimensional series**/
		if(reducedTable.size()>1)
			KMeans.All(reducedTable);
		else if(reducedTable.size() == 1) 
			LIST.integration = Change.change(reducedTable.get(0));
		
		System.out.println("Run time for FP_growth: "+(endTime_FP-startTime)+"ms");
		
	}
	
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
	
	/**
	 * Get the confidence of the set, based on the frequency and support we get
	 * and put the result into Map - conf
	 * E.g. {A,B}->C appear 30 times, {A,B} appears 50 times
	 * then the confidence will be 30/50
	 * Also eliminating the confidence < 0.5
	 * @param frequency: the set of frequent set we get from the FP-tree
	 * @param support: the support value of conditional prefix of frequent set
	 * @param conf: the map where store the result
	 */
	public static void confidence(Map<String,Integer> frequency, Map<String,Integer> support, Map<String,Double> conf) {
		/**Iterate the map to calculate the confidence**/
		for(Map.Entry<String, Integer> entry:frequency.entrySet()) {
			double con = entry.getValue().doubleValue()/support.get(entry.getKey()).doubleValue();
			/**Set the minimum confidence**/
			if(con>0.5)
				conf.put(entry.getKey(), con);
		}
	}
	
	/**
	 * Count the support for the prefix of the frequency we get previously
	 * supposing the rule {A,B}->C, count the frequency of {A,B}
	 * @param map: the frequency set we calculated previously
	 * @param list: the whole input list
	 * @return map where store the result of support
	 */
	public static Map<String,Integer> support(Map<String,Integer> map, List<List<String>> list) {
		/**Initialize the support map**/
		Map<String,Integer> support = new HashMap<>();
		/**Iterate the list**/
		for(int i=0;i<list.size();i++) {
			/**Iterate the map to compare the value to each List contained in the list**/
			for(Map.Entry<String, Integer> entry:map.entrySet()) {
				String Key = entry.getKey();
				/**split the Key**/
				List<String> mapKey = new ArrayList<>(Arrays.asList(Key.split("-")));
				/**set a flag to judge whether the list contains the set**/
				boolean flag = true;
				for(int j=0;j<mapKey.size()-1;j++) {
					if(list.get(i).contains(mapKey.get(j)))
						continue;
					flag = false;
					break;
				}
				
				if(flag == true) {
					if(support.containsKey(Key)) support.put(Key, support.get(Key)+1);
					else support.put(Key, 1);
				}	
			}
		}
		
		return support;	
	}
	/**
	 * Split the String in the String
	 * E.g. A-B-C will split into {A,B,C}
	 * @param map
	 * @return
	 */
	public static Map<List<String>,Double> split(Map<String,Double> map){
		Map<List<String>,Double> res = new HashMap<>();
		for(Map.Entry<String, Double> entry:map.entrySet()) {
			String[] key = entry.getKey().split("-");
			List<String> list = new ArrayList<>();
			for(int i=0;i<key.length-1;i++) {
				list.add(key[i]);
			}
			res.put(list, entry.getValue());
		}
		return res;
	}
	
	/**
	 * Save the association rule into List of association and index
	 * @param main the main feature that user selected
	 */
	public static void save(String main,int len) {
		
		for(int i=0;i<len;i++) {
			double association = contains(i, LIST.match(main)+LIST.matchList(main).get(i));
			if(association!=-1) {
				LIST.index.add(i);
				LIST.association.add(association);
			}
		}	
	}
	
	/**
	 * Save the necessary index of association rule into corresponding list
	 * @param Main: The main index
	 * @param len: the length of the list
	 * @param col: the name of each required columns
	 * @param list: The whole list
	 */
	public static void save(int Main,int len,String[] col,List<Integer> list) {
		for(int i=0;i<len;i++) {
			double association = contains(i, col[Main]+list.get(i));
			if(association!=-1) {
				LIST.index.add(i);
				LIST.association.add(association);
			}
		}	
	}
	
	/**
	 * see the content in the List to judge whether the rule can be applied to current index
	 * @param index current index
	 * @param main the main feature that user selected
	 * @return
	 */
	public static double contains(int index,String main) {
		boolean flag = true;
		Map<List<String>,Double> map = LIST.all.get(main);
		Map<List<String>,Double> m = new HashMap<>();
		List<String> mapKey = new ArrayList<>();
		double value = -1;
		int length = 0;
		if(map == null)
			return -1;
		for(Map.Entry<List<String>, Double> entry:map.entrySet()) {
			flag = true;
			List<String> key = entry.getKey();
			for(int i=0;i<key.size();i++) {
				if(!LIST.rest.get(index).contains(key.get(i))) {
					flag = false;
					break;
				}
			}
			if(flag == false)
				continue;
			m.put(key, entry.getValue());
		}
		
		
		for(Map.Entry<List<String>, Double> entry:m.entrySet()) {
			List<String> key = entry.getKey();
			if(key.size()>length) {
				mapKey = key;
				length = key.size();
				value = entry.getValue();
			}
			
		}
		
		if(value!=-1) {
			String ass="";
			for(int i=0;i<mapKey.size();i++) {
				ass+=mapKey.get(i)+" ";
			}
			LIST.relation.add(ass);
		}
		
		return value;
	}

}
