

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		grid = new int[n][m];
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			String tmp = sc.next();
			for (int j = 0; j < m; j++) {
				grid[i][j] = tmp.charAt(j) - '0';
			}
		}
		bfs(0, 0);

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		System.out.println(map[n - 1][m - 1]);

	}

	static int[] row = { -1, 1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };
	static int[][] map;
	static int[][] grid;
	static int n;
	static int m;
	static int dist = 1;

	private static void bfs(int r, int c) {
		Queue<Integer> q = new LinkedList<>();

		q.offer(r);
		q.offer(c);
		map[r][c] = dist++;

		while (!q.isEmpty()) {
			int size = q.size() / 2;
			for (int i = 0; i < size; i++) {

				int rnode = q.poll();
				int cnode = q.poll();

//				System.out.println(rnode + ", " + cnode);

				for (int k = 0; k < 4; k++) {
					int nr = rnode + row[k];
					int nc = cnode + col[k];

					if (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == 0 && grid[nr][nc] == 1) {
						q.offer(nr);
						q.offer(nc);
						map[nr][nc] = map[rnode][cnode] + 1;
					}

				}

			}

		}

	}
}
