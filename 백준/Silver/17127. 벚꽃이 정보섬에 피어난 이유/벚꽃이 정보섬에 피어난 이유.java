

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// 그룹은 무조건 4개
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 범위를 생각해보면 더해서 7이되는 애들의 순열...
		int ans = 0;

		for (int i = 1; i < N; i++) { // 끝 점 설정.
			int one = 1;
			for (int x = 0; x < i; x++) {
				one *= arr[x];
			}
			for (int j = i + 1; j < N; j++) {
				int two = 1;
				for (int x = i; x < j; x++) {
					two *= arr[x];
				}
				for (int k = j + 1; k < N; k++) {
					int three = 1;
					for (int x = j; x < k; x++) {
						three *= arr[x];
					}
					int four = 1;
					for (int x = k; x < N; x++) {
						four *= arr[x];
					}
//					System.out.println(i + " " + j + " " + k + " ");
//					System.out.println(one + " " + two + " " + three + " " + four + " ");
//					System.out.println("---------------------------");
					ans = Math.max(ans, one + two + three + four);

				}
			}
		}

		System.out.println(ans);

	}

}
