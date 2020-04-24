package pers.pre.clean;

import java.util.ArrayList;
import java.util.List;

import pers.instance.Pressure;
import pres.dao.Pressuredao;


public class PresClean {
	
	/**
	 * Clean the pressure
	 * @param region
	 * @return: the list that has been clustered
	 */
	public static List<Integer> clean(int region){
		List<Pressure> pres = Pressuredao.query(region);
		List<Pressure> ud = new ArrayList<>();
		
		for(Pressure p:pres) {
			 if(p.getAve_pressure()==32766) {
				ud.add(p);
			}
		}
		
		for(int i=0;i<ud.size();i++) {

			List<Pressure> pre = Pressuredao.query_unknown(ud.get(i),region);
			
			int sum = 0;
			int count = 0;
			
			for(Pressure p:pre) {
				if(p.getAve_pressure()==32766)
					continue;
				sum += p.getAve_pressure();
				count++;
			}
			
			ud.get(i).setAve_pressure(sum/count);
		}
		
		List<Integer> p = new ArrayList<>();
		
		for(int i = 0;i<pres.size();i++) {
			p.add(pres.get(i).getAve_pressure());
		}
		
		return p;
	}
	
	

}
