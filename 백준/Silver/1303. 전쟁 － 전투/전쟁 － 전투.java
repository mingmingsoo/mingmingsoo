

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int wp; // white power
	static int bp; // blue power
	static char[][] war;
	static boolean[][] visited;
	static int n;
	static int m;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		m = sc.nextInt();
		n = sc.nextInt();
		war = new char[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String tmp = sc.next();
			for (int j = 0; j < m; j++) {
				war[i][j] = tmp.charAt(j);
			}
		}
		wp = 0;
		bp = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j]) {
					cnt = 1;
					dfs(i, j, war[i][j]);
					if (war[i][j] == 'W') {
						wp += Math.pow(cnt, 2);
					} else {
						bp += Math.pow(cnt, 2);
					}
				}
			}
		}
		System.out.println(wp + " " + bp);

	}

	static int cnt;
	static int[] row = { -1, 1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };

	private static void dfs(int r, int c, char team) {

		visited[r][c] = true;

		for (int k = 0; k < 4; k++) {
			int nr = r + row[k];
			int nc = c + col[k];
			if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && war[nr][nc] == team) {
				cnt++;
				visited[nr][nc] = true;
				dfs(nr, nc, team);
			}
		}

	}

}
