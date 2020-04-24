package pers.pre.change;

public class BasicKMeans {
   
    /**
     * classify the double array into k kinds
     * @param p a double array needed to be clustered
     * @param k the number of total kinds
     * @return a two dimensional array that have been classified
     */
    public static double[][] cluster(double[] p, int k) {
        /**store the old center**/
        double[] c = new double[k];
        
        /**store the new center**/
        double[] nc = new double[k];
        
        /**store the return result**/
        double[][] g;
        /**
         * initialize the center with the first k point in p
         **/
        for (int i = 0; i < k; i++)
            c[i] = p[i];
        /**loop until center doesn't change any more**/
        while (true) {
            /**classify according to the center**/
            g = group(p, c);
            /**calculate the new center again**/
            for (int i = 0; i < g.length; i++) {
                nc[i] = center(g[i]);
            }
            /**if the center did not match**/
            if (!equal(nc, c)) {
                /**update the center**/
                c = nc;
                nc = new double[k];
            } else /**if center match, then end of loop**/
                break;
        }
        
        for(int i=0;i<g.length;i++) {
        	for(int j = 0;j<g.length - 1 - i; j++) {
        		/**if g[j]>g[j+1] then exchange**/
        		if(!compare(g[j],g[j+1])) {
        			exch(g,j,j+1);
        		}
        	}
        }
        
        return g;
    }
    /**
     * calculate the center of group (the average of a group)
     * @param p a double array
     * @return the center
     */
    public static double center(double[] p) {
    	/**if the length equal to 0 than the center become 0**/
        return p.length==0 ? 0:sum(p) / p.length;
    }
    
    /**
     * group the array p according to the center c
     * @param p the double array p waited to be grouped
     * @param c the center array store the center
     * @return return the group result
     */
    public static double[][] group(double[] p, double[] c) {
    	
        int[] gi = new int[p.length];
        /**
         * calculate the distance between p[i] and c[j]
         * group the shortest one into corresponding group
         **/
        for (int i = 0; i < p.length; i++) {
            /**store the distance**/
            double[] d = new double[c.length];
            /**calculate the distance of each point in p to the center**/
            for (int j = 0; j < c.length; j++) {
                d[j] = distance(p[i], c[j]);
            }
            /**find the smallest one and store the index**/
            int ci = min(d);
            gi[i] = ci;
        }
        double[][] g = new double[c.length][];
        
        for (int i = 0; i < c.length; i++) {
            int s = 0;
            for (int j = 0; j < gi.length; j++)
                if (gi[j] == i)
                    s++;
            g[i] = new double[s];
            s = 0;
            /**store point in p into corresponding group**/
            for (int j = 0; j < gi.length; j++)
                if (gi[j] == i) {
                    g[i][s] = p[j];
                    s++;
                }
        }
        return g;
    }
 
    /**
     * calculate the distance between x and y
     * @param x the first point
     * @param y the second point
     * @return the distance between x and y
     */
    public static double distance(double x, double y) {
        return Math.abs(x - y);
    }
 
   /**
    * the sum in double array p
    * @param p a double array
    * @return the sum of the array
    */
    public static double sum(double[] p) {
        double sum = 0.0;
        for (int i = 0; i < p.length; i++)
            sum += p[i];
        return sum;
    }
 
   /**
    * given the array p find the minimum point
    * @param p
    * @return the index of the minimum point
    */
    public static int min(double[] p) {
    	if(p.length==0)
    		return -1;
        int i = 0;
        double m = p[0];
        for (int j = 1; j < p.length; j++) {
            if (p[j] < m) {
                i = j;
                m = p[j];
            }
        }
        return i;
    }
    
    /**
     * given the array p find the minimum point
     * @param p
     * @return the index of the minimum point
     */
     public static int max(double[] p) {
    	 if(p.length==0)
    		 return -1;
         int i = 0;
         double m = p[0];
         for (int j = 1; j < p.length; j++) {
             if (p[j] > m) {
                 i = j;
                 m = p[j];
             }
         }
         return i;
     }
     
 
   /**
    * judge whether the two array are equal
    * @param a
    * @param b 
    * @return true: two array are equal; flase: two array are not equal
    */
    public static boolean equal(double[] a, double[] b) {
        if (a.length != b.length)
            return false;
        else {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i])
                    return false;
            }
        }
        return true;
    }
    
    /**
     * compare two double array, if the min of array a greater than min of array b
     * @param a double array a
     * @param b double array b
     * @return
     */
    public static boolean compare(double[] a,double[] b) {
    	if(a.length==0)
    		return true;
    	else if(b.length==0)
    		return false;
    	else if(a[min(a)]>b[min(b)])
    		return false;
    	
    	return true;
    }
    
    /**
     * exchange in two dimensional double array a between index of i and j
     * @param a two dimensional double array
     * @param i index of i
     * @param j index of j
     */
    public static void exch(double[][] a, int i, int j) {
    	double[] sub = new double[a[i].length];
    	sub = a[i];
    	a[i] = a[j];
    	a[j] = sub;
    }
    
    
}
