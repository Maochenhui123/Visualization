package pres.util.tool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Boxplot to detect the anomaly value
 * @author mch
 *
 */
public class Box {
	
	public static List<Double> cal_Q(List<Double> list) {
		Collections.sort(list);
		List<Double> Q = new ArrayList<>();
		double Q1 = 0, Q2 = 0, Q3 = 0;
		double index = (double)list.size()/4;
		double cof = (int)index+1-index;
		
		Q1 = list.get((int)index-1)*cof+list.get((int)index)*(1-cof);
		
		cof = (int)index*2+1-2*index;
		Q2 = list.get((int)index*2-1)*cof+list.get((int)index*2)*(1-cof);
		
		cof = (int)index*3+1-3*index;
		Q3 = list.get((int)index*3-1)*cof+list.get((int)index*3)*(1-cof);
		Q.add(Q1);Q.add(Q2);Q.add(Q3);
		return Q;
	}
	
	public static List<Double> Limit(List<Double> list){
		
		List<Double> Q = cal_Q(list);
		double IQR = Q.get(2)-Q.get(0);
		double max = Q.get(2) + IQR*1.5;
		double min = Q.get(0) - IQR*1.5;
		List<Double> Lim = new ArrayList<>();
		Lim.add(min);Lim.add(max);
		
		return Lim;
	}
	
	
}
