
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int idx = -1;
		int max = Integer.MIN_VALUE;

		for (int i = 1; i < 10; i++) {
			int num = Integer.parseInt(br.readLine());
			if (max < num) {
				max = num;
				idx = i;
			}
		}
		System.out.println(max+"\n"+idx);


	}
}
