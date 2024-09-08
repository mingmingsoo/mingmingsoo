
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			n = sc.nextInt();

			limit = sc.nextInt();
			arr = new int[n];
			sel = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}

			ans = Integer.MAX_VALUE;
			부분집합(0);
			System.out.println("#" + tt + " " + (ans - limit));
			tt++;
		}

	}

	static int ans;
	static int n;
	static int limit;
	static int[] arr;
	static int[] sel;

	private static void 부분집합(int idx) {
		if (idx == n) {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				if (sel[i] != 0) {
					sum += arr[i];
					if (sum >= limit) {
						ans = Math.min(sum, ans);
					}
				}
			}

			return;
		}

		sel[idx] = 1;
		부분집합(idx + 1);
		sel[idx] = 0;
		부분집합(idx + 1);
	}

}
