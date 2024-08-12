

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int x = sc.nextInt();
		int y = sc.nextInt();

		int n = sc.nextInt();
		int[] len_x = new int[n + 1];
		int[] len_y = new int[n + 1];

		for (int i = 0; i < n + 1; i++) {
			int dir = sc.nextInt();
			if (dir == 1) {
				len_x[i] = 0;
				len_y[i] = sc.nextInt();
			} else if (dir == 2) {
				len_x[i] = y;
				len_y[i] = sc.nextInt();
			} else if (dir == 3) {
				len_x[i] = sc.nextInt();
				len_y[i] = 0;
			} else if (dir == 4) {
				len_x[i] = sc.nextInt();
				len_y[i] = x;
			}

		}

//		System.out.println(Arrays.toString(len_x));
//		System.out.println(Arrays.toString(len_y));

		int[][] grid = new int[y + 1][x + 1];
		for (int i = 0; i < x + 1; i++) {
			grid[0][i] = 1;
			grid[y][i] = 1;
		}
		for (int i = 0; i < y + 1; i++) {
			grid[i][0] = 1;
			grid[i][x] = 1;
		}

//		System.out.println(Arrays.deepToString(grid));
		
		int[] clock = new int[n];
		int[] reverse_clock = new int[n];

		// 시계일 때
		int[] row = { 0, -1, 0, 1 };
		int[] col = { -1, 0, 1, 0 };

		for (int i = 0; i < n; i++) {
			int cnt = 0;
			int cur_x = len_x[n];
			int cur_y = len_y[n];
			
			int d = -1;
			if(cur_x == y) {
				d = 0;
			}
			else if(cur_y == 0) {
				d = 1;
			}
			else if(cur_x == 0) {
				d = 2;
			}
			else if(cur_y == x) {
				d = 3;
			}
			
			
			int goal_x = len_x[i]; // 1
			int goal_y = len_y[i]; // 0
			int nr = -1;
			int nc = -1;
			while (nr != goal_x || nc != goal_y) {
				nr = cur_x + row[d];
				nc = cur_y + col[d];
				if (nr < 0 || nr > y || nc < 0 ||nc > x || grid[nr][nc] != 1) {
					d = (d + 1) % 4;
					continue;
				}
				cur_x = nr;
				cur_y = nc;
				cnt++;

			}
			clock[i] = cnt;

		}
		
		// 반시계 일때
		int[] row_reverse = { 0, -1, 0, 1 };
		int[] col_reverse = { 1, 0, -1, 0 };

		for (int i = 0; i < n; i++) {
			int cnt = 0;
			int cur_x = len_x[n];
			int cur_y = len_y[n];
			
			int d = -1;
			if(cur_x == y) {
				d = 0;
			}
			else if(cur_y == x) {
				d = 1;
			}
			else if(cur_x == 0) {
				d = 2;
			}
			else if(cur_y == 0) {
				d = 3;
			}
			
			
			int goal_x = len_x[i]; // 1
			int goal_y = len_y[i]; // 0
			int nr = -1;
			int nc = -1;
			while (nr != goal_x || nc != goal_y) {
				nr = cur_x + row_reverse[d];
				nc = cur_y + col_reverse[d];
				if (nr < 0 || nr > y || nc < 0 ||nc > x || grid[nr][nc] != 1) {
					d = (d + 1) % 4;
					continue;
				}
				cur_x = nr;
				cur_y = nc;
				cnt++;

			}
			reverse_clock[i] = cnt;

		}

//		System.out.println(Arrays.toString(clock));
//		System.out.println(Arrays.toString(reverse_clock));
		int min = 0;
		for(int i = 0; i<n;i++) {
			min+=Math.min(clock[i], reverse_clock[i]);
		}
		System.out.println(min);
	}

}