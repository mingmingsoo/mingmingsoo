
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int[] weight = new int[n + 1];
		int[] cost = new int[n + 1];

		for (int i = 1; i < n + 1; i++) {
			weight[i] = sc.nextInt();
			cost[i] = sc.nextInt();
		}
		
		int[] dp = new int[n+2];

		for(int i = 1 ; i<n+1; i++) {
			dp[i] = Math.max(dp[i-1], dp[i]);
			if(i+weight[i]<=n+1) {
				dp[i+weight[i]] = Math.max(dp[i+weight[i]], cost[i]+dp[i]);
			}
		}
//		System.out.println(Arrays.toString(dp));
		int ans = 0;
		for(int ele:dp) {
			ans = Math.max(ans, ele);
		}
		System.out.println(ans);

	}

}
