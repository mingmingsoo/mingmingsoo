

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		int cnt = 0;

		int sum = arr[0];
		int left = 0;
		int right = 0;

		while (right < n) {
			if (sum < m) {
				right++;
				if (right < n) {
					sum += arr[right];
				}
			} else if (sum == m) {
				cnt++;
				sum -= arr[left];
				left++;
			} else if (sum > m) {
				sum -= arr[left];
				left++;
			}
		}
		System.out.println(cnt);

	}

}
