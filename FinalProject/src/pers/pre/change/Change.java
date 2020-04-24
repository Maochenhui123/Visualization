package pers.pre.change;


import java.util.ArrayList;
import java.util.List;

import pers.pre.clean.WindClean;

/**
 * 
 * @author mch
 * Use the KMeans to cluster the data in all dimensions
 */

public class Change {
	
	/**
	 * Cluster the given list
	 * @param list
	 * @return
	 */
	public static List<Integer> change(List<Double> list){
		double[] copy = new double[list.size()];
		
		for(int i=0;i<list.size();i++)
			copy[i] = list.get(i);
		
		/**implement the cluster**/
		double[][] g = BasicKMeans.cluster(copy, 5);
		double[] max = new double [g.length];
		double[] min = new double [g.length];
		
		for(int i = 0;i<g.length;i++) {
			if(BasicKMeans.max(g[i])==-1) {
				max[i] = 0;
				min[i] = 1;
				continue;
			}
			max[i] = g[i][BasicKMeans.max(g[i])];
			min[i] = g[i][BasicKMeans.min(g[i])];
		}
		
		List<Integer> dim = new ArrayList<>();
		for(int i = 0 ; i<copy.length; i++) {
			for(int j=0; j<g.length; j++) {
				if(copy[i]<=max[j]&&copy[i]>=min[j]) {
					dim.add(j);
					break;
				}
			}
		}
		return dim;
	}
	
	public static void evapChange(int region) {
		NormalChange.changeEvap(region);
		
		List<Double> evap = LIST.evaporation_normal;
		double[] evapcopy = new double[evap.size()];
		
		for(int i=0;i<evap.size();i++)
			evapcopy[i] = evap.get(i);
		
		double[][] g = BasicKMeans.cluster(evapcopy, 5);
		double[] max = new double [g.length];
		double[] min = new double [g.length];
		
		for(int i = 0;i<g.length;i++) {
			if(BasicKMeans.max(g[i])==-1) {
				max[i] = 0;
				min[i] = 1;
				continue;
			}
			max[i] = g[i][BasicKMeans.max(g[i])];
			min[i] = g[i][BasicKMeans.min(g[i])];
		}
		
		/**turn the value into discrete value**/
		for(int i = 0 ; i<evapcopy.length; i++) {
			for(int j=0; j<g.length; j++) {
				if(evapcopy[i]<=max[j]&&evapcopy[i]>=min[j]) {
					LIST.evaporation.add(j);
					break;
				}
			}
		}
	}
	
	public static void humChange(int region) {
		NormalChange.changeHum(region);
		
		List<Double> hum = LIST.humidity_normal;
		double[] humcopy = new double[hum.size()];
		
		for(int i=0;i<hum.size();i++)
			humcopy[i] = hum.get(i);
		
		double[][] g = BasicKMeans.cluster(humcopy, 5);
		double[] max = new double [g.length];
		double[] min = new double [g.length];

		
		for(int i = 0;i<g.length;i++) {
			if(BasicKMeans.max(g[i])==-1) {
				max[i] = 0;
				min[i] = 1;
				continue;
			}
			max[i] = g[i][BasicKMeans.max(g[i])];
			min[i] = g[i][BasicKMeans.min(g[i])];
		}
		
		/**turn the value into discrete value**/
		for(int i = 0 ; i<humcopy.length; i++) {
			for(int j=0; j<g.length; j++) {
				if(humcopy[i]<=max[j]&&humcopy[i]>=min[j]) {
					LIST.humidity.add(j);
					break;
				}
			}
		}

	}
	
	public static void presChange(int region) {
		NormalChange.changePres(region);
		
		List<Double> pres = LIST.pressure_normal;
		double[] prescopy = new double[pres.size()];
		
		for(int i=0;i<pres.size();i++)
			prescopy[i] = pres.get(i);
		
		double[][] g = BasicKMeans.cluster(prescopy, 5);
		double[] max = new double [g.length];
		double[] min = new double [g.length];

		
		for(int i = 0;i<g.length;i++) {
			if(BasicKMeans.max(g[i])==-1) {
				max[i] = 0;
				min[i] = 1;
				continue;
			}
			max[i] = g[i][BasicKMeans.max(g[i])];
			min[i] = g[i][BasicKMeans.min(g[i])];
		}
		
		/**turn the value into discrete value**/
		for(int i = 0 ; i<prescopy.length; i++) {
			for(int j=0; j<g.length; j++) {
				if(prescopy[i]<=max[j]&&prescopy[i]>=min[j]) {
					LIST.pressure.add(j);
					break;
				}
			}
		}
	}
	
