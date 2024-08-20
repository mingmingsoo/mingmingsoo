
import java.util.Scanner;

public class Main {

	static int m;
	static int n;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			m = sc.nextInt(); // 가로
			n = sc.nextInt(); // 세로
			int k = sc.nextInt(); // 배추 갯수

			int[][] grid = new int[n][m];

			for (int i = 0; i < k; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				grid[b][a] = 1;
			}

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(grid[i][j] + " ");
//			}
//			System.out.println();
//		}

			int cnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (grid[i][j] == 1) {
						cnt++;
						dfs(grid, i, j);
					}
				}
			}
			System.out.println(cnt);
			tt++;
		}

	}

	private static void dfs(int[][] grid, int i, int j) {
		int[] row = { 1, -1, 0, 0 };
		int[] col = { 0, 0, 1, -1 };

		grid[i][j] = 0;

		for (int k = 0; k < 4; k++) {
			int nr = i + row[k];
			int nc = j + col[k];

			if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 1) {
				dfs(grid, nr, nc);
			}
		}

	}
}