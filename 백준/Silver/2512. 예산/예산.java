
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int[] arr = new int[n];

		int sum = 0;
		int right = -1;
		int left = 0;
		int ans = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
			sum += arr[i];
			if (right < arr[i]) {
				right = arr[i];
			}
		}

		int limit = sc.nextInt();
		if (sum <= limit) {
			ans = right;
		}

		else {
			while (left <= right) {
				int mid = (left + right) / 2; // 처음엔 mid를 작게 설정하고 점차 조정할거임
				int total = 0; // 총 예산금액
				for (int i = 0; i < n; i++) {
					if (arr[i] > mid) {
						total += mid; // mid보다 크면 총 예산금액에 mid 합해줌
					} else {
						total += arr[i];
					}
				}

				if (total <= limit) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
			ans = right; // 맞춰진 right가 답

		}

		System.out.println(ans);

	}

}
