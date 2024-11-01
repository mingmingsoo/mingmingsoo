

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
//		System.out.println(Arrays.toString(arr));

		int curSum = 0;

		for (int i = 0; i < k; i++) {
			curSum += arr[i];
		}

		int maxSum = curSum;
//		System.out.println(curSum);
		// 
		// (3 -2 -4) -9 0 3 7 13 8 -3
		int left = 0;
		for (int i = k; i < n; i++) {
			curSum +=arr[i];
			curSum -= arr[left++];
//			System.out.println(curSum);
			if(maxSum<curSum) {
				maxSum = curSum;
			}
		}
		System.out.println(maxSum);
	}

}
