

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {
			int t = sc.nextInt();
			sc.nextLine();
			grid = new int[n][n];

			for (int i = 0; i < n; i++) {
				String str = sc.nextLine();
				for (int j = 0; j < n; j++) {
					grid[i][j] = str.charAt(j) - '0';
				}
			}
			ans = 0;
			out: for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j] == 2) {
						bfs(i, j);
						break out;
					}
				}
			}
			System.out.println("#" + t + " " + ans);
			if (t == 10)
				break;
		}

	}

	static int ans;
	static int n = 16;
	static int[] row = { -1, 1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };
	static int[][] grid;

	private static void bfs(int r, int c) {
		boolean[][] visited = new boolean[n][n];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c });
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int[] node = q.poll();
			int rr = node[0];
			int cc = node[1];

			if (grid[rr][cc] == 3) {
				ans = 1;
				return;
			}
			for (int k = 0; k < 4; k++) {
				int nr = rr + row[k];
				int nc = cc + col[k];
				if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc] && grid[nr][nc] != 1) { // grid[nr][nc]
																										// ==0 일때로 주면 안됨
																										// 3을 못찾음 아니면
																										// 0이거나 3이거나로
																										// 넣어줘아햠
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}

	}

}
