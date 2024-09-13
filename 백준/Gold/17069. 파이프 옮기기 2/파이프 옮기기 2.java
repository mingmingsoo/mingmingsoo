
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

		long[][][] dp = new long[n][n][3]; // 0 : 가로, 1: 세로, 2: 대각선
		// 가로로 올 수 있는 경우의 수 + 세로로 올 수 있는 경우의 수 + 대각선으로 올 수 있는 경우의 수를 더할거임

		// 처음 파이프
		dp[0][1][0] = 1; // 가로로 놓여져있음

		for (int j = 2; j < n; j++) {
			if (grid[0][j] == 0)
				dp[0][j][0] = dp[0][j - 1][0]; // 첫줄은 가로만 가능.
		}

		for (int i = 1; i < n; i++) {
			for (int j = 2; j < n; j++) {
				if (grid[i][j] != 0) {
					continue; // 벽만나면 넘어가기
				}

				// 가로 경우의 수
				dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][2]; // 가로로 올 때, 대각선으로 올 때
				// 세로 경우의 수
				dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2]; // 세로로 올 때, 대각선으로 올 때
				// 대각선 경우의 수
				if (grid[i - 1][j] == 0 && grid[i][j - 1] == 0) {
					dp[i][j][2] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
				}

			}
		}
		System.out.println(dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2]);
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(dp[i][j][0] + " ");
//			}
//			System.out.println();
//		}
//
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

	}

}
