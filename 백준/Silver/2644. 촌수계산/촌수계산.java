
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int V = sc.nextInt(); // 정점 갯수

		int p1 = sc.nextInt();
		int p2 = sc.nextInt();
		// p1과 p2의 촌수 따지기

		int E = sc.nextInt(); // 간선 갯수

		list = new ArrayList<>();
		visited = new boolean[V + 1];

		for (int i = 0; i < V + 1; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			list.get(a).add(b);
			list.get(b).add(a);
		}
//		System.out.println(list);
		int ans = bfs(p1, p2);
		System.out.println(ans);

	}

	static List<List<Integer>> list;
	static boolean[] visited;

	private static int bfs(int p1, int p2) {

		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { p1, 0 });
		visited[p1] = true;
		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				int[] node = q.poll(); // 7
				if (node[0] == p2) {
					return node[1];
				}
				for (int j = 0; j < list.get(node[0]).size(); j++) {
					if (!visited[list.get(node[0]).get(j)]) {
						q.add(new int[] { list.get(node[0]).get(j), node[1] + 1 });
						visited[list.get(node[0]).get(j)] = true;
					}
				}
			}
		}
		return -1;
	}

}
