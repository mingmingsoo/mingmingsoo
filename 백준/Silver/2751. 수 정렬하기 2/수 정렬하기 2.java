
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		arr = new int[n];
		tmp = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		mergeSort(0, n-1);
		for (int ele : arr) {
			sb.append(ele+"\n");
		}
		System.out.println(sb);
	}

	private static void mergeSort(int left, int right) {

		if (left < right) {

			int mid = (left + right) / 2;
			mergeSort(left, mid);
			mergeSort(mid + 1, right);
			merge(left, mid, right);
		}

	}

	static int[] arr;
	static int[] tmp;

	private static void merge(int left, int mid, int right) {
		int L = left;
		int R = mid + 1;
		int idx = left;

		while (L <= mid && R <= right) {
			if (arr[L] <= arr[R]) {
				tmp[idx++] = arr[L++];
			} else {
				tmp[idx++] = arr[R++];
			}
		}

		if (L <= mid) {
			for (int i = L; i <= mid; i++) {
				tmp[idx++] = arr[i];
			}
		} else {
			for (int i = R; i <= right; i++) {
				tmp[idx++] = arr[i];
			}
		}

		for (int i = left; i <= right; i++) {
			arr[i] = tmp[i];
		}

	}

}
