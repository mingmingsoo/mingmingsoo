

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		int tt = 1;
		while(tt<=t) {
			n = sc.nextInt();
			arr = new int[n];
			cnt = 0;
			nq(0);
			System.out.println("#"+tt+" " +cnt);
			tt++;
		}
	}

	static int n;
	static int arr[];
	static int cnt;

	private static void nq(int idx) {
		if (idx == n) {
			cnt++;
			return;
		}

		for (int i = 0; i < n; i++) {
			arr[idx] = i;
			if (check(idx)) {
				nq(idx + 1);
			}
		}

	}

	private static boolean check(int idx) {
		for(int i = 0; i<idx;i++) {
			if(arr[i]== arr[idx]) {
				return false;
			}
			else if(Math.abs(i-idx)==Math.abs(arr[i]-arr[idx])) {
				return false;
			}
		}
		return true;
	}

}