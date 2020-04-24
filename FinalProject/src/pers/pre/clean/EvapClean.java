package pers.pre.clean;

import java.util.ArrayList;
import java.util.List;

import pers.instance.Evaporation;
import pres.dao.Evapdao;

public class EvapClean {
	 
	/**
	 * Clean the evaporation
	 * @param region
	 * @return evap the list that has been clustered
	 */
	public static List<Integer> clean(int region) {
		List<Evaporation> ud = new ArrayList<>();
		List<Evaporation> evapList = Evapdao.query(region);
		
		for(Evaporation evap:evapList) {
			 if(evap.getEvaporation()==32766) {
				ud.add(evap);
			}
		}
		
		for(int i=0;i<ud.size();i++) {
			List<Evaporation> e = Evapdao.query_unknown(ud.get(i),region);
			
			int sum = 0;
			int count = 0;
			
			for(Evaporation ev:e) {
				if(ev.getEvaporation()==32766)
					continue;
				sum += ev.getEvaporation();
				count++;
			}
			
			ud.get(i).setEvaporation(sum/count);
		}
		
		List<Integer> evap = new ArrayList<>();
		
		for(int i = 0;i<evapList.size();i++) {
			evap.add(evapList.get(i).getEvaporation());
		}
		
		return evap;	
	}
	
//	public static void Check(List<Evaporation> evapList, int region) {
//		int[] list = new int[evapList.size()];
//		for(int i=0;i<evapList.size();i++) {
//			list[i]=evapList.get(i).getEvaporation();
//		}
//		boolean flag = false;
//		List<Double> limit = Box.Limit(list);
//		for(int i=0;i<list.length;i++) {
//			if((double)evapList.get(i).getEvaporation()>limit.get(1)||(double)evapList.get(i).getEvaporation()<limit.get(0)) {
//				flag = true;
//				System.out.println(i+" "+evapList.get(i));
//				evapList.get(i).setEvaporation(32766);
//			}	
//		}
//		if(flag == true)
//			clean(region);
//	}
	

}
