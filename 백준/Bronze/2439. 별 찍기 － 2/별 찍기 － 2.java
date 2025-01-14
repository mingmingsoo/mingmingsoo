

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int A = Integer.parseInt(br.readLine());

		for (int i = 0; i < A; i++) {
			for (int j = A - i - 2; j >= 0; j--) {
				System.out.print(" ");
			}

			for (int j = 0; j < i + 1; j++) {
				System.out.print("*");
			}

			System.out.println();
		}

	}
}
