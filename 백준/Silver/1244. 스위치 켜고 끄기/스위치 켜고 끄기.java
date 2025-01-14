

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			if (gender == 1) {
				// 남자
				for (int j = idx; j <= N; j += idx) {
					arr[j] = 1 - arr[j];
				}

			} else {
				// 여자
				int range = 0;
				for (int n = 1; (idx + n <= N) && (idx - n > 0); n++) {
					if (arr[idx + n] == arr[idx - n]) {
						range = n;
					} else {
						break;
					}
				}
				for (int j = idx - range; j <= idx + range; j++) {
					arr[j] = 1 - arr[j];
				}

			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(arr[i]).append(" ");
			if (i % 20 == 0) {
				sb.append("\n");
			}
		}
		System.out.println(sb);

	}

}
