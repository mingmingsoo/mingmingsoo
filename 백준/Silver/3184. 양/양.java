
import java.util.Scanner;

public class Main {
	
	static int n;
	static int m;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		char[][] grid = new char[n][m];
		
		for(int i = 0; i<n; i++) {
			String s = sc.next();
			for(int j = 0; j<m;j++) {
				grid[i][j] = s.charAt(j);
			}
		}
		
		// v이거나 o면 dfs 탐색 -> 탐색했으면 #으로 바꿔주기
		// # 만나면 나가기

		int o_total = 0;
		int v_total = 0;
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<m;j++) {
				if(grid[i][j]=='v'||grid[i][j]=='o') {
					o_cnt = 0;
					v_cnt = 0;
					dfs(grid, i, j);
					if(o_cnt>v_cnt) {
						v_cnt = 0;
					}
					else {
						o_cnt = 0;
					}
					o_total += o_cnt;
					v_total += v_cnt;
				}
			}
		}
		System.out.println(o_total+" "+v_total);


	}
	static int o_cnt;
	static int v_cnt;

	private static void dfs(char[][] grid, int i, int j) {
		
		if(grid[i][j]=='#') {
			return;
		}
		
		
		if(grid[i][j]=='o') {
			o_cnt++;
		}
		else if(grid[i][j]=='v') {
			v_cnt++;
		}
		
		grid[i][j] = '#'; //방문 처리
		
		int[] row = {1,-1,0,0};
		int[] col = {0,0,1,-1};
		
		for(int k = 0; k<4;k++) {
			int nr = i+row[k];
			int nc = j+col[k];
			
			if(nr>=0&& nr<n&&nc>=0&& nc<m) {
				dfs(grid, nr, nc);
			}
			
		}
		
	}

}
