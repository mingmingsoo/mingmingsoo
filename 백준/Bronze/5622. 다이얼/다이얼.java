

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		int ans = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= 65 && str.charAt(i) <= 67) {
				ans += 3;
			} else if (str.charAt(i) >= 68 && str.charAt(i) <= 70) {
				ans += 4;
			} else if (str.charAt(i) >= 71 && str.charAt(i) <= 73) {
				ans += 5;
			} else if (str.charAt(i) >= 74 && str.charAt(i) <= 76) {
				ans += 6;
			} else if (str.charAt(i) >= 77 && str.charAt(i) <= 79) {
				ans += 7;
			} else if (str.charAt(i) >= 80 && str.charAt(i) <= 83) {
				ans += 8;
			} else if (str.charAt(i) >= 84 && str.charAt(i) <= 86) {
				ans += 9;
			} else {
				ans += 10;
			}
		}
		System.out.println(ans);
	}
}
