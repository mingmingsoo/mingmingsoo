
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		int tt = 1;
		
		while(tt<=t) {
			n = sc.nextInt();
			grid = new int[n][n];

			int max = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					grid[i][j] = sc.nextInt();
					if (grid[i][j] > max) {
						max = grid[i][j];
					}
				}
			}
			int ans = 1;
			int day = 1;
			while (day < max) {
				visited = new boolean[n][n];
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (grid[i][j] <= day) {
							grid[i][j] = 0; // 먹힘처리
						}
					}
				}
				int cnt = 0;
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (!visited[i][j] && grid[i][j] != 0) {
							bfs(i, j);
							cnt++;
						}
					}
				}
				ans = Math.max(ans, cnt);
				day++;
			}
			System.out.println("#"+tt+" "+ans);
			tt++;
		}


	}

	static int[] row = { -1, 1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };

	private static void bfs(int r, int c) {
		visited[r][c] = true;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c });

		while (!q.isEmpty()) {
			int[] node = q.poll();
			int rr = node[0];
			int cc = node[1];
			for (int k = 0; k < 4; k++) {
				int nr = rr + row[k];
				int nc = cc + col[k];
				if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] != 0 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}

	}

	static boolean visited[][];
	static int grid[][];
	static int n;

}
