

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(); // 물건 갯수

		int k = sc.nextInt(); // 가방 최대 용량

		int[] weight = new int[n + 1];
		int[] cost = new int[n + 1];

		for (int i = 1; i < n + 1; i++) {
			weight[i] = sc.nextInt();
			cost[i] = sc.nextInt();
		}

		int[][] dp = new int[n + 1][k + 1];

		for (int i = 1; i < n + 1; i++) {
			for (int j = 0; j < k + 1; j++) {
				if (weight[i] <= j) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + cost[i]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}

			}

		}
		System.out.println(dp[n][k]);

	}

}
