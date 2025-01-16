
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
		int N = Integer.parseInt(st.nextToken()); // 카드 객수
		int M = Integer.parseInt(st.nextToken()); // 가까워져야하는 합
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 3장을 뽑아서 블라블라
		// 더한 값에서 M을 뺀 값이 가장 작은 값
		int minus = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				for (int w = j + 1; w < arr.length; w++) {
					int sum = arr[i]+arr[j]+arr[w];
					if(sum>M) {
						continue;
					}
					minus = Math.min(minus, Math.abs(sum-M));
				}
			}
		}
		System.out.println(M-minus);

	}
}
