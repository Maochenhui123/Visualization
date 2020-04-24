package pers.pre.change;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author mch
 * KMeans algorithm for multidimensional array
 * 
 */

public class KMeans {
	
	private static int length = 0;
	
	 /**
     * classify the double array into k kinds
     * the input type should be x:[[],[],[],[],[]], and output the category
     * @param p a double array needed to be clustered
     * @param k the number of total kinds
     * @return output the category for each element in input two dimensional array
     */
	public static int[] cluster(double[][] x,int k){
		length = x[0].length;
		
		/**store the old center**/
		double[][] c = new double[k][length];
		/**store the new center**/
		double[][] nc = new double[k][length];
		
		/**store the return result**/
		double[][][] g = null;
		/**randomally distributed the center with the element in x**/
		for(int i = 0; i < k; i++) 
			c[i]=x[i+3];
		
		int index = 1;
		/**loop 1000 times until center doesn't change any more**/
		while(index < 5000) {
			orderCenter(c);
			/**classify according to the center**/
			g = group(x,c);
			
			/**calculate the new center again**/
			nc = center(g);
			
			/**if the old center is not equal to the new one than iterate again**/
			if(!equal(c,nc)) {
				/**change the old center into new one**/
				c = nc;
				nc = new double[k][length];
			}else break;
			
			index++;
			
		}
			
		return map(g,x);
	}
	
	/**
     * group the array x according to the center c, 
     * use the distance between each elements to the center point
     * @param x the array x waited to be grouped
     * @param c the center array store the center
     * @return return the group result
     */
	public static double[][][] group(double[][] x,double[][] center){
		
		/**distance for each element in the x to the center**/
		double[] d = new double[center.length];
		
		/**group result**/
		double[] gi = new double[x.length];
		
		for(int i=0;i<x.length;i++) {
			/**calculate the distance between an element to each center**/
			for(int j=0;j<d.length;j++) {
				d[j] = distance(x[i],center[j]);
			}
			/**Find the minimum distance**/
			int index = Min(d);
			gi[i] = index;
		}
		
		/**grouping results in three dimensional array according to the grouping results of gi**/
		double[][][] g = new double[center.length][][];
		
		for(int i = 0;i < center.length; i++) {
			
			/**the length for each group**/
			int s = 0;
			
			for(int j = 0;j < gi.length; j++) {
				if(gi[j] == i) s++;
			}
			/**distributed space according to the length of each group**/
			g[i] = new double[s][length];
			
			s=0;
			
			/**Assign element to the group g**/
			for(int j = 0; j < gi.length; j++) {
				if(gi[j] == i) {
					g[i][s] = x[j];
					s++;
				}
			}
		}
		
		return g;
	}
	
	public static double distance(double[] xi,double[] xj) {
		double d = 0;
		for(int i=0;i<xi.length;i++) {
			d+=(xi[i]-xj[i])*(xi[i]-xj[i]);
		}
		return Math.sqrt(d);
	}
	
	public static double[][] center(double[][][] p){
		double[][] c = new double[p.length][length];
		for(int i=0;i<p.length;i++) {
			c[i] = center(p[i]);
		}
		return c;
	}
	
	public static double[] center(double[][] p) {
		Map<Double,Double> map = new HashMap<>();
		double[] cen = new double[length];
		for(int i=0;i<p[0].length;i++) {
			for(int j=0;j<p.length;j++) {
				if(map.containsKey(p[j][i])) map.put(p[j][i], map.get(p[j][i])+1);
				else map.put(p[j][i], (double)1);
			}
			cen[i] = Mode(map);
			map.clear();
		}
		return cen;
	}
	
	public static void orderCenter(double[][] center){
		double[] sum = new double[center.length];
		for(int i=0;i<center.length;i++) {
			sum[i] = 0;
			for(int j=0;j<center[i].length;j++) {
				sum[i]+=center[i][j];
			}
		}
		double exch = 0;
		double[] ex = new double[center[0].length];
		for(int i=0;i<sum.length;i++) {
			for(int j=0;j<sum.length-i-1;j++) {
				if(sum[j]>sum[j+1]) {
					exch = sum[j];
					sum[j] = sum[j+1];
					sum[j+1] = exch;
					ex = center[j];
					center[j] = center[j+1];
					center[j+1] = ex;
				}
			}
		}
	}
	
	public static double Mode(Map<Double,Double> map) {
		double max = 0;
		double key = 0;
		for(Map.Entry<Double, Double> m:map.entrySet()) {
			if(m.getValue()>max) {
				max = m.getValue();
				key = m.getKey();
			}
		}
		return key;
	}
	
	public static int Min(double[] x) {
		double min = 999;
		int index = 0;
		for(int i = 0; i < x.length; i++) {
			if(min>x[i]) {
				min = x[i];
				index = i;
			}
		}
		return index;
	}
	
	public static boolean equal(double[][] c,double[][] nc) {
		if(c.length!=nc.length) return false;
		else {
			for(int i=0;i<c.length;i++) {
				if(c[i].length!=nc[i].length) return false;
				else {
					for(int j=0;j<c[i].length;j++) {
						if(c[i][j]!=nc[i][j])
							return false;
					}
				}
			}
		}
		return true;
	}
	
	public static int[] map(double[][][] g, double[][] x) {
		int[] res = new int[x.length];
		boolean flag = false;
		for(int i=0;i<x.length;i++) {
			
			for(int m=0;m<g.length;m++) {
				
				for(int n=0;n<g[m].length;n++) {
					flag = false;
					if(BasicKMeans.equal(x[i], g[m][n])) {
						flag = true;
						res[i] = m;
						break;
					}
				}
				if(flag == true)
					break;
				
			}
		}
		return res;
	}
	
	public static void All(List<List<Double>> norm) {
		double[][] x = new double[norm.get(0).size()][];
		
		for(int i=0;i<norm.get(0).size();i++) {
			
			double[] s=new double[norm.size()];
			
			for(int j=0;j<norm.size();j++) {
				s[j] = norm.get(j).get(i);
			}
			x[i]=s;
		}
		int[] c = cluster(x,5);
		for(int i=0;i<c.length;i++) {
			LIST.integration.add(c[i]);
		}
	}

	
}
