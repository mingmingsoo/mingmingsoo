
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int V = sc.nextInt();
		int E = sc.nextInt();

		List<Integer> list = new ArrayList<>();

		int[][] grid = new int[V + 1][V + 1];
		int[] degree = new int[V + 1];
		int[] result = new int[V];
		int[] ans = new int[V];
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			grid[from][to] = 1;
			degree[to]++;
		}

//		System.out.println(Arrays.toString(degree));
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i < V + 1; i++) {
			if (degree[i] == 0) {
				q.offer(i);
			}
		}
//		System.out.println(q);
		int idx = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			list.add(size);
			for (int k = 0; k < size; k++) {
				int node = q.poll();
				result[idx++] = node;
				for (int i = 1; i < V + 1; i++) {
					if (grid[node][i] == 1) {
						grid[node][i] = 0;
						degree[i]--;
						if (degree[i] == 0) {
							q.add(i);
						}
					}
				}
			}

		}
//		System.out.println(Arrays.toString(result));

		int cnt = 1;
		int start = 0;
		for (int i = 0; i < list.size(); i++) {
			int num = list.get(i);// 3
			for (int j = start; j < start + num; j++) {
				ans[j] = cnt;
			}
			cnt++;
			start += num;
		}
//		System.out.println(Arrays.toString(ans));
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < V; i++) {
			map.put(result[i], ans[i]);
		}
//		System.out.println(map);
		for (int i = 1; i < V+1; i++) {
			System.out.print(map.get(i) + " ");
		}

	}

}
