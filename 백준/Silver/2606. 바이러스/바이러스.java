

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(); // 정점 수
		int m = sc.nextInt(); // 간선 수

		grid = new ArrayList<>();
		visited = new boolean[n+1];

		for (int i = 0; i <= n; i++) {
			grid.add(new ArrayList<>());
		}
		
		for (int i = 1; i < m+1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			grid.get(a).add(b);
			grid.get(b).add(a);
		}
//		System.out.println(grid);
		dfs(1);
		
		int cnt = 0;
		for(int i = 0; i<visited.length;i++) {
			if(visited[i]) {
				cnt++;
			}
		}
		System.out.println(cnt-1);
		
	}
	static List<List<Integer>> grid;
	static boolean[] visited;

	private static void dfs(int v) {
		
		visited[v] = true;
		
//		System.out.print(v+" ");
		
		for(int i : grid.get(v)) {
			if(!visited[i]) {
				dfs(i);
			}
		}

	}

}
