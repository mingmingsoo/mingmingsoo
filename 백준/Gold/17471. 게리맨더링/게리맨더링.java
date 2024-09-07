

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// 부분집합으로 두 구역 나누고
		// 나뉜 구역이 연결 되어있으면
		// 최솟값 갱신.
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		arr = new int[n];
		sel = new int[n];

		people = new int[n + 1];
		for (int i = 0; i < n; i++) {
			people[i + 1] = sc.nextInt();
			arr[i] = i + 1;
		}
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(people));

		list = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < n; i++) {
			int tmp = sc.nextInt(); // 간선 갯수
			for (int j = 0; j < tmp; j++) {
				list.get(i + 1).add(sc.nextInt());
			}
		}
//		System.out.println(list);
		ans = Integer.MAX_VALUE;

		calcul(0);
		if (ans == Integer.MAX_VALUE) {
			ans = -1;
		}
		System.out.println(ans);

	}

	static int ans;
	static int n;
	static int[] sel;
	static int[] arr;
	static List<Integer> alist;
	static List<Integer> blist;
	static int[] people;
	static int[] parents;
	static List<List<Integer>> list;

	private static void calcul(int idx) {
		if (sel[0] >= n / 2) {
			return;
		}

		if (idx == n) {
			alist = new ArrayList<>();
			blist = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				if (sel[i] != 0) {
					alist.add(arr[i]);
				} else {
					blist.add(arr[i]);
				}
			}
//			System.out.println(alist + " / " + blist);
			// 만약 alist 처음 값이 4면 안봐도됨. alist 크기가 6이나 0이면 안봐도됨.
			if (alist.size() > 0 && alist.size() < n && alist.get(0) <= n / 2 + 1 // 홀수 일때 생각해서...
					&& (connect(alist) && connect(blist))) {
				// 최솟값 구하는 로직
				int asum = 0;
				int bsum = 0;
				for (int i = 0; i < alist.size(); i++) {
					asum += people[alist.get(i)];
				}
				for (int i = 0; i < blist.size(); i++) {
					bsum += people[blist.get(i)];
				}
				int minus = Math.abs(asum - bsum);
				ans = Math.min(ans, minus);
			}
			return;
		}

		sel[idx] = 1;
		calcul(idx + 1);
		sel[idx] = 0;
		calcul(idx + 1);

	}

	// 연결되어 있는지 확인하는 로직
	private static boolean connect(List<Integer> ablist) {

		boolean[] visited = new boolean[n + 1];
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(ablist.get(0));
		visited[ablist.get(0)] = true;

		int cnt = 0;

		while (!queue.isEmpty()) {
			int from = queue.poll();

			for (int to : list.get(from)) {
				if (ablist.contains(to) && !visited[to]) {
					visited[to] = true;
					queue.offer(to);
					cnt++;
				}
			}
		}

		return cnt == ablist.size() - 1;
	}
}
