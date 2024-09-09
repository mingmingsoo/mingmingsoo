
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		// 안움직여도 되므로 최대 어디까지 갈 수 있는지를 세면 됨. 방문체크 초기화할 필요도 없음!

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			n = sc.nextInt();
			m = sc.nextInt();
			int r = sc.nextInt();
			int c = sc.nextInt();
			limit = sc.nextInt();

			grid = new int[n][m];
			visited = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					grid[i][j] = sc.nextInt();
				}
			}

			ans = 0;
			bfs(r, c, 1);
			System.out.println("#" + tt++ + " " + ans);
		}

	}

	static int ans;
	static int n;
	static int m;
	static int limit;
	static int[][] grid;
	static boolean[][] visited;

	private static void bfs(int r, int c, int t) {
		Queue<int[]> q = new LinkedList<>();
		visited[r][c] = true;
		q.add(new int[] { r, c, t });

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] node = q.poll();
				int rnode = node[0];
				int cnode = node[1];
				int time = node[2];

				if (time > limit) {
					return;
				}
				ans++;

				for (int[] dir : getdir(rnode, cnode)) {
					int nr = rnode + dir[0];
					int nc = cnode + dir[1];
					if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && connected(dir, nr, nc)) {
						visited[nr][nc] = true;
						q.add(new int[] { nr, nc, time + 1 });
					}
				}

			}
		}

	}

	private static boolean connected(int[] dir, int nr, int nc) {
		if (dir[0] == 0 && dir[1] == 1) {
			return grid[nr][nc] == 1 || grid[nr][nc] == 3 || grid[nr][nc] == 6 || grid[nr][nc] == 7;
		} else if (dir[0] == 0 && dir[1] == -1) {
			return grid[nr][nc] == 1 || grid[nr][nc] == 3 || grid[nr][nc] == 4 || grid[nr][nc] == 5;
		} else if (dir[0] == -1 && dir[1] == 0) {
			return grid[nr][nc] == 1 || grid[nr][nc] == 2 || grid[nr][nc] == 5 || grid[nr][nc] == 6;
		} else if (dir[0] == 1 && dir[1] == 0) {
			return grid[nr][nc] == 1 || grid[nr][nc] == 2 || grid[nr][nc] == 4 || grid[nr][nc] == 7;
		}
		return false;

	}

	private static int[][] getdir(int r, int c) {
		switch (grid[r][c]) {
		case 1:
			return new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
		case 2:
			return new int[][] { { -1, 0 }, { 1, 0 } };
		case 3:
			return new int[][] { { 0, 1 }, { 0, -1 } };
		case 4:
			return new int[][] { { -1, 0 }, { 0, 1 } };
		case 5:
			return new int[][] { { 1, 0 }, { 0, 1 } };
		case 6:
			return new int[][] { { 1, 0 }, { 0, -1 } };
		case 7:
			return new int[][] { { -1, 0 }, { 0, -1 } };
		default:
			return new int[][] { { 0, 0 } };

		}
	}
}
