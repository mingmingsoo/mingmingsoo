

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt(); // 회전 갯수

		grid = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = sc.nextInt();
			}
		}

		int kk = 0;

		arr = new int[k][4];
		sel = new int[k][4];

		while (kk < k) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int s = sc.nextInt();

			arr[kk][0] = r - s - 1;
			arr[kk][1] = c - s - 1;
			arr[kk][2] = r + s - 1;
			arr[kk][3] = c + s - 1;
			// 0 1 , 4 5 범위를 회전할 거임.

			kk++;
		}
		ans = Integer.MAX_VALUE;
		visited = new boolean[k];
		perm(0);
		System.out.println(ans);

	}

	static int k;
	static int[][] arr;
	static int[][] sel;
	static boolean[] visited;
	static boolean[][] check;
	static int n;
	static int m;
	static int[][] grid;
	static int[][] cgrid;
	static int[] range;
	static int ans;

	private static void perm(int idx) {

		if (idx == k) {
//			System.out.println(Arrays.deepToString(sel));

			cgrid = new int[n][m];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					cgrid[i][j] = grid[i][j];
				}
			}

			for (int i = 0; i < k; i++) {
				range = sel[i];
				check = new boolean[n][m];
				int r1 = range[0]; // 0
				int r2 = range[2]; // 4
				int c1 = range[1]; // 1
				int c2 = range[3]; // 5
				for (int r = r1; r <= r2; r++) {
					for (int c = c1; c <= c2; c++) {
						if (!check[r][c]) {
							rotation(r, c, r2, c2);
//							print(check);
//							print(cgrid);
							r2--;
							c2--;
						}
					}
				}

//				print(cgrid);
			}

			for (int i = 0; i < n; i++) {
				int sum = 0;
				for (int j = 0; j < m; j++) {
					sum += cgrid[i][j];
				}
				ans = Math.min(sum, ans);
			}

			return;
		}
		for (int i = 0; i < k; i++) {
			if (!visited[i]) {
				sel[idx] = arr[i].clone();
				visited[i] = true;
				perm(idx + 1);
				visited[i] = false;
			}
		}

	}

	private static void print(boolean[][] check2) {
		System.out.println("------------");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(check2[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void print(int[][] cgrid2) {
		System.out.println("------------");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(cgrid2[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void rotation(int r1, int c1, int r2, int c2) {
		if(r1==r2 && c1 == c2) {
			return;
		}
		int first = cgrid[r1][c1];
		for (int i = r1; i < r2; i++) {
			cgrid[i][c1] = cgrid[i + 1][c1];
			check[i][c1] = true;
		}

		for (int j = c1; j < c2; j++) {
			cgrid[r2][j] = cgrid[r2][j + 1];
			check[r2][j] = true;

		}
		for (int i = r2; i > r1; i--) {
			cgrid[i][c2] = cgrid[i - 1][c2];
			check[i][c2] = true;
		}
		for (int j = c2; j > c1; j--) {
			cgrid[r1][j] = cgrid[r1][j - 1];
			check[r1][j] = true;

		}

		cgrid[r1][c1 + 1] = first;

	}

}
