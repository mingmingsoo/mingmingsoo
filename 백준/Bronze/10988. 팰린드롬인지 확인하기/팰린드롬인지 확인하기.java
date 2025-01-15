
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		if (str.length() % 2 == 1) {// 홀수 일때
			for (int j = 1; j <= str.length() / 2; j++) {
				if (str.charAt(str.length() / 2 + j) != str.charAt(str.length() / 2 - j)) {
					System.out.println(0);
					return;
				}
			}
			System.out.println(1);
		} else {// 짝수일 때
			for (int i = 0; i < str.length() / 2; i++) {
				if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
					System.out.println(0);
					return;
				}
			}
			System.out.println(1);
		}
	}
}
