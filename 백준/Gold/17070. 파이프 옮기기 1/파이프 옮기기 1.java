
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		grid = new int[n][n];
		visited = new boolean[n][n];

		int[] tmprow = { 0, 0, 1, -1 };
		int[] tmpcol = { 1, -1, 0, 0 };

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = sc.nextInt();
				if (grid[i][j] == 1) {
					visited[i][j] = true; // 벽 못가게 true 처리.
				}
			}
		}

		dfs(0, 0, 0, 1);
		System.out.println(cnt);

	}

	static int n;
	static int[][] grid;
	static boolean[][] visited;
	static int[] row = { 0, 1, 1 };
	static int[] col = { 1, 1, 0 };
	static int cnt;

	private static void dfs(int r1, int c1, int r2, int c2) {
		// 위치가 가로일 때는 방향 0, 1 만 가능
		// 위치가 세로일 때는 방향 1, 2 만 가능
		// 위치가 대각선 일때는 방향 0, 1, 2 가능

		if (r2 == n - 1 && c2 == n - 1) { // 마지막 지점에 도달했으면 경우의 수 +1
			cnt++;
			return;
		}

		visited[r1][c1] = true;
		visited[r2][c2] = true;

		// 가로로 위치할 때
		if (r1 == r2 && c2 - c1 == 1) {
			for (int k = 0; k < 2; k++) {
				int nr1 = r2;
				int nc1 = c2;
				int nr2 = r2 + row[k];
				int nc2 = c2 + col[k];

				if (nr2 >= 0 && nr2 < n && nc2 >= 0 && nc2 < n && !visited[nr2][nc2]) {
					if (k == 1 && (grid[nr2 - 1][nc2] == 1 || grid[nr2][nc2 - 1] == 1)) {
						continue;
					}
					visited[r2][c2] = true;
					dfs(nr1, nc1, nr2, nc2);
				}
			}
		}

		// 세로로 위치할 때
		if (r2 - r1 == 1 && c1 == c2) {
			for (int k = 1; k < 3; k++) {
				int nr1 = r2;
				int nc1 = c2;
				int nr2 = r2 + row[k];
				int nc2 = c2 + col[k];

				if (nr2 >= 0 && nr2 < n && nc2 >= 0 && nc2 < n && !visited[nr2][nc2]) {
					if (k == 1 && (grid[nr2 - 1][nc2] == 1 || grid[nr2][nc2 - 1] == 1)) {
						continue;
					}
					visited[r2][c2] = true;
					dfs(nr1, nc1, nr2, nc2);
				}
			}
		}

		// 대각선으로 위치할 때
		if (r2 - r1 == 1 && c2 - c1 == 1) {
			for (int k = 0; k < 3; k++) {
				int nr1 = r2;
				int nc1 = c2;
				int nr2 = r2 + row[k];
				int nc2 = c2 + col[k];

				if (nr2 >= 0 && nr2 < n && nc2 >= 0 && nc2 < n && !visited[nr2][nc2]) {
					if (k == 1 && (grid[nr2 - 1][nc2] == 1 || grid[nr2][nc2 - 1] == 1)) {
						continue;
					}
					visited[r2][c2] = true;
					dfs(nr1, nc1, nr2, nc2);
				}
			}

		}

		visited[r1][c1] = false;
		visited[r2][c2] = false;

	}

	private static void check(int r2, int c2, int k) {
		int nr1 = r2;
		int nc1 = c2;
		int nr2 = r2 + row[k];
		int nc2 = c2 + col[k];

		if (nr2 >= 0 && nr2 < n && nc2 >= 0 && nc2 < n && !visited[nr2][nc2]) {
			if (k == 1 && (grid[nr2 - 1][nc2] == 1 || grid[nr2][nc2 - 1] == 1)) {
				return;
			}
			visited[r2][c2] = true;
			dfs(nr1, nc1, nr2, nc2);
		}

	}

}
