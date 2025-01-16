

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		float[] arr = new float[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Float.parseFloat(br.readLine());
		}
		float max = 0;
		for (int i = 0; i < N; i++) {
			float mul = 1;
			for (int j = i; j < N; j++) {
				mul *= arr[j];
				max = Math.max(max, mul);
//				System.out.println(mul);
			}
		}
		System.out.println(String.format("%.3f", (float) Math.round(max * 1000) / 1000));
	}
}
