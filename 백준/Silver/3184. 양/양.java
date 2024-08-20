

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int n;
	static int m;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		char[][] grid = new char[n][m];

		for (int i = 0; i < n; i++) {
			String line = sc.next();
			for (int j = 0; j < m; j++) {
				grid[i][j] = line.charAt(j);
			}

		}
		int ans_o = 0;
		int ans_v = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j]=='o'||grid[i][j]=='v') {
					O_cnt = 0;
					V_cnt = 0;
					dfs(grid, i, j);
					if(O_cnt>V_cnt) {
						ans_o+= O_cnt;
					}
					else {
						ans_v +=V_cnt;
					}
				}
			}
		}
		System.out.println(ans_o+" "+ans_v);

	}
	static int O_cnt;
	static int V_cnt;

	private static void dfs(char[][] grid, int i, int j) {
		int[] row = { 1, -1, 0, 0 };
		int[] col = { 0, 0, 1, -1 };
		
		if(grid[i][j]=='#') {
			return;
		}

		
		if(grid[i][j]=='o') {
			O_cnt++;
		}
		else if(grid[i][j]=='v') {
			V_cnt++;
		}
		
		grid[i][j] = '#'; // 방문 처리
		
		for(int k = 0; k<4;k++) {
			int nr = i+row[k];
			int nc = j+col[k];
			if(nr>=0&&nr<n&&nc>=0&&nc<m) dfs(grid,nr,nc);
		}
		
		

		
	}

}
