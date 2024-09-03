import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;
		while (tt <= t) {
			fee = new int[4]; // 요금
			days = new int[12]; // 이용 계획

			for (int i = 0; i < 4; i++) {
				fee[i] = sc.nextInt();
			}

			for (int i = 0; i < 12; i++) {
				days[i] = sc.nextInt();
			}

			ans = fee[3]; // 일년치 젤 큰 값

			for (int i = 0; i < 12; i++) {
				if (days[i] != 0) {
					dfs(i + 1, 0); // 시작하는 달에 dfs! -> 연산이 조금이나마 줄으라고...
					break;
				}
			}
			System.out.println("#" + tt + " " + ans);
			tt++;
		}

	}

	static int[] days;
	static int[] fee;
	static int ans;

	private static void dfs(int month, int cost) {
		if (month > 12) {
			if (ans > cost) {
				ans = cost;
			}
			return;
		}
		// 1일 일때
		dfs(month + 1, cost + days[month - 1] * fee[0]);
		// 1달 일때
		dfs(month + 1, cost + fee[1]);
		// 3달 일때
		dfs(month + 3, cost + fee[2]);

	}

}
