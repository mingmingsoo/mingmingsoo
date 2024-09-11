

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		grid = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = sc.nextInt();
			}
		}

		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] & grid[i][j] != 0)
					bfs(i, j);
			}
		}
		System.out.println(cnt);

	}

	static int n;
	static int m;
	static int[][] grid;
	static boolean[][] visited;
	static int[] row = { -1, 1, 0, 0, 1, 1, -1, -1 };
	static int[] col = { 0, 0, 1, -1, 1, -1, -1, 1 };
	static int cnt;

	private static void bfs(int r, int c) {

		visited[r][c] = true;

		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c });

		boolean bool = true;
		while (!q.isEmpty()) {
			int[] node = q.poll();
			int x = node[0];
			int y = node[1];
			for (int d = 0; d < 8; d++) {
				int nx = x + row[d];
				int ny = y + col[d];
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] > grid[x][y]) {
					bool = false;
				}
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && grid[nx][ny] == grid[x][y]) {
					visited[nx][ny] = true;
					q.add(new int[] { nx, ny });
				}
			}

		}
		if (bool) {
			cnt++;
//			System.out.println(cnt);
		}

	}

}
