

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		l0 = sc.nextInt();
		l1 = sc.nextInt();
		l2 = sc.nextInt();
		int max = Math.max(l0, l1);
		max = Math.max(max, l2);

		visited = new boolean[max + 1][max + 1][max + 1];

		int[] state = new int[] { 0, 0, l2 };

		list = new ArrayList<>();

		dfs(state);

		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for (int ele : list) {
			sb.append(ele + " ");
		}
		System.out.println(sb);

	}

	static List<Integer> list;
	static int max;
	static boolean[][][] visited;
	static int l0;
	static int l1;
	static int l2;

	private static void dfs(int[] state) {
		if (visited[state[0]][state[1]][state[2]]) {
			return;
		}
		visited[state[0]][state[1]][state[2]] = true;

		if (state[0] == 0) {
			list.add(state[2]);
		}

		move(state, 2, 0, l0);
		move(state, 2, 1, l1);
		move(state, 1, 0, l0);
		move(state, 1, 2, l2);
		move(state, 0, 1, l1);
		move(state, 0, 2, l2);

	}

	private static void move(int[] state, int from, int to, int limit) {
		int[] tmparr = state.clone();

		if (tmparr[from] + tmparr[to] <= limit) {
			tmparr[to] += tmparr[from];
			tmparr[from] = 0;

		} else {
			tmparr[from] -= (limit - tmparr[to]);
			tmparr[to] = limit;
			// 0 8 2 -> 0 9 1

		}

		dfs(tmparr);

	}

}
