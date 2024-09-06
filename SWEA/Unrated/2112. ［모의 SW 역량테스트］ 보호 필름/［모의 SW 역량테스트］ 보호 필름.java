
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;
		while (tt <= t) {
			n = sc.nextInt();
			m = sc.nextInt();
			k = sc.nextInt();

			grid = new int[n][m];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					grid[i][j] = sc.nextInt();
				}
			}
			ans = k; // k번이 ans의 최댓값임! k번 쏘면 무조건 통과니까!
			change(0, 0); // r , 쏜 횟수

//		매번 각 행은 3가지 경우의 수가 나온다 -> 안쐈을 때. 0 쐈을 때. 1 쐈을 때
			// 조건을 통과하는 최소 shoot 수를 구하기.
			System.out.println("#" + tt + " " + ans);
			tt++;
		}

	}

	static int n;
	static int m;
	static int k;
	static int[][] grid;
	static int ans;

	private static void change(int r, int shoot) {
		if (pass(0)) {
			ans = Math.min(shoot, ans);
			return;
		}

		if (r == n) {
			return;
		}

		if (shoot >= ans) {
			return;
		}

		// 원본 배열 생성
		int[] tmp = grid[r].clone();

		// 그냥 넘어갈 때
		change(r + 1, shoot);

		// 0을 쐈을 때
		Arrays.fill(grid[r], 0);
		change(r + 1, shoot + 1);

		// 1을 쐈을 때
		Arrays.fill(grid[r], 1);
		change(r + 1, shoot + 1);

		// 원상 복구
		grid[r] = tmp;

	}

	private static boolean pass(int c) {

		if (c == m) {
			return true;
		}

		int r = 0;
		int cnt = 0;
		int cur = grid[r][c];

		while (r < n - 1) {
			if (cur == grid[r + 1][c]) {
				cnt++;
				cur = grid[r + 1][c];
				r++;
			} else {
				r++;
				cnt = 0;
				cur = grid[r][c];
			}

			if (cnt == k - 1) {
				return pass(c + 1);
			}
		}

		return false;
	}

}
