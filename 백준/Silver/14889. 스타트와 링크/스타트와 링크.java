

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		r = n / 2;
		grid = new int[n][n];
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}
//		System.out.println(Arrays.toString(arr));

		sela = new int[r];
		cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		ans = Integer.MAX_VALUE;
		comb(0, 0);
		System.out.println(ans);

	}

	static int[][] grid;
	static int ans;
	static int cnt;
	static int n;
	static int r;
	static int sela[];
	static int selb[];
	static int arr[];
	static boolean check[];

	private static void comb(int idx, int sidx) {
		if (sidx == r) {
			selb = new int[r];
			check = new boolean[n];

			for (int i = 0; i < r; i++) {
				check[sela[i]] = true;
			}

			int bindex = 0;
			for (int i = 0; i < n; i++) {
				if (!check[i]) {
					selb[bindex++] = i;
				}
			}


			int atotal = 0;
			int btotal = 0;
			for (int i = 0; i < r; i++) {
				for (int j = i + 1; j < r; j++) {
					atotal += grid[sela[i]][sela[j]]+grid[sela[j]][sela[i]];
					btotal += grid[selb[i]][selb[j]]+grid[selb[j]][selb[i]];
				}
			}
			ans = Math.min(ans, Math.abs(atotal - btotal));

			return;
		}

		if (idx == n) {
			return;
		}
		

		sela[sidx] = arr[idx];
		comb(idx + 1, sidx + 1);
		comb(idx + 1, sidx);

	}

}
