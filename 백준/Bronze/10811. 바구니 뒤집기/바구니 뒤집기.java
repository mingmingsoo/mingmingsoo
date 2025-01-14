
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
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int diff = (to - from) / 2;
			for (int j = from; j <= from + diff; j++) {
				int tmp = arr[j];
				arr[j] = arr[to];
				arr[to] = tmp;
				to--;
				if (from == to) {
					break;
				}
			}

		}
		for (int ele : arr) {
			System.out.print(ele + " ");
		}

	}
}
