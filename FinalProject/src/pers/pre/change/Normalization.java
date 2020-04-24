package pers.pre.change;

import java.util.ArrayList;
import java.util.List;

/**
 * Normalized the incoming list into range from [0,1]
 * @author mch
 *
 */
public class Normalization {
	public static List<Double> normal(List<Double> list){
		List<Double> result = new ArrayList<>();
		double max = Max(list);
		double min = Min(list);
		for(int i=0;i<list.size();i++) {
			double x = (double)(list.get(i) - min)/(max-min);
			result.add(x);
		}
		return result;
	}
	
	public static double Max(List<Double> list) {
		double max = -999;
		for(int i=0;i<list.size();i++) {
			if(list.get(i)>max)
				max = list.get(i);
		}

		return max;
	}
	
	public static double Min(List<Double> list) {
		double min = 99999;
		for(int i=0;i<list.size();i++) {
			if(list.get(i)<min)
				min = list.get(i);
		}

		return min;
	}
	
}
