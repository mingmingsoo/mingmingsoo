
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		// 가장자리에 있는 core 들은 탐색 불필요 : -1로 설정.
		// 0,0 에서 시작해서 n-1,n-1까지 모든 탐색 필요.
		// 끝 지점에 다다랐을 때 core최댓값을 갱신하고. 만약 다음 core값이 동일하다면, 최소 전선 수를 갱신.
		// core 수와 전선 수를 들고 다녀야함.

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			n = sc.nextInt();
			grid = new int[n][n];
			int initcore = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					grid[i][j] = sc.nextInt();
					if ((i == 0 || i == n - 1 || j == 0 || j == n - 1) && grid[i][j] == 1) {
						grid[i][j] = -1;
						initcore++;
					}
				}
			}

			minline = Integer.MAX_VALUE;
			maxcore = 0;
			dfs(0, 0, initcore, 0); // 0, 0 , core, line
			System.out.println("#" + tt + " " + minline);
			tt++;
		}

	}

	static int n;
	static int[][] grid;
	static int minline;
	static int maxcore;
	static int[] row = { -1, 1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };

	private static void dfs(int r, int c, int core, int line) {
		if (r == n) {
			if (maxcore < core) {
				maxcore = core;
				minline = line;
			} else if (maxcore == core) {
				minline = Math.min(line, minline);
			}
			return;
		}

		int nextR = r;
		int nextC = c + 1;
		if (c == n - 1) {
			nextR = r + 1;
			nextC = 0;
		}

		if (grid[r][c] == 1) {
			for (int k = 0; k < 4; k++) {
				int cnt = countline(r, c, k);
				if (cnt != -1) {
					setline(r, c, k, 2);
					dfs(nextR, nextC, core + 1, line + cnt);
					setline(r, c, k, 0);
				}
			}
		}
		dfs(nextR, nextC, core, line);

	}

	private static void setline(int r, int c, int k, int value) {
		int nr = r + row[k];
		int nc = c + col[k];
		while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
			grid[nr][nc] = value;
			nr += row[k];
			nc += col[k];
		}
	}

	private static int countline(int r, int c, int k) {
		int cnt = 0;
		int nr = r + row[k];
		int nc = c + col[k];
		while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
			if (grid[nr][nc] != 0) {
				return -1;
			} else {
				cnt++;
				nr += row[k];
				nc += col[k];
			}
		}
		return cnt;
	}

}
