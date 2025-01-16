

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// Main
	static int[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// 그룹은 무조건 4개
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 범위를 생각해보면 더해서 7이되는 애들의 순열...
		int ans = 0;

		for (int i = 1; i < N; i++) { // 끝 점 설정.
			for (int j = i + 1; j < N; j++) {
				for (int k = j + 1; k < N; k++) {
					int one = cal(0, i);
					int two = cal(i, j);
					int three = cal(j, k);
					int four = cal(k, N);
					ans = Math.max(ans, one + two + three + four);

				}
			}
		}
		System.out.println(ans);

	}

	private static int cal(int k, int n) {
		int mul = 1;
		for (int i = k; i < n; i++) {
			mul *= arr[i];
		}
		return mul;
	}

}
