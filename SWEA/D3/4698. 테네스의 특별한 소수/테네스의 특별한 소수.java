import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		int size = 1000000;
		boolean[] prime = new boolean[size + 1];

		for (int i = 2; i <= size; i++) {
			prime[i] = true;
		}

		con: for (int i = 2; i <= size; i++) {
			for (int j = 2; j <= Math.sqrt(size); j++) {
				if (i > j && i % j == 0) {
					prime[i] = false;
					continue con;
				}
			}
		}
//		System.out.println(Arrays.toString(prime));

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;
		while (tt <= t) {

			String d = sc.next();
			int a = sc.nextInt();
			int b = sc.nextInt();

			int cnt = 0;
			for (int i = a; i <= b; i++) {
				if (prime[i] && String.valueOf(i).contains(d)) {
					cnt++;
				}
			}

			System.out.println("#" + tt + " " + cnt);
			tt++;
		}

	}

}