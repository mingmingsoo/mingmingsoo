
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		int tt =1;
		
		while(tt<=t) {
			int k = sc.nextInt();
			int n = sc.nextInt();
			
			int[][] grid = new int[k+1][n+1];
			for(int j = 0; j<n+1;j++) {
				grid[0][j] = j;
			}
			
			for(int i = 1; i<k+1;i++) {
				for(int j =0; j<n+1;j++) {
					for(int w =0; w<=j;w++) {
						grid[i][j] += grid[i-1][w];
					}
					
				}
			}
			
//		System.out.println(Arrays.deepToString(grid));
			System.out.println(grid[k][n]);
			tt++;
		}
		
	}

}
