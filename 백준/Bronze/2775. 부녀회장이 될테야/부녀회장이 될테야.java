

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int k = Integer.parseInt(br.readLine());
			int n = Integer.parseInt(br.readLine());
			int[][] dp = new int[k + 1][n + 1];
			for (int j = 1; j <= n; j++) {
				dp[0][j] = j;
			}

			for (int i = 1; i <= k; i++) {
				for (int j = 1; j <= n; j++) {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
			sb.append(dp[k][n]).append("\n");
		}
		System.out.println(sb);

	}
}
