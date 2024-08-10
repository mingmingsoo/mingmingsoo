import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		for (int t = 0; t < 4; t++) {

			char ans = ' ';

			int[] arr1 = new int[4];
			int[] arr2 = new int[4];

			for (int i = 0; i < 4; i++) {
				arr1[i] = sc.nextInt();
			}

			for (int i = 0; i < 4; i++) {
				arr2[i] = sc.nextInt();
			}

			int x1 = arr1[0];
			int y1 = arr1[1];
			int p1 = arr1[2];
			int q1 = arr1[3];

			int x2 = arr2[0];
			int y2 = arr2[1];
			int p2 = arr2[2];
			int q2 = arr2[3];

			if ((x2 > p1) || (x1 > p2) || (y1 > q2) || (y2 > q1)) {
				ans = 'd';
			}

			else if ((x2 == p1 && y1 == q2) || (x2 == p1 && y2 == q1) || (x1 == p2 && y1 == q2)
					|| (x1 == p2 && y2 == q1)) {
				ans = 'c';
			}

			else if ((p1 == x2) || (p2 == x1) || (q2 == y1) || (y2 == q1)) {
				ans = 'b';
			}

			else {
				ans = 'a';
			}

			System.out.println(ans);
		}

	}

}