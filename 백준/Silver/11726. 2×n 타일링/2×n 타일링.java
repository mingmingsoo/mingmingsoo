
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt()+1;
		
		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 1;
		for (int i = 2; i < n + 1; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2])%10007;

		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[n]);
		
	}

}
