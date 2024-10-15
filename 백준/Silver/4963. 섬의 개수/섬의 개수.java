
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {
			m = sc.nextInt();
			n = sc.nextInt();
			if (m == 0 && n == 0) {
				return;
			}
			grid = new int[n][m];
			visited = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					grid[i][j] = sc.nextInt();
				}
			}
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (grid[i][j] == 1 && !visited[i][j]) {
						cnt++;
						dfs(i, j);
					}
				}
			}
			System.out.println(cnt);
		}

	}

	static int[] row = { -1, 1, 0, 0, 1, 1, -1, -1 };
	static int[] col = { 0, 0, 1, -1, 1, -1, 1, -1 };

	static boolean[][] visited;
	static int[][] grid;
	static int n;
	static int m;

	private static void dfs(int r, int c) {
		visited[r][c] = true;

		for (int k = 0; k < 8; k++) {
			int nr = r + row[k];
			int nc = c + col[k];
			if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && grid[nr][nc] == 1) {
				dfs(nr, nc);
			}
		}
	}
}
