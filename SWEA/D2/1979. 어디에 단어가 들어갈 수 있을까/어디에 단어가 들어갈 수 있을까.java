import java.util.Scanner;
public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int tt =1;
		while(tt<=t) {
			int size = sc.nextInt();
			int k = sc.nextInt();
			
			int[][] grid = new int[size + 2][size + 2];
			for (int i = 1; i < size + 1; i++) {
				for (int j = 1; j < size + 1; j++) {
					grid[i][j] = sc.nextInt();
				}
			}
			
			int ans = 0;
			for (int i = 1; i < size + 1; i++) {
				for (int j = 1; j < size + 1; j++) {
					boolean bool = false;
					if (grid[i][j] == 1 && grid[i][j - 1] == 0) {
						int nr = i;
						int nc = j + 1;
						int cnt = 1;
						while (grid[nr][nc] == 1) {
							nc++;
							cnt++;
						}
						if (cnt == k) {
							bool = true;
						}
					}
					if (bool) {
						ans++;
					}
				}
			}
			for (int i = 1; i < size + 1; i++) {
				for (int j = 1; j < size + 1; j++) {
					boolean bool = false;
					if (grid[i][j] == 1 && grid[i-1][j] == 0) {
						int nr = i+1;
						int nc = j;
						int cnt = 1;
						while (grid[nr][nc] == 1) {
							nr++;
							cnt++;
						}
						if (cnt == k) {
							bool = true;
						}
					}
					if (bool) {
						ans++;
					}
				}
			}
			System.out.println("#"+tt+" "+ans);
			tt++;
		}
	}
}