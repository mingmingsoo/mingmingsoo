

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int[][] grid = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = sc.nextInt();
			}
		}

		// dp 를 사용해서...
		// 0 : 가로, 1: 세로, 2: 대각선
		int[][][] dp = new int[n][n][3];
		dp[0][1][0] = 1;

		for (int j = 2; j < n; j++) {
			if (grid[0][j] == 0) { // 첫 줄은 가로로 밖에 못감!
				dp[0][j][0] = dp[0][j - 1][0];
			}
		}

		for (int i = 1; i < n; i++) {
			for (int j = 2; j < n; j++) {
				if (grid[i][j] != 0) {
					continue;
				}

				dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][2]; // 가로로 올 수 있는 경우의 수는 가로 + 대각선
				dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2]; // 세로로 올 수 있는 경우의 수는 세로 + 대각선

				if (grid[i - 1][j] == 0 && grid[i][j - 1] == 0) {
					dp[i][j][2] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
				} // 대각선으로 올 수 있는 경우의 수는 가 + 세 + 대

			}
		}
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(dp[i][j][0] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(dp[i][j][1] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(dp[i][j][2] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		System.out.println(dp[n-1][n-1][0]+dp[n-1][n-1][1]+dp[n-1][n-1][2]);

	}

}
