package pers.pre.change;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import Jama.Matrix;


public class PCA {

    private static final double threshold = 0.90;

    /**
     * 
     * To change every item in array to the one that has been minus with average value
     * which means newXi = Xi - averagevalue
     * 
     * @param primary the input array that want to implement the operation
     * @return averageArray: the array that has been minus with the average value
     */
    public double[][] changeAverageToZero(double[][] primary) {
        int n = primary.length;
        int m = primary[0].length;
        /**sum for each rows**/
        double[] sum = new double[m];
        /**average value for each row**/
        double[] average = new double[m];
        double[][] averageArray = new double[n][m];
        
        /**calculate the average value**/
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i] += primary[j][i];
            }
            average[i] = sum[i] / n;
        }
        /**minus average value**/
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                averageArray[j][i] = primary[j][i] - average[i];
            }
        }
        return averageArray;
    }

    /**
     * 
     * covariance matrix 
     * 
     * @param matrix: matrix that has been minus with average value
     *           
     * @return result
     */
    public double[][] getVarianceMatrix(double[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        /**the covariance matrix**/
        double[][] result = new double[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                double temp = 0;
                for (int k = 0; k < n; k++) {
                    temp += matrix[k][i] * matrix[k][j];
                }
                result[i][j] = temp / (n - 1);
            }
        }
        return result;
    }

    /**
     * Eigen value
     * 
     * @param matrix: the input covariance matrix
     *            
     * @return result Eigen value stored in the diagonal of the matrix
     * E.g.
     * 					1 0 0
     * 					0 2 0
     * 					0 0 1
     * so we can find that 1 and 2 is the Eigen value for the input covariance matrix
     */
    public double[][] getEigenvalueMatrix(double[][] matrix) {
        Matrix A = new Matrix(matrix);
        
        /**Built in function to calculate the Eigen value**/
        double[][] result = A.eig().getD().getArray();
        return result;
    }

    /**
     * Eigen vector
     * 
     * @param matrix the input covariance matrix
     *            
     * @return result the Eigen vector for all Eigen value
     * E.g. for Eigen value of 1 and 2
     * 					1 2
     * 					4 3
     * then {1,4} is the Eigen vector for Eigen 1, and {2,3} is the Eigen vector for Eigen 2
     */
    public double[][] getEigenVectorMatrix(double[][] matrix) {
        Matrix A = new Matrix(matrix);
        
        /**Built in function to calculate the Eigen vector**/
        double[][] result = A.eig().getV().getArray();
        return result;
    }
    
    /**
     * Obtain the principal Component based on the Eigen vector and Eigen value
     * Calculate the top N vector that has the Eigen value less than the threshold
     * 
     * @param eigenvalue: the Eigen value for the covariance matrix
     * @param eigenVectors: The Eigen vector for the covariance matrix
     * @return principleMatrix: the principle component in the eigenVector
     */
    public Matrix getPrincipalComponent(double[][] eigenvalue, 
    		double[][] eigenVectors) {
    	
    	/**Convert it into matrix and get the transpose of it so that we can calculate more easily**/
    	Matrix A = new Matrix(eigenVectors);
    	double[][] Vectors = A.transpose().getArray();
    	
    	int len = eigenvalue.length;
    	
    	/**Key is the Eigen value, the Value is the corresponding Eigen vector, followed by the order of Eigen value**/
    	Map<Double,double[]> eigenMap = new TreeMap<>(Comparator.reverseOrder());
    	double total = 0;
    	for(int i=0;i<len;i++) {
    		eigenMap.put(eigenvalue[i][i], Vectors[i]);
    		total+=eigenvalue[i][i];
    	}
    	
//    	int principalComponentNum = 0;
    	List<double[]> pList = new ArrayList<>();
    	double temp = 0;
    	for(Double key:eigenMap.keySet()) {
    		/**Get the vector that has Eigen value less than the threshold**/
    		if(temp/total<threshold) {
    			temp+=key;
//    			principalComponentNum++;
    			pList.add(eigenMap.get(key));
    		}
    	}
    	
    	/**Turn it into principle Array**/
    	double[][] principleArray = new double[pList.size()][];

    	
    	for(int i=0;i<pList.size();i++) {
    		principleArray[i] = pList.get(i);
    	}
    	Matrix principleMatrix = new Matrix(principleArray).transpose();
    	
    	return principleMatrix;
    }

    /**
     * Reduce the primary matrix given with the principal matrix
     * 
     * @param primary: the primary matrix
     * 
     * @param matrix: principal matrix
     * 
     * @return result: the Matrix that has been reduced
     */
    public Matrix getResult(double[][] primary, Matrix matrix) {
        Matrix primaryMatrix = new Matrix(primary);
        Matrix result = primaryMatrix.times(matrix);
//        result.print(6, 3);
        return result;
    }
    
    /**
     * Deal with the incoming List from the preprocessing, and process it into List after reduced
     * @param table: the incoming List
     * @return reducedTable: List after reduced
     */
    public List<List<Double>> reduction(List<List<Double>> table) {
    	int col = table.size();
    	int row = table.get(0).size();
    	
    	/**Turn List into 2-dimensional Array**/
    	double[][] primaryArray = new double[row][col];
    	for(int i=0;i<row;i++) {
    		for(int j=0;j<col;j++) {
    			primaryArray[i][j] = table.get(j).get(i);
    		}
    	}
    	
    	/**Deaverage of the primary Array**/
    	double[][] averageArray = changeAverageToZero(primaryArray);
    	/**Obtain the covariance Matrix of the original matrix**/
        double[][] cov = getVarianceMatrix(averageArray);
        
        /**Eigen value and Eigen vector for the given covariance matrix**/
        double[][] eigenvalueMatrix = getEigenvalueMatrix(cov);
        double[][] eigenvectorMatrix = getEigenVectorMatrix(cov);
        
        /**Get the principal component matrix**/
        Matrix principalMatrix = getPrincipalComponent(eigenvalueMatrix, eigenvectorMatrix);
        double[][] resultMatrix = getResult(primaryArray, principalMatrix).getArray();
        
        List<List<Double>> reducedTable = new ArrayList<>();
        for(int i=0;i<resultMatrix[0].length;i++) {
        	
        	List<Double> tuple = new ArrayList<>();
        	
        	for(int j=0;j<resultMatrix.length;j++) {
        		tuple.add(resultMatrix[j][i]);
        	}
        	reducedTable.add(tuple);
        }
        return reducedTable;
    	
    }
    
//    public static void main(String[] args) {
//    	double[][] primaryArray = {{0.9,1},{2.4,2.6},{1.2,1.7},{0.5,0.7},{0.3,0.7},{1.8,1.4},
//    						{0.5,0.6},{0.3,0.6},{2.5,2.6},{1.3,1.1}};
//    	PCA pca = new PCA();
//
//        double[][] averageArray = pca.changeAverageToZero(primaryArray);
//        double[][] cov = pca.getVarianceMatrix(averageArray);
//        
//        double[][] eigenvalueMatrix = pca.getEigenvalueMatrix(cov);
//        double[][] eigenvectorMatrix = pca.getEigenVectorMatrix(cov);
//        Matrix principalMatrix = pca.getPrincipalComponent(primaryArray, eigenvalueMatrix, eigenvectorMatrix);
//        Matrix resultMatrix = pca.getResult(primaryArray, principalMatrix);
//        
//    }
}
