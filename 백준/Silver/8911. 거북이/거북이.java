

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			String tmp = sc.next();

			char[] orders = tmp.toCharArray();
			int d = 0;
			int x = 0;
			int y = 0;

			// 0 : 북
			// 1 : 동
			// 2 : 남
			// 3 : 서

			int xmin = 0, ymin = 0, xmax = 0, ymax = 0;
			for (int i = 0; i < orders.length; i++) {
				if (orders[i] == 'F') {
					if (d == 0) {
						++y;
					} else if (d == 1) {
						++x;
					} else if (d == 2) {
						--y;
					} else if (d == 3) {
						--x;
					}
				} else if (orders[i] == 'B') {
					if (d == 0) {
						--y;
					} else if (d == 1) {
						--x;
					} else if (d == 2) {
						++y;
					} else if (d == 3) {
						++x;
					}

				} else if (orders[i] == 'L') {
					if (d == 0) {
						d = 3;
					} else if (d == 1) {
						d = 0;
					} else if (d == 2) {
						d = 1;
					} else if (d == 3) {
						d = 2;
					}
				} else if (orders[i] == 'R') {
					if (d == 0) {
						d = 1;
					} else if (d == 1) {
						d = 2;
					} else if (d == 2) {
						d = 3;
					} else if (d == 3) {
						d = 0;
					}
				}
				if (xmin > x) {
					xmin = x;
				}
				if (ymin > y) {
					ymin = y;
				}
				if (xmax < x) {
					xmax = x;
				}
				if (ymax < y) {
					ymax = y;
				}
			}

//			System.out.println(xmin + " " + xmax);
//			System.out.println(ymin + " " + ymax);
			System.out.println((xmax-xmin)*(ymax-ymin));
			tt++;
		}

	}

}
