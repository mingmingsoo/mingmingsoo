

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			if (isGroup(str)) {
				ans++;
			}
		}
		System.out.println(ans);

	}

	private static boolean isGroup(String str) {
		int[] abc = new int[26];
		con: for (int i = 0; i < str.length(); i++) {
			for (int j = i + 1; j < str.length(); j++) {
				if (str.charAt(i) == str.charAt(j)) {
					continue con;
				} else {
					break;
				}
			}
			if (abc[str.charAt(i) - 97] == 0) {
				abc[str.charAt(i) - 97] = 1;
				continue con;
			} else {
				return false;
			}

		}
		return true;
	}
}
