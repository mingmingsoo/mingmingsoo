

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while(true) {
			n = sc.nextInt();
			if(n == 0) {
				break;
			}
			r = 6;
			arr = new int[n];
			
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}
			visited = new boolean[n];
			sel = new int[r];
			sb = new StringBuilder();
			재귀(0, 0);
			System.out.println(sb);
			
		}
	}

	static int n;
	static int r;
	static int[] arr;
	static int[] sel;
	static boolean[] visited;
	static StringBuilder sb;

	private static void 재귀(int idx, int sidx) {
		if (sidx == r) {
			for (int ele : sel) {
				sb.append(ele + " ");
			}
			sb.append("\n");
			return;
		}

		if (idx == n) {
			return;
		}

		sel[sidx] = arr[idx];
		재귀(idx + 1, sidx + 1);
		재귀(idx + 1, sidx);

	}

}
