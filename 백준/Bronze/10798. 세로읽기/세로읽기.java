

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = new String[5];
		int maxL = 0;
		for (int i = 0; i < 5; i++) {
			str[i] = br.readLine();
			if (str[i].length() > maxL) {
				maxL = str[i].length();
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < maxL; j++) {
			for (int i = 0; i < 5; i++) {
				if (str[i].length() > j) {
					sb.append(str[i].charAt(j));
				}
			}
		}
		System.out.println(sb);
	}
}
