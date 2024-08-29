

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int r = sc.nextInt();

		int[] arr = new int[n];
		int[] sel = new int[r];

		int arridx = 0;
		for (int i = 1; i <= n; i++) {
			arr[arridx++] = i;
		}

		comb(0, n, r, arr, sel);

	}

	static StringBuilder sb;

	private static void comb(int idx, int n, int r, int[] arr, int[] sel) {
		if (idx == r) {
			sb = new StringBuilder();
			boolean bool = true;

			for (int i = r - 1; i > 0; i--) {
				if (sel[i] - sel[i - 1] < 0) {
					bool = false;
					if (!bool) {
						break;
					}
				}
			}
			if (bool) {
				for (int ele : sel) {
					sb.append(ele + " ");
				}
				System.out.println(sb);
			}

			return;
		}

		for (int i = 0; i < n; i++) {
			sel[idx] = arr[i];
			comb(idx + 1, n, r, arr, sel);

		}

	}

}
