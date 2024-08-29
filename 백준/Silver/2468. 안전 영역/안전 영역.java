
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();

		int[][] grid = new int[n][n];

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = sc.nextInt();
				max = Math.max(max, grid[i][j]);
				min = Math.min(min, grid[i][j]);
			}
		}

		boolean bool = true;
		int check = grid[0][0];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] != check) {
					bool = false;
				}
			}
		}

		if (!bool) {
			int ans = 0;
			while (min <= max) {
				int[][] copy_grid = new int[n][n];
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						copy_grid[i][j] = grid[i][j];
					}
				}

				int safety = 0;

				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (copy_grid[i][j] <= min) {
							copy_grid[i][j] = 0;
						}
					}
				}

				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (copy_grid[i][j] != 0) {
							dfs(copy_grid, i, j);
							safety++;
						}
					}
				}

				ans = Math.max(ans, safety);
				min++;
			}

			System.out.println(ans);

		}
		else {
			System.out.println(1);
		}

	}

	static int n;

	private static void dfs(int[][] grid, int i, int j) {
		grid[i][j] = 0; // 방문 처리

		int[] row = { 1, -1, 0, 0 };
		int[] col = { 0, 0, 1, -1 };

		for (int k = 0; k < 4; k++) {
			int nr = i + row[k];
			int nc = j + col[k];
			if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] != 0) {
				dfs(grid, nr, nc);
			}
		}

	}

}
