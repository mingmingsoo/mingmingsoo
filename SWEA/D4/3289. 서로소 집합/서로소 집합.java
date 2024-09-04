

import java.util.Scanner;

public class Solution {

	static int[] parents; // 대표자를 지정할 배열
	static int n;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;

		while (tt <= t) {
			StringBuilder sb = new StringBuilder(); // 출력 결과 담기
			n = sc.nextInt(); // 정점 갯수 7개
			int m = sc.nextInt(); // 연산 갯수 8개
			parents = new int[n + 1]; // 정점 1번부터 나ㅗㅇ므로

			makeSet(); // 집합 만들기

			for (int i = 0; i < m; i++) {
				int order = sc.nextInt();
				int a = sc.nextInt();
				int b = sc.nextInt();

				if (order == 0) { // 합치기
					union(a, b);
				} else { // 두 집합이 같으면 1 다르면 0
					int aparent = findSet(a);
					int bparent = findSet(b);
					if (aparent == bparent) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}

			}

			System.out.println("#" + tt + " " + sb);
			tt++;
		} // 테케

	}

	private static void union(int x, int y) {
		int xroot = findSet(x);
		int yroot = findSet(y);

		if (xroot != yroot) {
			parents[findSet(y)] = findSet(x);
		} else {
			return;
		}
	}

	private static int findSet(int x) {
		if (x != parents[x]) {
			parents[x] = findSet(parents[x]);
		}
		return parents[x];
	}

	private static void makeSet() {
		for (int i = 1; i < n + 1; i++) {
			parents[i] = i;
		}
	}

}
