
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			n = sc.nextInt();
			k = sc.nextInt();

			grid = new int[n][n];
			visited = new boolean[n][n];

			int max = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int tmp = sc.nextInt();
					grid[i][j] = tmp;
					if (tmp > max) {
						max = tmp;
					}
				}
			}

			// 중요한 건 내 위치에서 그 다음 위치를 깎아야 한다면 내 위치보다 -1 만큼만 해주면 되는 것.
			// 깍을 수 있는가?는 1. 아직 안깎았고 2. 현재위치 - k 보다 작아야 함.
			eachmaxlength = 0;
			int ans = 0; // 등산로 길이
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j] == max) { // 최대 높이인 지점들만 지형 깎는 과정 거쳐서 길이 구하고, 그 중 최댓값이 ans가 될거임
//						ans = Math.max(ans, work(i, j, 1, true));
						ans = Math.max(ans, work(i, j, 1, grid[i][j], true)); // 현재 내 높이 추가
					}
				}
			}
			System.out.println("#" + tt + " " + ans);
			tt++;
		}

	}

	static int n;
	static int k;

	static int row[] = { -1, 1, 0, 0 };
	static int col[] = { 0, 0, 1, -1 };
	static boolean[][] visited;
	static int[][] grid;
	static int eachmaxlength;

	private static int work(int r, int c, int length, boolean onechance) { // 내 위치 i, j 와 거리, 깎을 수 있는지?

		eachmaxlength = Math.max(length, eachmaxlength);

		visited[r][c] = true;

		for (int i = 0; i < 4; i++) {
			int nr = r + row[i];
			int nc = c + col[i];
			if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
				if (grid[nr][nc] < grid[r][c]) {
					work(nr, nc, length + 1, onechance);
				} else if (grid[nr][nc] - k < grid[r][c] && onechance) {
					int tmp = grid[nr][nc];
					grid[nr][nc] = grid[r][c] - 1;
					work(nr, nc, length + 1, false);
					grid[nr][nc] = tmp;
				}
			}
		}

		visited[r][c] = false;

		return eachmaxlength;

	}

	private static int work(int r, int c, int length, int height, boolean onechance) { // 내 위치 i, j 와 거리, 깎을 수 있는지?

		eachmaxlength = Math.max(length, eachmaxlength);

		visited[r][c] = true;

		for (int i = 0; i < 4; i++) {
			int nr = r + row[i];
			int nc = c + col[i];
			if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
				if (grid[nr][nc] < height) {
					work(nr, nc, length + 1, grid[nr][nc], onechance);
				} else if (grid[nr][nc] - k < height && onechance) {
//					onechance = false; // 이거 안됨!!
					work(nr, nc, length + 1, height - 1, false); // 매개변수를 바꿔버리면 그 뒤에 작업들이 영향을 받고 모두 false 가 되버림!! 안된다!
				}
			}
		}

		visited[r][c] = false;

		return eachmaxlength;

	}

}
