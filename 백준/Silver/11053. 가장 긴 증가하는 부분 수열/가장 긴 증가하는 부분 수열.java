

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int[] arr = new int[n + 1];
		int[] dp = new int[n + 1];

		for (int i = 1; i < n + 1; i++) {
			arr[i] = sc.nextInt();
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}
		}

//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(dp));

		int ans = 0;
		for (int ele : dp) {
			ans = Math.max(ans, ele);
		}

		System.out.println(ans);

	}

}
