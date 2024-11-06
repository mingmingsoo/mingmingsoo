

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			char[][] grid = new char[r][c];

			for (int i = 0; i < r; i++) {
				String line = sc.next();
				for (int j = 0; j < c; j++) {
					grid[i][j] = line.charAt(j);
				}
			}
//		System.out.println(Arrays.deepToString(grid));

			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] { 0, 0, 2, 0 }); // 현재 위치, 방향, 메모리
			boolean[][][][] visited = new boolean[r][c][4][16];
			visited[0][0][2][0] = true;
			String ans = "NO";
			while (!q.isEmpty()) {
				int[] node = q.poll();
				int x = node[0];
				int y = node[1];
				int d = node[2];
				int m = node[3];
//			System.out.println(x + " " + y + " " + d + " " + m);
				if (grid[x][y] == '@') {
					ans = "YES";
					break;
				}

				char order = grid[x][y];
				if (order != '?') {
					if (order == '<') {
						d = 3;
					} else if (order == '>') {
						d = 2;
					} else if (order == '^') {
						d = 0;
					} else if (order == 'v') {
						d = 1;
					} else if (order == '_' && m == 0) {
						d = 2;

					} else if (order == '_' && m != 0) {
						d = 3;
					} else if (order == '|' && m == 0) {
						d = 1;
					} else if (order == '|' && m != 0) {
						d = 0;
					} else if (order == '?') {

					} else if (order >= '0' && order <= '9') {
						m = (order - 48);
					} else if (order == '+') {
						if (m == 15) {
							m = 0;
						} else {
							m++;
						}
					} else if (order == '-') {
						if (m == 0) {
							m = 15;
						} else {
							m--;
						}
					}
					if (d == 0) {
						if (!visited[(x - 1 + r) % r][y % c][d][m]) {
							q.add(new int[] { (x - 1 + r) % r, y % c, d, m });
							visited[(x - 1 + r) % r][y % c][d][m] = true;
						}

					} else if (d == 1) {
						if (!visited[(x + 1) % r][y % c][d][m]) {
							q.add(new int[] { (x + 1) % r, y % c, d, m });
							visited[(x + 1 + r) % r][y % c][d][m] = true;
						}
					} else if (d == 2) {
						if (!visited[x % r][(y + 1) % c][d][m]) {
							q.add(new int[] { x % r, (y + 1) % c, d, m });
							visited[x % r][(y + 1) % c][d][m] = true;
						}
					} else if (d == 3) {
						if (!visited[x % r][(y - 1 + c) % c][d][m]) {
							q.add(new int[] { x % r, (y - 1 + c) % c, d, m });
							visited[x % r][(y - 1 + c) % c][d][m] = true;
						}
					}
				} else {
					if (!visited[(x - 1 + r) % r][y % c][d][m]) {
						q.add(new int[] { (x - 1 + r) % r, y % c, 0, m });
						visited[(x - 1 + r) % r][y % c][0][m] = true;
					}
					if (!visited[(x + 1) % r][y % c][d][m]) {
						q.add(new int[] { (x + 1) % r, y % c, 1, m });
						visited[(x + 1 + r) % r][y % c][1][m] = true;
					}
					if (!visited[x % r][(y + 1) % c][d][m]) {
						q.add(new int[] { x % r, (y + 1) % c, 2, m });
						visited[x % r][(y + 1) % c][2][m] = true;
					}
					if (!visited[x % r][(y - 1 + c) % c][d][m]) {
						q.add(new int[] { x % r, (y - 1 + c) % c, 3, m });
						visited[x % r][(y - 1 + c) % c][3][m] = true;
					}

				}
			}
			System.out.println("#" + tt + " " + ans);
			tt++;
		}

	}
}
