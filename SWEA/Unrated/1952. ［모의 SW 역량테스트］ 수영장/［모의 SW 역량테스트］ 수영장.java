
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		int tt = 1;
		StringBuilder sb = new StringBuilder();
		
		while(tt<=t) {
			sb.append("#"+tt+" ");
			int fees[] = new int[4];
			int plans[] = new int[13];

			for (int i = 0; i < 4; i++) {
				fees[i] = sc.nextInt();
			}
			for (int i = 1; i < 13; i++) {
				plans[i] = sc.nextInt();
			}

			int[] dp = new int[13];

			for (int i = 1; i < 13; i++) {
				// 일일권일때
				dp[i] = dp[i - 1] + fees[0] * plans[i];

				// 한달
				dp[i] = Math.min(dp[i], dp[i - 1] + fees[1]);// 예를들어 일일권 이용금액 vs 한달치 이용금액

				// 세달
				if (i >= 3) {
					dp[i] = Math.min(dp[i], dp[i - 3] + fees[2]); // 3달치 이용금액이 적으면 3달치 pick
				}

				// 1년
				if (i == 12)
					dp[i] = Math.min(dp[i], fees[3]);
			}
			sb.append(dp[12]+"\n");
			tt++;
		}
		System.out.println(sb);


	}

}
