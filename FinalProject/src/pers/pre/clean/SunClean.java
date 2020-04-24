package pers.pre.clean;

import java.util.ArrayList;
import java.util.List;

import pers.instance.Sunlightdur;
import pres.dao.Sunlightdurdao;

public class SunClean {
	
	/**
	 * Clean the sunlight duration
	 * @param region
	 * @return: the list that has been clustered
	 */
	public static List<Integer> clean(int region){
		List<Sunlightdur> sun = Sunlightdurdao.query(region);
		List<Sunlightdur> ud = new ArrayList<>();
		
		for(Sunlightdur sd:sun) {
			 if(sd.getSunlightdur()==32766) {
				ud.add(sd);
			}
		}
		
		for(int i=0;i<ud.size();i++) {

			List<Sunlightdur> sundur = Sunlightdurdao.query_unknown(ud.get(i),region);
			
			int sum = 0;
			int count = 0;
			
			for(Sunlightdur sd:sundur) {
				if(sd.getSunlightdur()==32766)
					continue;
				sum += sd.getSunlightdur();
				count++;
			}
			
			ud.get(i).setSunlightdur(sum/count);
		}
		
		List<Integer> s = new ArrayList<>();
		
		for(int i = 0;i<sun.size();i++) {
			s.add(sun.get(i).getSunlightdur());
		}
		
		
		return s;
	}
	
//	public static void clean() {
//		List<Sunlightdur> list = Sunlightdurdao.query();
//		List<Sunlightdur> ud = new ArrayList<>();
//		List<Integer> y = new ArrayList<>();
//		List<Integer> x = new ArrayList<>();
//		List<Integer> x0 = new ArrayList<>();
//		int count=0;
//		int sundur = 0;
//		for(Sunlightdur sun:list) {
//			if((sundur=sun.getSunlightdur())!=32766) {
//				y.add(sundur);
//				x.add(count++);
//			}
//			else if((sundur=sun.getSunlightdur())==32766) {
//				x0.add(count++);
//				ud.add(sun);
//			}
//		}
//		
//		for(int i=0;i<x0.size();i++) {
//			double sum = 0;
//			for(int j=0;j<5;j++)
//				sum += Lagrange.upgrade_lagrange(x.subList(x0.get(i)-j, x0.get(i)+5),y.subList(x0.get(i)-j, x0.get(i)+5),x0.get(i));
//			ud.get(i).setSunlightdur((int)(sum/5));
//		}
//		
////		for(Sunlightdur sun :ud) {
////			Sunlightdurdao.update(sun);
////		}	
//	}
	
}
