

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int n;
	static int m;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {

			n = sc.nextInt();
			m = sc.nextInt();
			
			if(n==0&&m==0) {
				return;
			}

			int[][] grid = new int[m][n];

			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					grid[i][j] = sc.nextInt();
				}
			}
			
			int cnt = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j] == 1) {
						cnt++;
						dfs(grid, i, j);
					}
				}
			}
			System.out.println(cnt);
		}
	}

	private static void dfs(int[][] grid, int i, int j) {

		grid[i][j] = 0;

		int[] row = { 1, -1, 0, 0, 1, 1, -1, -1 };
		int[] col = { 0, 0, 1, -1, 1, -1, 1, -1 };

		for (int k = 0; k < 8; k++) {
			int nr = i + row[k];
			int nc = j + col[k];

			if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
				dfs(grid, nr, nc);
			}
		}

	}

}
