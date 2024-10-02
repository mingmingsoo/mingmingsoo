
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// 말은 2*1 8방으로 이동 가능
		// 원숭이는 사방 가능
		// 원숭이는 k번만 말처럼 이동 가능
		// 0,0 에서 시작해서 n-1, m-1 까지
		// 최소한의 동작으로 도착하는 법은??

		Scanner sc = new Scanner(System.in);

		limit = sc.nextInt();
		m = sc.nextInt();
		n = sc.nextInt();

		grid = new int[n][m];
		visited = new boolean[n][m][limit + 1];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		ans = Integer.MAX_VALUE;
		bfs(0, 0, 0, 0); // 현재 위치 0,0 이동 횟수 0, 쩜푸횟수
		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(ans);
	}

	static int ans;
	static boolean[][][] visited;
	static int[] row = { -1, 1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };
	static int[] row_jump = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] col_jump = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int n;
	static int m;
	static int limit;
	static int[][] grid;

	private static void bfs(int r, int c, int count, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c, count, j }); // 쩜푸 안했음
		visited[r][c][0] = true;

		while (!q.isEmpty()) {
			int[] node = q.poll();
			int rr = node[0];
			int cc = node[1];
			int cnt = node[2];
			int jump = node[3];
			if (rr == n - 1 && cc == m - 1) {
				ans = Math.min(cnt, ans);
				return;
			}

			if (jump < limit) {
				for (int k = 0; k < 8; k++) {
					int nr = rr + row_jump[k];
					int nc = cc + col_jump[k];
					if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
						if (!visited[nr][nc][jump + 1] && grid[nr][nc] == 0) {
							visited[nr][nc][jump + 1] = true;
							q.add(new int[] { nr, nc, cnt + 1, jump + 1 });
						}
					}
				}
			}
			for (int k = 0; k < 4; k++) {
				int nr = rr + row[k];
				int nc = cc + col[k];
				if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
					if (!visited[nr][nc][jump] && grid[nr][nc] == 0) {
						visited[nr][nc][jump] = true;
						q.add(new int[] { nr, nc, cnt + 1, jump });
					}
				}

			}
		}

	}

}
