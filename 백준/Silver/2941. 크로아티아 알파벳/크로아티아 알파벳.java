

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		List<String> list = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			boolean two = false;
			boolean three = false;
			// 세글자 검사
			if (i < str.length() - 2) {
				String s = String.valueOf(str.charAt(i));
				s += String.valueOf(str.charAt(i + 1));
				s += String.valueOf(str.charAt(i + 2));
				if (s.equals("dz=")) {
					three = true;
				}
			}
			// 두글자 검사
			if (i < str.length() - 1) {
				String s = String.valueOf(str.charAt(i));
				s += String.valueOf(str.charAt(i + 1));
				if (s.equals("c=")) {
					two = true;
				} else if (s.equals("c-")) {
					two = true;
				} else if (s.equals("d-")) {
					two = true;
				} else if (s.equals("lj")) {
					two = true;
				} else if (s.equals("nj")) {
					two = true;
				} else if (s.equals("s=")) {
					two = true;
				} else if (s.equals("z=")) {
					two = true;
				}
			}
			if (two) {
				list.add("C");
				i++;
			} else if (three) {
				list.add("C");
				i += 2;
			} else {
				list.add("X");
			}
		}
		System.out.println(list.size());

	}
}
