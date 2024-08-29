
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

		comb(0, 0, n, r, arr, sel);

	}

	static StringBuilder sb;

	private static void comb(int idx, int sidx, int n, int r, int[] arr, int[] sel) {
		if (sidx == r) {
			sb = new StringBuilder();
			for(int ele: sel) {
				sb.append(ele+" ");
			}
			System.out.println(sb);
			return;
		}

		if (idx == n) {
			return;
		}

		sel[sidx] = arr[idx];
		comb(idx, sidx + 1, n, r, arr, sel); // 선택했을 때 sidx는 당연히 커지고
		comb(idx + 1, sidx, n, r, arr, sel); // 선택안했을 때 idx도 커져야지 -> 그래야 선택 안한거지

	}

}
