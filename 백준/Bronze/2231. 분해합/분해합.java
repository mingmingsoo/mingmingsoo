

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();

		// x+y+z+100x+10y+z = N이 되야하는뎀...
		// 101x+11y+2z = N
		boolean bool = false;
		int ans = 0;

		for (int i = 1; i < Integer.parseInt(str); i++) {
			int sum = 0;
			String number = String.valueOf(i);
			for (int j = 0; j < number.length(); j++) {
				sum += number.charAt(j) - '0';
			}
			sum += i;
			if (sum == Integer.parseInt(str)) {
				bool = true;
				ans = i;
				break;
			}
		}

		if (bool) {
			System.out.println(ans);
			return;
		}
		System.out.println(0);
	}
}
