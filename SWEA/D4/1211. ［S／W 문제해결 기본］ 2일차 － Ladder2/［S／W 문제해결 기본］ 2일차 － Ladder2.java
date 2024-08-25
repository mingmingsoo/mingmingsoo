
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = 10;
		int tt = 1;
		while (tt <= t) {
			sc.nextInt();
			int size = 100;

			int[][] grid = new int[size][size];

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					grid[i][j] = sc.nextInt();
				}
			}
			int min = Integer.MAX_VALUE;
			int ii = -1;
			int[] row = { 1, 0, 0 };
			int[] col = { 0, -1, 1 };
			for (int j = 0; j < size; j++) {
				int start_i = 0;
				if (grid[start_i][j] == 1) {
					int start_j = j;
					int cnt = 0;
					while (start_i < size - 1) {
						if (start_j + col[1] >= 0 && start_j + col[1] < size
								&& grid[start_i + row[1]][start_j + col[1]] == 1) {
							while (start_j + col[1] >= 0 && start_j + col[1] < size
									&& grid[start_i + row[1]][start_j + col[1]] == 1) {
								start_i += row[1];
								start_j += col[1];
								cnt++;
							}

						} else if (start_j + col[2] >= 0 && start_j + col[2] < size
								&& grid[start_i + row[2]][start_j + col[2]] == 1) {
							while (start_j + col[2] >= 0 && start_j + col[2] < size
									&& grid[start_i + row[2]][start_j + col[2]] == 1) {
								start_i += row[2];
								start_j += col[2];
								cnt++;
							}

						}

						cnt++;
						start_i += row[0];
						start_j += col[0];

					}

					if (cnt < min) {
						min = cnt;
						ii = j;

					}

				}

			}
			System.out.println("#" + tt + " " + ii);
			tt++;
		}

	}

}
