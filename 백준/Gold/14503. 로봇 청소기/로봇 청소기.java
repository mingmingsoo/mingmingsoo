
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		/*
		 * 
		 * 1. 현재 칸이 청소 되지 않은 경우 청소 2. 현재 칸 주변 4칸 중 빈칸이 없으면 방향 유지한 채 후진하고 1번 - 후진할 수 없으면
		 * 작동 멈춤 3. 현재 칸 주변 4칸 중 빈칸이 있는 경우 - 반시계 방향으로 90도 회전 - 바라보는 방향을 기준으로 앞쪽 칸이 청소되지
		 * 않은 빈칸인 경우 한칸 전진 - 1번
		 * 
		 * 0이면 갈 수 있음 1이면 벽임
		 * 
		 * visited는 청소 유무만 두고 벽인지 아닌지 확인해줘야함
		 * 
		 */

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		r = sc.nextInt();
		c = sc.nextInt();
		int d = sc.nextInt();

		grid = new int[n][m];
		clean = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		int cnt = 0;
		while (true) {
			if (grid[r][c] == 0 && !clean[r][c]) {
				clean[r][c] = true;
				cnt++;
			}
			if (con()) {
				if (d == 0 && r + 1 < n && grid[r + 1][c] == 0) {
					r++;
				} else if (d == 1 && c - 1 >= 0 && grid[r][c - 1] == 0) {
					c--;
				} else if (d == 2 && r - 1 >= 0 && grid[r - 1][c] == 0) {
					r--;
				} else if (d == 3 && c + 1 < m && grid[r][c + 1] == 0) {
					c++;
				} else {
					break;
				}
			}

			else {
				if (d == 0) {
					d = 3;
				} else if (d == 1) {
					d = 0;
				} else if (d == 2) {
					d = 1;
				} else if (d == 3) {
					d = 2;
				}

				if (d == 0 && r - 1 >= 0 && !clean[r - 1][c] && grid[r - 1][c] == 0) {
					r--;
				} else if (d == 1 && c + 1 < m && !clean[r][c + 1] && grid[r][c + 1] == 0) {
					c++;
				} else if (d == 2 && r + 1 < n && !clean[r + 1][c] && grid[r + 1][c] == 0) {
					r++;
				} else if (d == 3 && c - 1 >= 0 && !clean[r][c - 1] && grid[r][c - 1] == 0) {
					c--;
				}

			}
		}
		System.out.println(cnt);

	}

	static int[] row = { -1, 1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };
	static int r;
	static int c;
	static int n;
	static int m;
	static int[][] grid;
	static boolean[][] clean;

	private static boolean con() {
		for (int k = 0; k < 4; k++) {
			int nr = r + row[k];
			int nc = c + col[k];
			if (nr >= 0 && nr < n && nc >= 0 && nc < m && !clean[nr][nc] && grid[nr][nc] == 0) {
				return false;
			}
		}
		return true;
	}

}
