
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			int n = sc.nextInt() / 10;

			int[] cost = new int[] { 5000, 1000, 500, 100, 50, 10, 5, 1 };

			int[] count = new int[cost.length];
			// ex 330이면
			for (int i = 0; i < cost.length; i++) {
				if (n / cost[i] >= 1) { // 3
					count[i] = n / cost[i];
					n -= cost[i] * count[i];
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#" + tt + "\n");
			for (int ele : count) {
				sb.append(ele + " ");
			}
			System.out.println(sb);
			tt++;
		}

	}
}