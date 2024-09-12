

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		int tt =1 ;
		
		while(tt<=t) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			
			int[] score = new int[n + 1];
			int[] cal = new int[n + 1];
			
			for (int i = 1; i < n + 1; i++) {
				score[i] = sc.nextInt();
				cal[i] = sc.nextInt();
			}
			int[] dp = new int[k + 1];
			
			for (int i = 1; i < n + 1; i++) {
				for (int j = k; j >= cal[i]; j--) {
					dp[j] = Math.max(dp[j], score[i] + dp[j - cal[i]]);
				}
			}
			System.out.println("#"+tt+" "+dp[k]);
			
			tt++;
		}


	}

}
