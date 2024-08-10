import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {

			String s = sc.next();

			int ea = 1; // 마디가 1개~ 10개일 때 다 따질거임
			boolean check = false;
			while (ea <= 10) {
				String repeat = ""; // 같은 글자가 나오면 마디
				String repeat_check = "";
				for (int i = 0; i < ea; i++) {
					repeat += s.charAt(i); // ~ea까지의 글자와
				}
				for (int i = ea; i < ea * 2; i++) {
					repeat_check += s.charAt(i); // ea~ 다음 ea까지의 글자를 비교
				}

				if (repeat.equals(repeat_check)) { // 같으면 마디다.
					check = true;
				}

				if (check) {
					System.out.print("#" + tt + " " + repeat.length());
					System.out.println();
					break;
				}
				ea++;
			}

			tt++;
		}
	}
}