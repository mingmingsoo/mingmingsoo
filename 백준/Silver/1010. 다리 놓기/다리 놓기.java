
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 머 이런 어이없는 문제가.
public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// mCn 구해야함
			double ans = 1;
			for (int i = M; i > (M - N); i--) {
				ans *= i;
			}
			for (int i = 1; i <= N; i++) {
				ans /= i;
			}
			sb.append(Math.round(ans)).append("\n");
		}
		System.out.println(sb);

	}
}
