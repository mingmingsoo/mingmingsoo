

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 바구니 갯수
		int M = Integer.parseInt(st.nextToken()); // 공 던질 횟수

		int[] basket = new int[N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int ball = Integer.parseInt(st.nextToken());

			for (int j = from; j <= to; j++) {
				basket[j] = ball;

			}

		}
		for (int ele : basket) {
			System.out.print(ele + " ");
		}

	}
}
