

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		if (N >= 90) {
			System.out.println('A');
			return;
		} else if (N >= 80) {
			System.out.println('B');
			return;
		} else if (N >= 70) {
			System.out.println('C');
			return;
		} else if (N >= 60) {
			System.out.println('D');
			return;
		}
		System.out.println('F');
	}

}
