
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);

		StringBuilder sb = new StringBuilder();
		int cnt = sc.nextInt();
		for (int i = 0; i < cnt; i++) {
			int value = sc.nextInt();
			if (Arrays.binarySearch(arr, value) < 0) {
				sb.append(0+"\n");
			} else {
				sb.append(1+"\n");
			}
		}
		System.out.println(sb);

	}

}
