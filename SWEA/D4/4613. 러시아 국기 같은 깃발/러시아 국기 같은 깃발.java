
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// 모든 경우의 수 따지기
		// 첫번째 줄은 무조건 W, 마지막 줄은 무조건 R
		// W는 1부터 될 수 있음
		// B는 W+1부터 N-2까지 될 수 있음
		// R는 B+1부터 N-1까지

		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		int tt =1;
		while(tt<=t) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			char[][] grid = new char[n][m];
			for (int i = 0; i < n; i++) {
				String line = sc.next();
				for (int j = 0; j < m; j++) {
					grid[i][j] = line.charAt(j);
				}
			}
			
			int min = Integer.MAX_VALUE;
			for (int i = 1; i < n - 1; i++) {
				for (int j = i + 1; j < n; j++) {
					int cnt = 0;
					cnt += change_color(grid[0], 'W');
					for (int w = 1; w < i; w++) {
						cnt += change_color(grid[w], 'W');
					}
					for (int b = i; b < j; b++) {
						cnt += change_color(grid[b], 'B');
					}
					for (int r = j; r < n-1; r++) {
						cnt += change_color(grid[r], 'R');
					}
					
					cnt += change_color(grid[n - 1], 'R');
					min = Math.min(cnt, min);
					
				}
			}
			System.out.println("#"+tt+" "+min);
			tt++;
		}

	}

	private static int change_color(char[] cha, char c) {
		int change = 0;
		for (int j = 0; j<cha.length;j++) {
			if(cha[j]!=c) {
				change++;
			}
		}
		return change;
	}

}