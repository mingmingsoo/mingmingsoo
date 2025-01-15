

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int max = -1;
		int idx = -1;
		int jdx = -1;
		for (int i = 1; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 10; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (max < num) {
					max = num;
					idx = i;
					jdx = j;
				}
			}
		}
		System.out.println(max + "\n" + idx + " " + jdx);

	}
}
