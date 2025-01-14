

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int X = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());

		int Y = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Y += Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken());
		}
		if(X==Y) {
			System.out.println("Yes");
			return;
		}
		System.out.println("No");

	}

}