	public static void sundurChange(int region) {
		NormalChange.changeSun(region);
		
		List<Double> sun = LIST.sundur_normal;
		double[] copy = new double[sun.size()];
		
		for(int i=0;i<sun.size();i++)
			copy[i] = sun.get(i);
		
		double[][] g = BasicKMeans.cluster(copy, 5);
		double[] max = new double [g.length];
		double[] min = new double [g.length];
		
		for(int i = 0;i<g.length;i++) {
			if(BasicKMeans.max(g[i])==-1) {
				max[i] = 0;
				min[i] = 1;
				continue;
			}
			max[i] = g[i][BasicKMeans.max(g[i])];
			min[i] = g[i][BasicKMeans.min(g[i])];
		}
		
		/**turn the value into discrete value**/
		for(int i = 0 ; i<copy.length; i++) {
			for(int j=0; j<g.length; j++) {
				if(copy[i]<=max[j]&&copy[i]>=min[j]) {
					LIST.sundur.add(j);
					break;
				}
			}
		}
	}
	
	public static void tempChange(int region) {
		NormalChange.changeTemp(region);
		
		List<Double> temp = LIST.temperature_normal;
		double[] copy = new double[temp.size()];
		
		for(int i=0;i<temp.size();i++)
			copy[i] = temp.get(i);
		
		double[][] g = BasicKMeans.cluster(copy, 5);
		double[] max = new double [g.length];
		double[] min = new double [g.length];

		
		for(int i = 0;i<g.length;i++) {
			if(BasicKMeans.max(g[i])==-1) {
				max[i] = 0;
				min[i] = 1;
				continue;
			}
			max[i] = g[i][BasicKMeans.max(g[i])];
			min[i] = g[i][BasicKMeans.min(g[i])];
		}
		
		/**turn the value into discrete value**/
		for(int i = 0 ; i<copy.length; i++) {
			for(int j=0; j<g.length; j++) {
				if(copy[i]<=max[j]&&copy[i]>=min[j]) {
					LIST.temperature.add(j);
					break;
				}
			}
		}

	}
	
	public static void windSpeedChange(int region) {
		NormalChange.changeWind(region);
		
		List<Double> wind = LIST.windspeed_normal;
		double[] copy = new double[wind.size()];
		
		for(int i=0;i<wind.size();i++)
			copy[i] = wind.get(i);
		
		double[][] g = BasicKMeans.cluster(copy, 5);
		double[] max = new double [g.length];
		double[] min = new double [g.length];

		for(int i = 0;i<g.length;i++) {
			if(BasicKMeans.max(g[i])==-1) {
				max[i] = 0;
				min[i] = 1;
				continue;
			}
				
			max[i] = g[i][BasicKMeans.max(g[i])];
			min[i] = g[i][BasicKMeans.min(g[i])];
		}
		
		/**turn the value into discrete value**/
		for(int i = 0 ; i<copy.length; i++) {
			for(int j=0; j<g.length; j++) {
				if(copy[i]<=max[j]&&copy[i]>=min[j]) {
					LIST.windspeed.add(j);
					break;
				}
			}
		}

	}
	
	public static void windDirectionChange(int region) {
		List<Integer> wind = WindClean.clean_direction(region);
		
		for(int i=0;i<wind.size();i++) {
			LIST.winddirection.add(wind.get(i));
		}

	}
	
	public static void precpChange(int region) {	
		
		NormalChange.changePrecp(region);
		
		List<Double> precp = LIST.precipitation_normal;
		List<Double> precpnew = new ArrayList<>();
		
		for(int i=0;i<precp.size();i++) {
			if(precp.get(i)==0) continue;
			precpnew.add(precp.get(i));
		}
		
		double[] precpcopy = new double[precpnew.size()];
		
		
		for(int i=0;i<precpnew.size();i++) {	
			precpcopy[i] = (double)precpnew.get(i);
		}
		
		double[][] g = BasicKMeans.cluster(precpcopy, 4);
		double[] max = new double [g.length];
		double[] min = new double [g.length];
		
	
		for(int i = 0;i<g.length;i++) {
			if(BasicKMeans.max(g[i])==-1) {
				max[i] = 0;
				min[i] = 1;
				continue;
			}
			max[i] = g[i][BasicKMeans.max(g[i])];
			min[i] = g[i][BasicKMeans.min(g[i])];
		}
		
		/**turn the value into discrete value**/
		for(int i=0;i<precp.size();i++) {
			
			if(precp.get(i)==0) {
				LIST.precipitation.add(0);
				continue;
			}
			for(int j=0; j<max.length; j++) {
				
				if(precp.get(i)<=max[j]&&precp.get(i)>=min[j]) {
					LIST.precipitation.add(j+1);
					break;
				}
			}
		}
		

	}
	
}
