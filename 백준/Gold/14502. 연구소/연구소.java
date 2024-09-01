

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
				int tmp = sc.nextInt();
				grid[i][j] = tmp;
				if (tmp == 0) {
					zero++;
				}
			}
		}
		zerolist_x = new int[zero];
		zerolist_y = new int[zero];

		int zeroidx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 0) {
					zerolist_x[zeroidx] = i;
					zerolist_y[zeroidx] = j;
					zeroidx++;
				}
			}
		}

		sel_x = new int[r];
		sel_y = new int[r];

		ans = 0; // 💛
		term(0, 0); // idx sidx

//		1. grid 값이 0인 곳들의 조합 // 매 경우의수 별로true로 만든다음
//		2. bfs로 안전영역 계산
//		3. 그다음 true로만든거 다시 원상복구
		System.out.println(ans);

	}

	static int n;
	static int m;
	static int r = 3;
	static int[] sel_x;
	static int[] sel_y;
	static int[] zerolist_x;
	static int[] zerolist_y;
	static int zero;
	static int[][] grid;
	static boolean[][] visited;
	static int[] row = { -1, 1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };
	static int ans;

	private static void term(int idx, int sidx) {
		if (sidx == r) {
//			System.out.println(Arrays.toString(sel_x) + " " + Arrays.toString(sel_y));
			for (int i = 0; i < 3; i++) {
				grid[sel_x[i]][sel_y[i]] = 1;
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (grid[i][j] == 2) { 
						dfs(i, j);
					}
				}
			}

			int safety = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (!visited[i][j] && grid[i][j] == 0) {
						safety++;
					}
				}
			}
//			System.out.println(safety);
			ans = Math.max(ans, safety);

			visited = new boolean[n][m]; // 초기화

			for (int i = 0; i < 3; i++) {
				grid[sel_x[i]][sel_y[i]] = 0; // 초기화
			}

			return;
		}
		if (idx == zero) {
			return;
		}

		sel_x[sidx] = zerolist_x[idx];
		sel_y[sidx] = zerolist_y[idx];
		term(idx + 1, sidx + 1);
		term(idx + 1, sidx);

	}

	private static void dfs(int i, int j) {

		for (int k = 0; k < 4; k++) {
			int nr = i + row[k];
			int nc = j + col[k];
			if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 0 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr,nc);// 💛
			}
		}
	}
}
