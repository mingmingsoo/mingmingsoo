
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		limit = sc.nextInt();

		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		r = 3;
		min = Integer.MAX_VALUE;
		ans = 0;
		재귀(0, 0, 0);
		System.out.println(ans);

	}

	static int sum;
	static int n;
	static int limit;
	static int r;
	static int[] arr;
	static int min;
	static int ans;

	private static void 재귀(int idx, int sidx, int sum) {
		if (sum > limit) {
			return;
		}

		if (sidx == r) {
			int diff = limit - sum;
			min = Math.min(diff, min);
			if (diff == min) {
				ans = sum;
			}

			return;
		}

		if (idx == n) {
			return;
		}
		재귀(idx + 1, sidx + 1, sum + arr[idx]);
		재귀(idx + 1, sidx, sum);

	}

}
