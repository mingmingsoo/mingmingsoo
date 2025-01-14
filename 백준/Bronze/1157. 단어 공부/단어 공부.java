

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[26];

		String str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			int n = str.charAt(i);
			if (n < 97) {
				arr[n + 32 - 97]++;
			} else {
				arr[n - 97]++;
			}
		}
		int max = -1;
		int maxIdx = -1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
				maxIdx = i;
			}
		} // 가장 큰 값.

		for (int i = maxIdx + 1; i < arr.length; i++) {
			if (arr[i] == max) {
				System.out.println("?");
				return;
			}
		}

		System.out.println((char) (maxIdx + 65));
	}
}
