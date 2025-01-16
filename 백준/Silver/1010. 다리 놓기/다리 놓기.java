

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// mCn 구해야함
			double mok = 1;
			for (int i = M; i > (M - N); i--) {
				mok *= i;
			}
			double divide = 1;
			for (int i = 1; i <= N; i++) {
				divide *= i;
			}
//			System.out.println(mok);
//			System.out.println(divide);
			double ans = mok/divide;
			System.out.println(Math.round(ans));
//			1
//			20 21
			
//			1
//			24 29
		}

	}
}
