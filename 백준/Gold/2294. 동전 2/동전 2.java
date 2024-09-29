

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();

		int[] arr = new int[n];
		int[] dp = new int[k + 1];

		Arrays.fill(dp, 100001);

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		for (int i = 0; i < n; i++) {
			if (arr[i] <= k) {
				dp[arr[i]] = 1;
			}
		}

		for (int i = 1; i < k + 1; i++) {
			for (int j = 0; j < n; j++) {
				if (i >= arr[j]) {
					dp[i] = Math.min(dp[i], dp[i - arr[j]] + 1);
				}
			}

		}

//		System.out.println(Arrays.toString(dp));
		if (dp[k] != 100001) {
			System.out.println(dp[k]);
		} else {
			System.out.println(-1);
		}

	}

}
