import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(); // 동전 갯수
		int k = sc.nextInt(); // 만들어야할 동전

		int[] coins = new int[n];
		for (int i = 0; i < n; i++) {
			coins[i] = sc.nextInt();
		}

		// 모든 경우의 수

		// 테케의 coins는?
		// 0 1 2 3 4 5 6 7 8 9 10
		// 0 1 2 2 3 4 5 6 7 8 10
//		1: 1
//		2: 11 2
//		3: 111 21
//		4 : 1111 211 22
//		5 : 11111 2111 221 5
//		6 : 111111 21111 2211 222 15
//		7 : 1111111 211111 22111 2221 115 25
//		8 : 11111111 2111111 221111 22211 2222 1115 125
//		9 : 111111111 21111111 2211111 222111 22221 11115 1125 225
//		10: 1111111111, 211111111, 22111111, 2221111, 222211, 22222, 511111, 52111, 5221, 55

		int[] dp = new int[k + 1];
		dp[0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < k+1; j++) {
				if(coins[i]<=j) {
					dp[j] += dp[j-coins[i]];
//					System.out.println(j-coins[i]);
//					System.out.println(Arrays.toString(dp));
				}
			}
		}
		System.out.println(dp[k]);
	}

}
