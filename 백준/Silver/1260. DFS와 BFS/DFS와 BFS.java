
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(); // 정점 갯수
		int m = sc.nextInt(); // 간선 갯수
		int v = sc.nextInt(); // 정점 시작 번호

		dfs_visited = new boolean[n + 1];
		bfs_visited = new boolean[n + 1];

		grid = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			grid[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			grid[a].add(b);
			grid[b].add(a);

		}

//		System.out.println(Arrays.deepToString(grid));
		
		for(int i =1; i<n+1;i++) {
			Collections.sort(grid[i]);
		}

		dfs(v);
		System.out.println();
		bfs(v);

	}

	static List<Integer>[] grid;
	static boolean[] dfs_visited;
	static boolean[] bfs_visited;

	private static void dfs(int v) {
		dfs_visited[v] = true;

		System.out.print(v + " ");

		for (int node : grid[v]) {
			if (!dfs_visited[node]) {
				dfs(node);
			}
		}
	}

	private static void bfs(int v) {
		Queue<Integer> q = new LinkedList<Integer>();

		q.offer(v);

		bfs_visited[v] = true;

		while (!q.isEmpty()) {
			int nodeIndex = q.poll();
			System.out.print(nodeIndex + " ");
			for (int i = 0; i < grid[nodeIndex].size(); i++) {
				int tmp = grid[nodeIndex].get(i);
				if (!bfs_visited[tmp]) {
					bfs_visited[tmp] = true;
					q.offer(tmp);
				}
			}
		}

	}

}
