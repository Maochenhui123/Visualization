package pers.pre.clean;

import java.util.ArrayList;
import java.util.List;
import pers.pre.change.Normalization;
import pres.util.tool.Box;
import pers.pre.change.LIST;

public class Clean {
	/**
	 * Clean the given list
	 * @param list: the list where store the input dataset
	 * @param Anomaly: flag to judge whether to proceed the Anomaly detection
	 * @return
	 */
	public static List<List<Double>> cleandata(List<List<String>> list,int Anomaly) {
		/**get the number of rows**/
		int len = list.get(0).size();
		List<List<Double>> table = new ArrayList<>();
		/**Turn the String into Double,start from index 1 because index 0 represent the date**/
		for(int i=1;i<len;i++) {
			List<Double> row = new ArrayList<>();
			for(int j=0;j<list.size();j++) {
				row.add(Double.valueOf(list.get(j).get(i)));
			}
			interpolation(row);
			/**only if when Anomaly is 1 to proceed the detection**/
			if(Anomaly == 1) {
				anomalyDetection(row);
			}
			/**Normalized the data**/
			row = Normalization.normal(row);
			table.add(row);
		}
		/**Add the date into list**/
		for(int i=0;i<list.size();i++) {
			LIST.date.add(list.get(i).get(0));
		}
		return table;
	}
	
	/**
	 * Anomaly detection by applying the Box-plot
	 * @param list: the input list that want to proceed anomaly detection
	 */
	public static void anomalyDetection(List<Double> list) {
		List<Double> row = new ArrayList<>(list);
		List<Double> limit = Box.Limit(row);
		
		/**if the value breaks the limits then replaced with 32766**/
		for(int i=0;i<list.size();i++) {
			if(list.get(i)<limit.get(0)||list.get(i)>limit.get(1))
				list.set(i, (double) 32766);
		}
		interpolation(list);
	}
	
	/**
	 * proceed interpolation if encountering 32766
	 * @param list: The list that want to interpolate
	 */
	public static void interpolation(List<Double> list) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i)==32766) {
				interpolation(list,i);
			}
		}
	}
	/**
	 * Apply mean interpolation
	 * @param list
	 * @param index
	 */
	public static double interpolation(List<Double> list,int index) {
		double sum = 0;
		int count = 0;
		int position = -3;
		
		/**find until 7 points to calculate the mean value**/
		while(true) {
			if(count>7)
				break;
			
			/**Out of bound**/
			if((index+position)<0) {
				position++;
				continue;
			}
			else if((index+position)>(list.size()-1)) {
				position = position-7;
				continue;
			}
			/**if encounter 32766 jump**/
			if(list.get(index+position)==32766) {
				position++;
				continue;
			}
			sum += list.get(index+position);
			position++;
			count++;
			
		}
		list.set(index, sum/7);
		return sum/7;
	}
}
