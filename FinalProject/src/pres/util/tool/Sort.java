package pres.util.tool;

import java.util.Collections;
import java.util.List;

import pers.util.association.FP_growth;

public class Sort {
	
	public static void bubbleSort(List<String> sort) {

		for(int i = 0; i < sort.size(); i ++) {
			for(int j = 0; j<sort.size()-i-1;j++) {
				if(FP_growth.freqMap.get(sort.get(j))<FP_growth.freqMap.get(sort.get(j+1))) 
					Collections.swap(sort, j, j+1);
			}
		}
	}
	
	public static void quickSort(List<String> sort,int left,int right) {
		int pivot_dx;
		if(left<right) {
			pivot_dx = partition(sort,left,right);
			quickSort(sort,left,pivot_dx-1);
			quickSort(sort,pivot_dx+1,right);
		}
	}
	
	public static int partition(List<String> sort,int left,int right) {
		int x = FP_growth.freqMap.get(sort.get(right));
		int i = left, j =right-1;
		while(i<j) {
			while(FP_growth.freqMap.get(sort.get(i))>x) i++;
			while(FP_growth.freqMap.get(sort.get(j))<=x && j>left) j--;
			
			if(i<j) Collections.swap(sort, i, j);
		}
		
		Collections.swap(sort, right, i);
		return i;
	}
	
	public static void quickSort(double[] sort, int left,int right){
		
		int pivot_dx;
		if(left<right) {
			pivot_dx = partition(sort,left,right);
			quickSort(sort,left,pivot_dx-1);
			quickSort(sort,pivot_dx+1,right);
		}
	}
	
	public static int partition(double[] sort,int left,int right) {
		double x = sort[right];
		int i = left; int j = right - 1;
		while(i<j) {
			while(sort[i]<x) i++;
			while(sort[j]>=x && j>left) j--;
			
			if(i<j) exch(sort,i,j);
		}
		
		exch(sort,right,i);
		return i;
	}
	
	public static void exch(double[] sort,int i, int j) {
		double ex = sort[i];
		sort[i]=sort[j];
		sort[j]=ex;
	}
	

}
