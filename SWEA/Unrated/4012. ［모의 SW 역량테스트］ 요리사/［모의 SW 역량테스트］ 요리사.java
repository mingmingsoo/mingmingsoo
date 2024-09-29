

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;
		StringBuilder sb = new StringBuilder();
		long before = System.currentTimeMillis();

		while (tt <= t) {
			sb.append("#" + tt + " ");
			n = sc.nextInt();
			arr = new int[n];
			visited = new boolean[n];

			for (int i = 0; i < n; i++) {
				arr[i] = i;
			}

			grid = new int[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					grid[i][j] = sc.nextInt();
				}
			}

			// 순열 연습
//		perm(0);
			// 부분집합 연습
//		a = new int[n];
//		subset(0);
			// 조합재귀 연습
			r = n / 2;
			a = new int[r];
			b = new int[r];
			ans = Integer.MAX_VALUE;
			combi(0, 0);
			sb.append(ans + "\n");
			tt++;
		}
		System.out.println(sb);
		long after = System.currentTimeMillis();
//		System.out.println(after - before);
	}

	static int[] a;
	static int[] b;
	static int[] arr;
	static int[][] grid;
	static boolean[] visited;
	static int n;
	static int r;
	static int ans;

	private static void combi(int idx, int sidx) {

		if (sidx == r) {
			Arrays.fill(visited, false);
			for (int i = 0; i < r; i++) {
				visited[a[i]] = true;
			}
			int bidx = 0;
			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					b[bidx++] = i;
				}
			}
//			System.out.println(Arrays.toString(a) + Arrays.toString(b));

			int ascore = calcul(a);
			int bscore = calcul(b);

			int minus = Math.abs(ascore - bscore);
			ans = Math.min(ans, minus);
			return;
		}
		if (idx == n / 2) { // 백트랙킹
			if (a[0] > r) {
				return;
			}
		}

		if (idx == n) {
			return;

		}

		a[sidx] = arr[idx];
		combi(idx + 1, sidx + 1);
		combi(idx + 1, sidx);
	}

	private static int calcul(int[] arr) {
		int sum = 0;
		for (int i = 0; i < r; i++) {
			for (int j = i + 1; j < r; j++) {
				sum += grid[arr[i]][arr[j]] + grid[arr[j]][arr[i]];
			}
		}

		return sum;
	}

	private static void subset(int idx) {

		if (idx >= n) {
			for (int i = 0; i < n; i++) {
				if (a[i] != 0) {
					System.out.print(arr[i] + " ");
				}
			}
			System.out.println();
			return;
		}
		a[idx] = 0;
		subset(idx + 1);
		a[idx] = 1;
		subset(idx + 1);
	}

	private static void perm(int idx) {

		if (idx >= n) {
			System.out.println(Arrays.toString(a));
			return;
		}
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				a[idx] = i;
				visited[i] = true;
				perm(idx + 1);
				visited[i] = false;

			}
		}

	}

}
