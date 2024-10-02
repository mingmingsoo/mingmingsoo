

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		int limit = sc.nextInt();

		int[][] grid = new int[n][m];
		int airx_down = -1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = sc.nextInt();
				if (grid[i][j] == -1) {
					airx_down = i;
				}
			}
		}
		int airx_up = airx_down - 1;
//		System.out.println(airx_up + " " + airx_down);

		int time = 0;
		int[] row = { -1, 1, 0, 0 };
		int[] col = { 0, 0, 1, -1 };
		while (time < limit) {
			// 먼지 확산
			Queue<int[]> q = new LinkedList<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (grid[i][j] != 0 && grid[i][j] != -1) {
						q.add(new int[] { i, j });
					}
				}
			}
			int[][] tmp = new int[n][m];

			while (!q.isEmpty()) {
				int size = q.size();
				for (int i = 0; i < size; i++) {
					int[] node = q.poll();
					int r = node[0];
					int c = node[1];
					int before = grid[r][c]; // 30
					int devide = grid[r][c] / 5; // 6
					int devide_sum = 0;
					for (int k = 0; k < 4; k++) {
						int nr = r + row[k];
						int nc = c + col[k];
						if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] != -1) {
							tmp[nr][nc] += devide;
							devide_sum += devide;
						}
					}
					tmp[r][c] += (before - devide_sum);
				}
			}
			for (int i = 0; i < n; i++) {
				grid[i] = tmp[i].clone();
			}
			for (int i = 0; i < n; i++) {
				Arrays.fill(tmp[i], 0);
			}

//			System.out.println("확산후");
//			System.out.println(Arrays.deepToString(grid));
//			System.out.println(Arrays.deepToString(tmp));

			// 공기청정기 순환
			// 1. 위 순환
			// - 좌표가 airx_up, 0 이면 사라짐
			boolean[][] visited = new boolean[n][m];
			for (int i = airx_up; i > 0; i--) {
				tmp[i][0] = grid[i - 1][0];
				visited[i][0] = true;
			}
			for (int j = 0; j < m - 1; j++) {
				tmp[0][j] = grid[0][j + 1];
				visited[0][j] = true;
			}
			for (int i = 0; i < airx_up; i++) {
				tmp[i][m - 1] = grid[i + 1][m - 1];
				visited[i][m - 1] = true;
			}
			for (int j = m - 1; j > 0; j--) {
				tmp[airx_up][j] = grid[airx_up][j - 1];
				visited[airx_up][j] = true;
			}

			// 2. 아래 순환
			// - 좌표가 airx_down, 0 이면 사라짐
			for (int i = airx_down; i < n - 1; i++) {
				tmp[i][0] = grid[i + 1][0];
				visited[i][0] = true;
			}
			for (int j = 0; j < m - 1; j++) {
				tmp[n - 1][j] = grid[n - 1][j + 1];
				visited[n - 1][j] = true;
			}
			for (int i = n - 1; i > airx_down; i--) {
				tmp[i][m - 1] = grid[i - 1][m - 1];
				visited[i][m - 1] = true;
			}
			for (int j = m - 1; j > 0; j--) {
				tmp[airx_down][j] = grid[airx_down][j - 1];
				visited[airx_down][j] = true;
			}

//			System.out.println(Arrays.deepToString(visited));
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (!visited[i][j]) {
						tmp[i][j] = grid[i][j];
					}
				}
			}

			for (int i = 0; i < n; i++) {
				grid[i] = tmp[i].clone();
			}
			grid[airx_up][0] = -1;
			grid[airx_down][0] = -1;

//			System.out.println("순환 후");
//			System.out.println(Arrays.deepToString(grid));

			time++;
		}
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(grid[i][j]!=-1) {
					sum += grid[i][j];
				}
			}
		}
		System.out.println(sum);

	}

}
