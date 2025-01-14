

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int award = 0;

		if (A == B && B == C) {
			award = 10000 + A * 1000;
			System.out.println(award);
			return;
		} else if (A == B || B == C) {
			award = 1000 + B * 100;
			System.out.println(award);
			return;
		} else if (A == C) {
			award = 1000 + A * 100;
			System.out.println(award);
			return;
		}

		int max = Math.max(A, B);
		max = Math.max(max, C);
		award = max * 100;
		System.out.println(award);

	}
}
