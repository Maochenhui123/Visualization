package pres.util.tool;

import java.util.ArrayList;
import java.util.List;

public class Lagrange {
	public static double langrange(List<Double> list,int index,int count) {
		double li = 1;
		double L = 0;
		int position = -5;
		int counter = 0;
		List<Integer> X = new ArrayList<>();
		while(true) {
			if(counter>count)
				break;
			/**Out of bound**/
			if((index+position)<0) {
				position++;
				continue;
			}
			else if((index+position)>(list.size()-1)) {
				position = position-14;
				continue;
			}
			/**if encounter 32766 jump**/
			if(list.get(index+position)==32766) {
				position++;
				continue;
			}
			X.add(index+position);
			position++;
			counter++;
		}
		for(int i=0;i<count;i++) {
			li = 1;
			for(int j=0;j<count;j++) {
				if(i==j)
					continue;
				li*= (double)(index-X.get(j))/(X.get(i)-X.get(j));
			}
			L+=list.get(X.get(i))*li;
		}
		list.set(index, L);
		return L;
	}
	
	public static void main(String[] args) {
		List<Double> list = new ArrayList<>();
		list.add(1.0);list.add(2.0);list.add(32766.0);list.add(4.0);
		list.add(5.0);list.add(6.0);list.add(7.0);list.add(8.0);
		list.add(9.0);list.add(10.0);list.add(11.0);list.add(12.0);
		langrange(list,2,5);
		System.out.println(list);
	}

}
