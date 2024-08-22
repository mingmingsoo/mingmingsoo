import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int n;
	static int m;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		int[][] grid = new int[n + 1][m + 1];

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				int tmp = sc.nextInt();
				if (tmp > 0) {
					grid[i][j] = tmp + 1;
				} else {
					grid[i][j] = tmp;
				}
			}
		}
		
//		for(int i = 0; i<n+1; i++) {
//			for(int j = 0; j<m+1;j++) {
//				System.out.print(grid[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println("=================");
		
		int cnt = 0;
		while (true) {
			visited = new boolean[n + 1][m + 1];
			bfs(grid, 0, 0);
			fill2(grid);
			if(exit == 0) {
				break;
			}
			cnt++;
			
		}
		System.out.println(cnt);

	}

	static int[] row = { 1, -1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };
	
	static int exit;

	static boolean[][] visited;

	private static void bfs(int[][] grid, int r, int c) {
		exit = 0;
		visited[r][c] = true;

		Queue<Integer> q = new LinkedList<>();

		q.offer(r);
		q.offer(c);

		while (!q.isEmpty()) {

			int rnode = q.poll();
			int cnode = q.poll();

			for (int k = 0; k < 4; k++) {
				int nr = rnode + row[k];
				int nc = cnode + col[k];

				if (nr >= 0 && nr < n+1 && nc >= 0 && nc < m+1 &&!visited[nr][nc]&& grid[nr][nc] == 0) {
					visited[nr][nc] = true;
					q.offer(nr);
					q.offer(nc);
				} else if (nr >= 0 && nr < n+1 && nc >= 0 && nc < m+1 && grid[nr][nc] > 0) {
					exit++;
					visited[nr][nc] = true;
					grid[nr][nc]--;
					if (grid[nr][nc] < 0) {
						grid[nr][nc] = 0;
					}
				}

			}

		}
//		for(int i = 0; i<n+1; i++) {
//			for(int j = 0; j<m+1;j++) {
//				System.out.print(grid[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println("=================");

	}
	
	private static void fill2(int[][] grid) {
		for(int i = 0; i<n;i++) {
			for(int j = 0; j<m;j++) {
				if(grid[i][j]>0) {
					grid[i][j]=2;
				}
			}
		}
		
	}
}
