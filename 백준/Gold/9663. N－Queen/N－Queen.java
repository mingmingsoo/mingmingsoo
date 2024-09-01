

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();

		cnt = 0;
		arr = new int[n];
		// 열 번호를 idx로 행 번로를 arr값으로 넣을거임
//		(0,0)(1,3)(2,4)(3,3) 이면
//		arr = {0 3 4 3} 
//		 idx = 0 1 2 4 이런식으로
//		퀸은 어차피 가로 세로 대각선에 못놓으므로 열단위로 넘어가는 것
		// 같은 행 열 위치에 퀸을 놓을 수 없다.
		nq(0);
		System.out.println(cnt);

	}

	static int n;
	static int cnt;
	static int[] arr;

	private static void nq(int idx) {
		if (idx == n) { // 배열이 모두 채워졌으면 공격받지 않는 위치로만 배열이 채워진 것임.
			cnt++;
			return;
		}

		for (int i = 0; i < n; i++) { // n번 반복할거임(배열의 크기가 n이니까)
			arr[idx] = i; // 첫 값으로 0 넣어주고
			if (check(idx)) { // 첫 퀸은 0부터 들어갈 것임, 그리고 놓을 수 있는 위치면
				nq(idx + 1); // nq(idx+1) 다음값
			}
		}

	}

	private static boolean check(int idx) {
		for (int i = 0; i < idx; i++) {
			if (arr[i] == arr[idx]) { // 같은 열이면 실패(같은 행은 이미 1차원 배열이므로 걸러졌음)
				return false;
			} else if (Math.abs(i - idx) == Math.abs(arr[i] - arr[idx])) {
				return false; // 같은 대각선이면 실패
			}
		}
		return true; // 그게 아니라면 공격 성공한 거임
	}

}
