
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int t = 10;
		int tt  =1;
		
		while(tt<=t) {
			sc.nextInt();
			int n = sc.nextInt();
			int pow = sc.nextInt();
			
			System.out.println("#"+tt+" "+분할정복(n, pow));
			tt++;
		}
	}

	private static int 분할정복(int n, int pow) {

		// 기저조건
		if (pow <= 1) {
			return n;
		}

		if (pow % 2 == 0) {
			int tmp = 분할정복(n, pow / 2);
			return tmp * tmp;
		}

		else {
			int tmp = 분할정복(n, (pow - 1) / 2);
			return tmp * tmp * n;
		}
	}

}
