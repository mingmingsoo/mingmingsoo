

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		int tt = 1;
		while(tt<=t) {
			
			n = sc.nextInt();
			grid = new int[n][n];
			visited = new boolean[n][n];

			int min = 101;
			int max = 0;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int tmp = sc.nextInt();
					grid[i][j] = tmp;
					if (tmp > max) {
						max = tmp;
					}
					if (tmp < min) {
						min = tmp;
					}
				}
			}

			int ans = 1;
			for (int k = min; k < max; k++) {
				visited = new boolean[n][n];
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (grid[i][j] <= k) {
							visited[i][j] = true;
						}
					}
				}
				int cnt = 0;
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (!visited[i][j]) {
							dfs(i, j);
							cnt++;
						}
					}
				}
				ans = Math.max(ans, cnt);
			}
			System.out.println("#"+tt+" "+ans);
			tt++;
		}


	}

	static int[][] grid;
	static boolean[][] visited;
	static int n;
	static int[] row = { 1, -1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };

	private static void dfs(int r, int c) {
		visited[r][c] = true;

		for (int k = 0; k < 4; k++) {
			int nr = r + row[k];
			int nc = c + col[k];
			if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
				dfs(nr, nc);
				visited[nr][nc] = true;
			}
		}

	}

}
