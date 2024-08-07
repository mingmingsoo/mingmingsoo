
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int tt = 1;
		while (tt <= t) {
			int city = sc.nextInt();
			int d = sc.nextInt();
			int cnt = 0;
			int dnt = 0;
			for (int i = 0; i < city; i++) {
				if (sc.nextInt() == 1) {
					dnt = 0;
				} else {
					dnt++;
					if (dnt == d) {
						cnt++;
						dnt = 0;
					}
				}
			}
			System.out.println("#" + tt + " " + cnt);
			tt++;
		}
	}
}