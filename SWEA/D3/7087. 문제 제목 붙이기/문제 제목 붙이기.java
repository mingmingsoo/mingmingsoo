import java.util.Arrays;
import java.util.Scanner;
public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int tt = 1;
		while (tt <= t) {
			int n = sc.nextInt();
			String[] arr = new String[n];
			for (int i = 0; i < n; i++) {
				arr[i] = sc.next();
			}
			int[] first = new int[n];
			for (int i = 0; i < n; i++) {
				first[i] = arr[i].charAt(0) - 'A';
			}
			Arrays.sort(first);
			int cnt = 0;
			if (first[0] == 0) {
				cnt = 1;
			}
			for (int i = 0; i < first.length - 1; i++) {
				if (first[i + 1] - first[i] == 1) {
					cnt++;
				} else if (first[i + 1] - first[i] == 0) {
				} else {
					break;
				}
			}
			if (first[0] != 0) {
				cnt = 0;
			}
			System.out.println("#" + tt + " " + cnt);
			tt++;
		}
	}
}