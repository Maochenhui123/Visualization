package pres.util.tool;

public class Processingstr {
	public static int[] strToint(String[] arr) {
		int[] list = new int[arr.length];
		for(int i=0;i<arr.length;i++) {
			list[i] = Integer.valueOf(arr[i]);
			System.out.println(list[i]);
		}
		return list;
	}

}
