
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		int t = sc.nextInt(); // 쓰레기 갯수
		grid = new int[n][m];

		for (int i = 0; i < t; i++) {
			grid[sc.nextInt() - 1][sc.nextInt() - 1] = 1;
		}

		visited = new boolean[n][m];
		int ans = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1 && !visited[i][j]) {
					int dist = bfs(i, j);
					ans = Math.max(ans, dist);
				}
			}
		}
		System.out.println(ans);

	}

	static boolean[][] visited;
	static int[][] grid;
	static int n;
	static int m;
	static int[] row = { -1, 1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };

	private static int bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c });

		visited[r][c] = true;
		int cnt = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] node = q.poll();
				int rr = node[0];
				int cc = node[1];
				cnt++;

				for (int k = 0; k < 4; k++) {
					int nr = rr + row[k];
					int nc = cc + col[k];
					if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && grid[nr][nc] == 1) {
						q.add(new int[] { nr, nc });
						visited[nr][nc] = true;
					}
				}
			}
		}
		return cnt;

	}

}
