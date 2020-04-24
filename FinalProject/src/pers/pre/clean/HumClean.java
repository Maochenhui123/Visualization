package pers.pre.clean;

import java.util.ArrayList;
import java.util.List;

import pers.instance.Humidity;
import pres.dao.Humiditydao;

public class HumClean {
	
    /**
     * Clean the humidity
     * @param region 
     * @return hum: list after clustered
     */
	public static List<Integer> clean(int region){
		List<Humidity> hum = Humiditydao.query(region);
		List<Humidity> ud = new ArrayList<>();
		
		for(Humidity h:hum) {
			 if(h.getHumidity()==32766) {
				ud.add(h);
			}
		}
		
		for(int i=0;i<ud.size();i++) {

			List<Humidity> humidity = Humiditydao.query_unknown(ud.get(i),region);
			
			int sum = 0;
			int count = 0;
			
			for(Humidity h:humidity) {
				if(h.getHumidity()==32766)
					continue;
				sum += h.getHumidity();
				count++;
			}
			
			ud.get(i).setHumidity(sum/count);
		}
		
		List<Integer> h = new ArrayList<>();
		
		for(int i = 0;i<hum.size();i++) {
			h.add(hum.get(i).getHumidity());
		}
		
		return h;
	}
}
