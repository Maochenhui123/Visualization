package pers.pre.clean;

import java.util.ArrayList;
import java.util.List;

import pers.instance.Temperature;
import pres.dao.Tempdao;

public class TempClean {
	
	/**
	 * Clean the temperature
	 * @param region
	 * @return: the list that has been clustered
	 */
	public static List<Integer> clean(int region) {
		List<Temperature> temp = Tempdao.query(region);
		List<Temperature> ud = new ArrayList<>();
		
		for(Temperature t:temp) {
			 if(t.getAve_temp()==32766) {
				ud.add(t);
			}
		}
		
		for(int i=0;i<ud.size();i++) {

			List<Temperature> tem = Tempdao.query_unknown(ud.get(i),region);
			
			int sum = 0;
			int count = 0;
			
			for(Temperature t:tem) {
				if(t.getAve_temp()==32766)
					continue;
				sum += t.getAve_temp();
				count++;
			}
			
			ud.get(i).setAve_temp(sum/count);
		}
		List<Integer> t = new ArrayList<>();
		
		for(int i = 0;i<temp.size();i++) {
			t.add(temp.get(i).getAve_temp());
		}
		
		return t;
	}
}
