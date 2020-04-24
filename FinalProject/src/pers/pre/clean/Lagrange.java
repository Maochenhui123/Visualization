package pers.pre.clean;


import java.util.List;

/**
 * apply Lagrange to insert into value where the value is missing
 * @author mch
 *
 */
public class Lagrange {
	
	/**
	 * Apply the lagrange interpolation given with the x and y list and the index of insert
	 * @param x: the X list
	 * @param y: the Y list
	 * @param insert: the index needed to be inserted
	 * @return: return the result
	 */
	public static double lagrange(List<Integer> x,List<Integer> y, int insert) {
		double p = 0;
			
		double l = 0;
		for(int j = 0;j<x.size();j++) {
			l = (double)y.get(j);
			for(int k = 0; k<x.size();k++) {
				if(k==j)
					continue;
				l*=(double)(insert-x.get(k))/(double)(x.get(j)-x.get(k));
					
			}
			p+=l;
		}
		
		return p;
	}
	
	/***
	 * an updated lagrange interpolation strategies, by setting the weight
	 * @param x: the X list
	 * @param y: the Y list
	 * @param insert: the index needed to be inserted
	 * @return: return the result
	 */
	public static double upgrade_lagrange(List<Integer> x,List<Integer> y, int insert) {
		
		double yj = 0;
		double dividend = 0;
		double divisor = 0;
		for(int j=0; j< x.size();j++) {
			yj = y.get(j);
			double wj = 1;
			for(int i=0; i < x.size(); i++) {
				if(i==j)
					continue;
				wj *= (double)1/(x.get(j)-x.get(i));
			}
			divisor += wj*yj/(insert - x.get(j));
			dividend += wj/(insert - x.get(j));
		}
		double result = divisor/dividend;
		return result;
	}
	
	public static void showList(List<?> x) {
		for(Object i:x) {
			System.out.println(i);
		}
	}
	
//	public static void main(String[] args) {
//		List<Integer> x = new ArrayList<>();
//		List<Integer> y = new ArrayList<>();
//		x.add(1);x.add(2);x.add(3);
//		y.add(4);y.add(9);y.add(16);
//		System.out.println(upgrade_lagrange(x,y,18));
//	}


	
}
