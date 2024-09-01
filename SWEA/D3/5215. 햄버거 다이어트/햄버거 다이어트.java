

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			n = sc.nextInt();
			limit = sc.nextInt();

			taste = new int[n];
			cals = new int[n];

			for (int i = 0; i < n; i++) {
				taste[i] = sc.nextInt();
				cals[i] = sc.nextInt();
			}
			bestscore = 0;
			makeburger(0, 0, 0);
			System.out.println("#" + tt + " " + bestscore);
			tt++;
		}

	}

	static int bestscore;
	static int limit;
	static int[] taste;
	static int[] cals;
	static int n;

	private static void makeburger(int idx, int sum_taste, int sum_cal) {
		if (sum_cal > limit) {
			return;
		}
		if (idx == n) {
			if (sum_cal <= limit) {
				bestscore = Math.max(bestscore, sum_taste);
			}
			return;
		}

		makeburger(idx + 1, sum_taste + taste[idx], sum_cal + cals[idx]);
		makeburger(idx + 1, sum_taste, sum_cal);

	}

}
