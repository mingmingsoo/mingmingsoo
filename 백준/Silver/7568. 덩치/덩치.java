
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// Main

	static class People {
		int kg;
		int cm;

		public People(int kg, int cm) {
			this.kg = kg;
			this.cm = cm;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		People[] arr = new People[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new People(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < N; i++) {
			int rank = 1;
			for (int j = 0; j < N; j++) {
				if (i == j) {
					continue;
				}

				if (arr[j].cm > arr[i].cm && arr[j].kg > arr[i].kg) {
					rank++; // 나보다 덩치 큰 애들 몇명인지...
				}

			}
			System.out.print(rank + " ");
		}

	}
}
