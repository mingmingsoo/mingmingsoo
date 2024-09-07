

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		d = sc.nextInt();

		arr = new int[m];
		sel = new int[3];
		for (int i = 0; i < m; i++) {
			arr[i] = i;
		}

		xlist_origin = new LinkedList<>();
		ylist_origin = new LinkedList<>();

		int[][] grid = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = sc.nextInt();
			}
		}

		for (int j = 0; j < m; j++) {
			for (int i = n - 1; i >= 0; i--) {
				if (grid[i][j] == 1) {
					xlist_origin.add(i);
					ylist_origin.add(j);
				} else {
					continue;
				}
			}
		}
		enumynum = xlist_origin.size();
//		System.out.println(xlist_origin);
//		System.out.println(ylist_origin);

		ans = 0;
		hanzo(0, 0); // 궁수 구하는 조합
		System.out.println(ans);

	}

	static int ans;
	static int[] arr;
	static int[] sel;
	static int n;
	static int m;
	static int d;
	static int enumynum;
	static List<Integer> xlist_origin;
	static List<Integer> ylist_origin;

	private static void hanzo(int idx, int sidx) {
		if (sidx == 3) {
//			System.out.println(Arrays.toString(sel));
			List<Integer> xlist = new LinkedList<>(xlist_origin);
			List<Integer> ylist = new LinkedList<>(ylist_origin);

			int kill = 0;

			gameout: while (true) {
				// 가장 거리가 가까운 거리 먼저 담고 // 그 다음에 좌표 담기.

				if (xlist.isEmpty()) {
//					System.out.println(kill);
					break gameout;
				}

				if (kill == enumynum) {
					break gameout;
				}

				int[] dists = new int[3];
				for (int i = 0; i < 3; i++) {
					int dist = Integer.MAX_VALUE;
					for (int j = 0; j < xlist.size(); j++) { // 적의 수
						int tmp = Math.abs(n - xlist.get(j)) + Math.abs(sel[i] - ylist.get(j));
						if (dist > tmp) {
							dist = tmp;
							if (dist <= d) { // 최대 사격 거리보다 작거나 같으면 죽일 예정
								dists[i] = tmp;
							} else {
								dists[i] = 100; // 아님 못죽임.
							}
						}
					}
				}
				// 죽일 후보 위치 담는 set 생성.
				Set<Integer> killx = new LinkedHashSet<>();

				con: for (int i = 0; i < 3; i++) {
					for (int j = 0; j < xlist.size(); j++) {
						if (dists[i] == Math.abs(n - xlist.get(j)) + Math.abs(sel[i] - ylist.get(j))) {
							killx.add(j);
							continue con;
						}
					}
				}

				kill += killx.size();

				List<Integer> tmpxlist = new LinkedList<>();
				List<Integer> tmpylist = new LinkedList<>();

				for (int j = 0; j < xlist.size(); j++) { // 여기가 꼬일 것 같은데 확인.
					if (!killx.contains(j)) {
						tmpxlist.add(xlist.get(j));
						tmpylist.add(ylist.get(j));
					}
				}

				xlist = tmpxlist;
				ylist = tmpylist;

				// 공격 끝. 내려오기
				for (int i = 0; i < xlist.size(); i++) {
					xlist.set(i, xlist.get(i) + 1);

				}
				// 침략하기

				for (int i = 0; i < xlist.size(); i++) {
					if (xlist.get(i) == n) {
						xlist.remove(i);
						ylist.remove(i);
						i--;

					}
				}

			}
			ans = Math.max(ans, kill);
			return;
		}
		if (idx == m) {
			return;
		}

		sel[sidx] = arr[idx];
		hanzo(idx + 1, sidx + 1);
		hanzo(idx + 1, sidx);
	}
}
