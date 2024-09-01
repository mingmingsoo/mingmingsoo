

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		s = sc.nextInt();

		arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		sel = new int[n];

		cnt = 0;
		부분집합(0);
		System.out.println(cnt);

	}

	static int cnt;
	static int[] sel;
	static int n;
	static int s;
	static int[] arr;

	private static void 부분집합(int idx) {
		if (idx == n) {
			boolean bool = true;
			for (int i = 0; i < n; i++) {
				if (sel[i] != 0) {
					bool = false;
					break;
				}
			}

			if (!bool) {
				int sum = 0;
				for (int i = 0; i < n; i++) {
					if (sel[i] != 0) {
						sum += arr[i];
					}
				}
				if (sum == s) {
					cnt++;
				}
				return;
			}
			return;
		}

		sel[idx] = 0;
		부분집합(idx + 1);
		sel[idx] = 1;
		부분집합(idx + 1);

	}
}
