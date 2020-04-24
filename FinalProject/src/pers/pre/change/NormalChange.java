package pers.pre.change;

import java.util.ArrayList;
import java.util.List;

import pers.pre.clean.EvapClean;
import pers.pre.clean.HumClean;
import pers.pre.clean.PrecpClean;
import pers.pre.clean.PresClean;
import pers.pre.clean.SunClean;
import pers.pre.clean.TempClean;
import pers.pre.clean.WindClean;

public class NormalChange {
	
	public static void changePrecp(int region) {
		List<Integer> precp = PrecpClean.clean(region);
		List<Double> preList = new ArrayList<>();
		for(int i=0;i<precp.size();i++) {
			if(precp.get(i)==32700) {
				preList.add(0.1);
				continue;
			}
			preList.add((double)precp.get(i));
		}
		LIST.precipitation_normal = Normalization.normal(preList);
		LIST.Norm.add(LIST.precipitation_normal);
	}
	
	public static void changeEvap(int region) {
		List<Integer> copy = EvapClean.clean(region);
		List<Double> copyList = new ArrayList<>();
		for(int i=0;i<copy.size();i++) {
			if(copy.get(i)==32700) {
				copyList.add(0.1);
				continue;
			}
			copyList.add((double)copy.get(i));
		}
		
		LIST.evaporation_normal = Normalization.normal(copyList);
		LIST.Norm.add(LIST.evaporation_normal);
	}
	
	public static void changeHum(int region) {
		List<Integer> copy = HumClean.clean(region);
		List<Double> copyList = new ArrayList<>();
		for(int i=0;i<copy.size();i++) {
			if(copy.get(i)==32700) {
				copyList.add(0.1);
				continue;
			}
			copyList.add((double)copy.get(i));
		}
		
		LIST.humidity_normal = Normalization.normal(copyList);
		LIST.Norm.add(LIST.humidity_normal);
	}
	
	public static void changePres(int region) {
		List<Integer> copy = PresClean.clean(region);
		List<Double> copyList = new ArrayList<>();
		for(int i=0;i<copy.size();i++) {
			if(copy.get(i)==32700) {
				copyList.add(0.1);
				continue;
			}
			copyList.add((double)copy.get(i));
		}
		
		LIST.pressure_normal = Normalization.normal(copyList);
		LIST.Norm.add(LIST.pressure_normal);
	}
	
	public static void changeSun(int region) {
		List<Integer> copy = SunClean.clean(region);
		List<Double> copyList = new ArrayList<>();
		for(int i=0;i<copy.size();i++) {
			if(copy.get(i)==32700) {
				copyList.add(0.1);
				continue;
			}
			copyList.add((double)copy.get(i));
		}
		
		LIST.sundur_normal = Normalization.normal(copyList);
		LIST.Norm.add(LIST.sundur_normal);
	}
	
	public static void changeTemp(int region) {
		List<Integer> copy = TempClean.clean(region);
		List<Double> copyList = new ArrayList<>();
		for(int i=0;i<copy.size();i++) {
			if(copy.get(i)==32700) {
				copyList.add(0.1);
				continue;
			}
			copyList.add((double)copy.get(i));
		}
		
		LIST.temperature_normal = Normalization.normal(copyList);
		LIST.Norm.add(LIST.temperature_normal);
	}
	public static void changeWind(int region) {
		List<Integer> copy = WindClean.clean_speed(region);
		List<Double> copyList = new ArrayList<>();
		for(int i=0;i<copy.size();i++) {
			if(copy.get(i)==32700) {
				copyList.add(0.1);
				continue;
			}
			copyList.add((double)copy.get(i));
		}
		LIST.windspeed_normal = Normalization.normal(copyList);
		LIST.Norm.add(LIST.windspeed_normal);
		
		copy.clear();
		copyList.clear();
		copy = WindClean.clean_direction(region);
		for(int i=0;i<copy.size();i++) {
			copyList.add((double)copy.get(i));
		}
		LIST.winddirection_normal = Normalization.normal(copyList);
		LIST.Norm.add(LIST.winddirection_normal);
	}
	
	
}
