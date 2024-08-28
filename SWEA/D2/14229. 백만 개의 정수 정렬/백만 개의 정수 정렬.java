
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		arr = new int[n];
		tmp = new int[n];
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		mergeSort(0, n - 1);
		System.out.println(arr[500000]);
	}

	static int n = 1000000;
	static int[] arr;
	static int[] tmp;

	private static void mergeSort(int left, int right) {

		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(left, mid);
			mergeSort(mid + 1, right);
			merge(left, mid, right);
		}
	}

	private static void merge(int left, int mid, int right) {
		int L = left; // 왼쪽 시작점
		int R = mid + 1; // 오른쪽 시작점
		int idx = left; // tmp 인덱스

		while (L <= mid && R <= right) {
			if (arr[L] <= arr[R]) {
				tmp[idx++] = arr[L++];
			} else {
				tmp[idx++] = arr[R++];
			}
		}

		if (L <= mid) { // 왼쪽이 아직 남아있을 경우
			for (int i = L; i <= mid; i++) {
				tmp[idx++] = arr[i];
			}
		}

		else { // 오른쪽이 아직 남아있을 경우
			for (int i = R; i <= right; i++) {
				tmp[idx++] = arr[i];
			}
		}
		
		// 복사
		for(int i = left; i<=right; i++) {
			arr[i] = tmp[i];
		}

	}

}
