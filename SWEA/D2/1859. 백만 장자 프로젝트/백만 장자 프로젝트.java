
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			int n = sc.nextInt();
			int[] arr = new int[n];
			for (int i = n - 1; i >= 0; i--) {
				arr[i] = sc.nextInt(); // 뒤에서 부터 보려고 뒤부터 받았음
			}

			long ans = 0;
//		System.out.println(Arrays.toString(arr));
		int cnt;
			out: for (int i = 0; i < n; i+=cnt+1) {
				cnt = 0;
				for (int j = i + 1; j < n; j++) {
					if (arr[i] > arr[j]) {
						ans += (arr[i] - arr[j]);
						cnt++;
//						System.out.println(ans);
					} else {
						continue out;
					}
				}
			}

			System.out.println("#" + tt + " " + ans);

			tt++;
		}

	}

}