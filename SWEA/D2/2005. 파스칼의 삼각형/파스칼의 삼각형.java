import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {

			int n = sc.nextInt();
			System.out.print("#" + tt + " ");
			System.out.println();
			int[] arr1 = { 1 };
			System.out.println(arr1[0]);

			int cnt = 1;
			while (cnt < n) {
				int[] arr_next = new int[arr1.length + 1];
				arr_next[0] = 1;
				arr_next[arr_next.length - 1] = 1;
				for (int i = 1; i < arr_next.length - 1; i++) {
					arr_next[i] = arr1[i - 1] + arr1[i];
				}

				for (int i = 0; i < arr_next.length; i++) {
					System.out.print(arr_next[i] + " ");
				}
				System.out.println();
				arr1 = arr_next;

				cnt++;
			}

			tt++;
		}
	}
}