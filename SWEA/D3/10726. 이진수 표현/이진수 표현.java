
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;
		while (tt <= t) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			// n이 4이고, m이 47일 때 0010 1111 끝 4자리 1111가 모두 1이므로 ON임
			// 어떤 숫자 X(=0000 1111)와 m을 &연산자 했을 때 X와 같으면 ON!
			
			// OFF일땐
			// n이 4이고, m이 30일 때 0001 1100 끝 4자리 1100이 모두 1이 아니므로 F임
			// 어떤숫자 X(= 0000 1111)과 m을 &연산자 했을 때 X와 같지 않으므로 OFF
			
			// 어떤 숫자 X 만들기 0001을 N만큼 왼쪽 이동시켜서 0001 0000만들고 1빼면
			// 0000 1111이 된다.
			String ans = "OFF";
			int x = (1 << n) - 1;
			if ((x & m) == x) {
				ans = "ON";
			}
			
			System.out.println("#"+tt+" "+ans);
			tt++;

		}
	}
}
