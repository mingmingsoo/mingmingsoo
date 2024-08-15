
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int n;
	static int m;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		char[][] grid = new char[n][m];

		for (int i = 0; i < n; i++) {
			String line = sc.next();
			for (int j = 0; j < m; j++) {
				grid[i][j] = line.charAt(j);
			}
		}
		int ans = Integer.MAX_VALUE;
		int ix = 0;
		int jx = 0;
		while (true) {
//			System.out.println(ix+" "+jx);
			int cnt = 0;
//			System.out.println(change_cnt_wb(grid, ix, jx));
//			System.out.println(change_cnt_bw(grid, ix, jx));
			cnt = Math.min(change_cnt_wb(grid, ix, jx), change_cnt_bw(grid, ix, jx));
			ans = Math.min(ans, cnt);

			jx++;
			if (jx + 8 > m) {
				jx=0;
				ix++;
			}
			if (ix + 8 > n) {
				ix--;
			}
			if (ix + 8 >= n && jx + 8 >= m) {
				break;
			}
		}
		
		int last = Math.min(change_cnt_wb(grid, n-8, m-8), change_cnt_bw(grid, n-8, m-8));
		ans = Math.min(ans, last);
		System.out.println(ans);

	}

	private static int change_cnt_wb(char[][] grid, int i, int j) {
		// w로 시작
		int cnt = 0;
		char[] cha = { 'W', 'B' };
		int idx = 0;
		int idx2=1;
		for (int r = i; r < i + 8; r++) {
			for (int c = j; c < j + 8; c++) {
				if (r % 2 == 0) {
					if (r < n && c < m && grid[r][c] != cha[idx]) {
						cnt++;
						idx = 1 - idx;
					} else {
						idx = 1 - idx;
					}
				} else if (r % 2 == 1) {
					
					if (r < n && c < m && grid[r][c] != cha[idx2]) {
						cnt++;
						idx2 = 1 - idx2;
					} else {
						idx2 = 1 - idx2;
					}
				}
			}
		}
		// b로 시작
		return cnt;
	}

	private static int change_cnt_bw(char[][] grid, int i, int j) {
		// w로 시작
		int cnt = 0;
		char[] cha = { 'B', 'W' };
		int idx = 0;
		int idx2=1;
		for (int r = i; r < i + 8; r++) {
			for (int c = j; c < j + 8; c++) {
				if (r % 2 == 0) {
					if (r < n && c < m && grid[r][c] != cha[idx]) {
						cnt++;
						idx = 1 - idx;
					} else {
						idx = 1 - idx;
					}
				} else if (r % 2 == 1) {
					
					if (r < n && c < m && grid[r][c] != cha[idx2]) {
						cnt++;
						idx2 = 1 - idx2;
					} else {
						idx2 = 1 - idx2;
					}
				}
			}
		}
		// b로 시작
		return cnt;
	}

}
