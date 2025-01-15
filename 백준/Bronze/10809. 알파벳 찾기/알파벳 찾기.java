

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		int[] arr = new int[26];
		Arrays.fill(arr, -1);

		for (int i = 0; i < str.length(); i++) {
			int abc = str.charAt(i) - 97;
			if (arr[abc] == -1) {
				arr[abc] = i;
			}
		}
		for (int ele : arr) {
			System.out.print(ele + " ");
		}

	}
}
