
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			n = sc.nextInt();

			arr = new int[n];
			// arr의 idx는 행, 값은 열을 넣을거임
//		예를들어
//		[2,0,3,1] : 행
//		 0 1 2 3 : 열
			cnt = 0;
			nq(0); // nqueen, 첫 idx =0;
			System.out.println("#" + tt + " " + cnt);
			tt++;
		}

	}

	static int n;
	static int cnt;
	static int[] arr;

	private static void nq(int idx) {
		if (idx == n) { // 공격받지 않고 arr가 다 채워졌다면
			cnt++; // 경우의수 +1
			return;
		}

		for (int i = 0; i < n; i++) {
			arr[idx] = i;
			if (location(idx)) { // 위치할 수 있다면
				nq(idx + 1); // 다음으로 넘어가기
			}
		}

	}

	private static boolean location(int idx) {

		for (int i = 0; i < idx; i++) {
			if (arr[i] == arr[idx]) { // 같은 선상(행에 있으면) 공격 받음
				return false;
			} else if (Math.abs(i - idx) == Math.abs(arr[i] - arr[idx])) {
				return false; // 대각선에 위치해 있어도 공격받음
				// 0,0 1,1 2,2 이면
//				[0,1,2] 혹은 [2,1,0]
//				 0,1,2       2,1,0  -> 즉 인덱스 차 = 값 차
			}
		}

		return true;

	}

}
