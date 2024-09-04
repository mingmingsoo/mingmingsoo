

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int tt =1;
		
		while(tt<=t) {
			int V = sc.nextInt();
			int E = sc.nextInt();

			list = new ArrayList<>();

			for (int i = 0; i <= V; i++) {
				list.add(new ArrayList<>());
			}
			visited = new boolean[V + 1];

			for (int i = 0; i < E; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				list.get(from).add(to);
				list.get(to).add(from);
			}
//		System.out.println(list);

			int cnt = 0;
			for (int i = 1; i <= V; i++) {
				if (!visited[i]) {
					dfs(i);
					cnt++;
				}
			}
			System.out.println("#"+tt+" "+cnt);
			tt++;
		}

	}

	static boolean visited[];
	static List<List<Integer>> list;

	private static void dfs(int i) {
		visited[i] = true;
		for (int ele : list.get(i)) {
			if (!visited[ele]) {
				visited[ele] = true;
				dfs(ele);
			}
		}

	}

}
