import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int n;
	static int m;
	public static void main(String[] args) {

		// 맨 첫줄은 무조건 흰색
		// 맨 마지막 줄은 무조건 빨간색
		// 블루는 흰색+1, red-1
		// 바꿔야 하는 숫자는 최소로

		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		int tt = 1;
		
		while(tt<=t) {
			n = sc.nextInt();
			m = sc.nextInt();
			
			char[][] grid = new char[n][m];
			for (int i = 0; i < n; i++) {
				String line = sc.next();
				for (int j = 0; j < m; j++) {
					grid[i][j] = line.charAt(j);
				}
			}
//		System.out.println(Arrays.deepToString(grid));
			
			// W와 B가 가능한 범위 모두 따져주기 (R은 두 문자에 의해 배치되므로)
			int min = Integer.MAX_VALUE;
			for (int i = 1; i < n - 1; i++) { // W
				for (int j = i + 1; j < n; j++) { // B
					// i = 0일땐 무조건 w, 바꿔야 하는 갯수 계산
					int cnt = 0;
					cnt+= change_color(grid[0],'W');
					// w
					for(int k = 1; k<i;k++) {
						cnt+= change_color(grid[k],'W');
					}
					// b
					for(int k = i; k<j;k++) {
						cnt+= change_color(grid[k],'B');
					}
					// r
					for(int k = j; k<n-1;k++) {
						cnt+= change_color(grid[k],'R');
					}
					// i = n-1일땐 무조건 r, 바꿔야 하는 갯수 계산
					cnt+= change_color(grid[n-1],'R');
					min = Math.min(cnt, min);
				}
			}
			System.out.println("#"+tt+" "+min);
			tt++;
		}


	}

	private static int change_color(char[] cha, char c) {
		int change = 0;
		for(int j = 0; j<m;j++) {
			if(cha[j]!=c) {
				change++;
			}
		}
		return change;
	}

}
