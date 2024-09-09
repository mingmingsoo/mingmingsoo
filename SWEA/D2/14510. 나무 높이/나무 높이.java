
import java.util.Scanner;

public class Solution {
	public static void main(String args[]) {

		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		int tt = 1;
		
		while(tt<=t) {
			int n = sc.nextInt();

			int[] arr = new int[n];

			int max = 0;
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
				if (arr[i] > max) {
					max = arr[i];
				}
			}

			int one = 0;
			int two = 0;
			for (int i = 0; i < n; i++) {
				int grow = max - arr[i];
				if (grow % 2 == 1) { // grow가 홀수면 one ++
					one++;
				}
				two += grow / 2; // two는 항상 grow/2 ++;
			}
			if (two > one) {
				while (Math.abs(two - one) > 1) { // 경우의 수 차이가 1 될때까지~
					one += 2; // one은 2씩 +
					two--; // two 1씩 -
				}
			}
			int ans = 0;
			if (one > two) { // one 갯수가 더 많으면
				ans = 2 * one - 1; // 2one-1
			} else if (one <= two) {
				ans = 2 * two; // two 갯수가 더 많거나 같으면 2two
			}
			System.out.println("#"+tt+" "+ans);
			tt++;
		}


	}
}