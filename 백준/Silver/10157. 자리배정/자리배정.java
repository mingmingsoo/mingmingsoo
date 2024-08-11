

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int c = sc.nextInt(); // 이게 j
		int r = sc.nextInt(); // 이게 i
		int k = sc.nextInt();

		int[] row = { -1, 0, 1, 0 };
		int[] col = { 0, 1, 0, -1 };

		int cnt = 1;
		int i = r - 1;
		int j = 0;
		int d = 0;
		int[][] grid = new int[r][c];
		grid[i][j] = cnt++;
		while (cnt <= r * c) {
			int nr = i + row[d];
			int nc = j + col[d];
			if (nr < 0 || nr >= r || nc < 0 || nc >= c || grid[nr][nc] != 0) {
				d = (d + 1) % 4;
				continue;
			}
			grid[nr][nc] = cnt++;
			i = nr;
			j = nc;

		}
		int x = 0;
		int y = 0;
		boolean bool = false;
		for (int ii = 0; ii < r; ii++) {
			for (int jj = 0; jj < c; jj++) {
				if (grid[ii][jj] == k) {
					bool = true;
					x = jj+1;
					y =r - ii;
				} 
			}
		}
		if(bool) {
			System.out.println(x+" "+y);
		}
		else {
			System.out.println(0);
		}
	}

}
