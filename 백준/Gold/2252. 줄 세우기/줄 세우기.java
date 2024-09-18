

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int V = sc.nextInt(); // 정점 수
		int E = sc.nextInt(); // 간선 수

		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < V + 1; i++) {
			adj.add(new ArrayList<>());
		}

		int[] degree = new int[V + 1];
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adj.get(from).add(to);
			degree[to]++;
		}

//		System.out.println(adj);
//		System.out.println(Arrays.toString(degree));

		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i < V + 1; i++) {
			if (degree[i] == 0) {
				q.add(i);
			}
		}
//		System.out.println(q);

		StringBuilder sb = new StringBuilder();
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int node = q.poll();
				sb.append(node + " ");
				List<Integer> neighbor = adj.get(node);
				for (int ele : neighbor) {
					degree[ele]--;
					if (degree[ele] == 0) {
						q.add(ele);
					}
				}
			}
		}
		System.out.println(sb);

	}

}
