
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// Main

	// 아 DP네 아 아니네

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		for (int i = N - 1; i >= 0; i--) {
			arr[i] = Integer.parseInt(br.readLine());
		} // 내림차순으로 받음
		int ans = 0;
		for (int i = 0; i < N; i++) {
			if (K >= arr[i]) {
//				System.out.println(arr[i]+"원 동전 사용");
				while (K >= arr[i]) {
					ans++;
					K -= arr[i];
//					System.out.println(K+"원 남음");
				}
				if(K==0) {
					break;
				}
			}
		}
		System.out.println(ans);

	}
}
