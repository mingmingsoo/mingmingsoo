

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		int tt = 1;
		
		int[] row = {-1,1,0,0};
		int[] col = {0,0,1,-1};
		
		StringBuilder sb = new StringBuilder();
		while(tt<=t) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			Queue<int[]> q = new LinkedList<>();
			
			char[][] grid = new char[n][m];
			boolean[][] visited = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				String line = sc.next();
				for (int j = 0; j < m; j++) {
					grid[i][j] = line.charAt(j);
					if(grid[i][j]=='W') {
						q.add(new int[] {i,j,0});
						visited[i][j] = true;
					}
				}
			}
			
			int ans = 0;
			
			while(!q.isEmpty()) {
				int size = q.size();
				for(int i =0; i<size;i++) {
					int[] node = q.poll();
					int x = node[0];
					int y = node[1];
					int cnt = node[2];
					if(grid[x][y]=='L') {
						ans += cnt;
					}
					for(int k = 0; k<4; k++) {
						int nr = x+row[k];
						int nc = y+col[k];
						if(nr>=0 && nr<n && nc>=0 && nc<m && !visited[nr][nc]) {
							q.add(new int[] {nr,nc, cnt+1});
							visited[nr][nc] = true;
						}
					}
				}
			}
			sb.append("#"+tt+" "+ans+"\n");
			tt++;
		}
		System.out.println(sb);
		
	}


}
