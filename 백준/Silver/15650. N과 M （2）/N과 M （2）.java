
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
//		System.out.println(Arrays.toString(arr));

		comb(0, 0, arr, sel, n, r);

	}
	static StringBuilder sb;

	private static void comb(int idx, int sidx, int[] arr, int[] sel, int n, int r) {
		if (sidx == r) {
			sb = new StringBuilder();
			for(int ele: sel) {
				sb.append(String.valueOf(ele)+" ");
			}
			System.out.println(sb);
			return;
		}
		if (idx == n) {
			return;
		}

		sel[sidx] = arr[idx];
		comb(idx + 1, sidx + 1, arr, sel, n, r);
		comb(idx + 1, sidx, arr, sel, n, r);

	}

}
