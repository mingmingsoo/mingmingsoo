

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int r = sc.nextInt();

		int[] arr = new int[n];
		int[] sel = new int[r];

		int idx = 0;
		for (int i = 1; i <= n; i++) {
			arr[idx++] = i;
		}
		dupleperm(0, n, r, arr, sel);
		System.out.println(sb);

	}

	static StringBuilder sb= new StringBuilder();

	private static void dupleperm(int idx, int n, int r, int[] arr, int[] sel) {

		if (idx == r) {
			for (int ele : sel) {
				sb.append(ele + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < n; i++) {
			sel[idx] = arr[i];
			dupleperm(idx + 1, n, r, arr, sel);

		}

	}
}
