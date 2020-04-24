package pers.pre.clean;

import java.util.ArrayList;
import java.util.List;

import pers.instance.Precipitation;
import pres.dao.Precipitationdao;

public class PrecpClean {
	
	/**
	 * Clean the precipitation
	 * @param region
	 * @return: the list that has been clustered
	 */
	public static List<Integer> clean(int region){
		List<Precipitation> precp = Precipitationdao.query(region);
		List<Precipitation> ud = new ArrayList<>();
		
		for(Precipitation p:precp) {
			 if(p.getPrecipitation()==32766) {
				ud.add(p);
			}
		}
		
		for(int i=0;i<ud.size();i++) {

			List<Precipitation> pre = Precipitationdao.query_unknown(ud.get(i),region);
			
			int sum = 0;
			int count = 0;
			
			for(Precipitation p:pre) {
				if(p.getPrecipitation()==32766)
					continue;
				sum += p.getPrecipitation();
				count++;
			}
			
			ud.get(i).setPrecipitation(sum/count);
		}
		
		List<Integer> p = new ArrayList<>();
		
		for(int i = 0;i<precp.size();i++) {
			p.add(precp.get(i).getPrecipitation());
		}
		
		return p;
	}
}
