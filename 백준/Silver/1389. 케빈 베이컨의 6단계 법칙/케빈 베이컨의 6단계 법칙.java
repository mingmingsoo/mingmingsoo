
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static int n;
	static int m;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();

		m = sc.nextInt();

		grid = new ArrayList<>();

		for (int i = 0; i < n + 1; i++) {
			grid.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			int prev = sc.nextInt();
			int next = sc.nextInt();
			grid.get(prev).add(next);
			grid.get(next).add(prev);
		}

//		System.out.println(grid);
		

		int min = Integer.MAX_VALUE;
		int idx = -1;
		for (int i = 1; i < n + 1; i++) {
			visited = new boolean[n + 1]; 
			int ans = bfs(i);
//			System.out.println("i"+i+" "+"ans"+ans);
			if(min > ans) { // 갓정표...
				min = ans;
				idx = i;
			}
		}
		System.out.println(idx);



	}

	static boolean[] visited;
	static List<List<Integer>> grid;

	private static int bfs(int v) {
		int cnt = 0;
		int plus = 1;
		visited[v] = true;

		Queue<Integer> q = new LinkedList<>();

		q.offer(v);

		while (!q.isEmpty()) {
			int size = q.size();
			for(int i = 0; i<size;i++) {
				int node = q.poll();
//			System.out.print(node + " ");
				for (int j = 0; j < grid.get(node).size(); j++) {
					int tmp = grid.get(node).get(j);
					if (!visited[tmp]) {
						visited[tmp] = true;
						q.offer(tmp);
						cnt += plus;
					}
				}
				
			}
			plus++;

		}
		return cnt;
	}
}
