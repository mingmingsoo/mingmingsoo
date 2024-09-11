import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = 10;
		int tt = 1;
		while (tt <= t) {
			int V = sc.nextInt();
			int E = sc.nextInt();

			int[][] adj = new int[V + 1][V + 1];
			int[] degree = new int[V + 1];

			for (int i = 0; i < E; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				adj[a][b] = 1;
				degree[b]++;
			}

			Queue<Integer> q = new LinkedList<>();
			for (int i = 1; i < V + 1; i++) {
				if (degree[i] == 0) {
					q.add(i);
				}
			}
			StringBuilder sb = new StringBuilder();
			while (!q.isEmpty()) {
				int curr = q.poll();
				sb.append(curr + " ");
				for (int i = 1; i < V + 1; i++) {
					if (adj[curr][i] == 1) {
						degree[i]--;
						adj[curr][i] = 0;

						if (degree[i] == 0) {
							q.add(i);
						}
					}
				}
			}
			System.out.println("#" + tt + " " + sb);
			tt++;
		}

	}

}
