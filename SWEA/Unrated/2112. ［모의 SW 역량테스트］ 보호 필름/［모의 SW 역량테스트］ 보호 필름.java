
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// 최대 쏠 수 있는 횟수는 k
		// 즉 k 번쏘면 무조건 통과는 하게 되어있음
		// 위에서부터 한줄씩 쏠꺼임.

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			d = sc.nextInt();
			w = sc.nextInt();
			k = sc.nextInt();

			grid = new int[d][w];
			for (int i = 0; i < d; i++) {
				for (int j = 0; j < w; j++) {
					grid[i][j] = sc.nextInt();
				}
			}

			ans = Integer.MAX_VALUE;
			dfs(0, 0); // 0: 행 0: 쏜 횟수
			System.out.println("#" + tt + " " + ans);
			tt++;
		}

	}

	static int ans;
	static int k;
	static int d;
	static int w;
	static int[][] grid;

	private static void dfs(int r, int cnt) {
		if (pass()) {
			if (ans > cnt) {
				ans = cnt;
			}
			return;
		}

		if (cnt >= k) {
			return;
		}

		if (r == d) {
			return;
		}

		// 안 쏠때
		dfs(r + 1, cnt);

		// 쏠 때
		int[] tmp = grid[r].clone();

		// 0 쐈음
		Arrays.fill(grid[r], 0);
		dfs(r + 1, cnt + 1);

		// 1 쐈음
		Arrays.fill(grid[r], 1);
		dfs(r + 1, cnt + 1);

		grid[r] = tmp;

	}

	private static boolean pass() {

		con: for (int j = 0; j < w; j++) {
			int cnt = 1;
			for (int i = 0; i < d - 1; i++) {
				if (grid[i][j] == grid[i + 1][j]) {
					cnt++;
				} else {
					cnt = 1;
				}
				if (cnt >= k) {
					continue con;
				}
			}
			return false;
		}

		return true;

	}

}
