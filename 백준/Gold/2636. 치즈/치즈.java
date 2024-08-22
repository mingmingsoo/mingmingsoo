
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

		int[][] grid = new int[n][m];

		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		
		int first = cheeze_count(grid);
		
		while (true) {
			visited = new boolean[n][m];
			bfs(grid, 0, 0);
			if (total_one == 0) {
				break;
			}
		}

		System.out.println(cheeze_residues.size()); // 걸린 시간
		if (cheeze_residues.size() > 2) {
			System.out.println(cheeze_residues.get(cheeze_residues.size() - 2));
		} else {
			System.out.println(first); // 다 녹기 -1 전 남은 치즈
		} 
//		System.out.println(cheeze_residues);

	}

	static int[] row = { 1, -1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };

	static List<Integer> cheeze_residues = new ArrayList<>();
	static boolean[][] visited;

	private static void bfs(int[][] grid, int r, int c) {
		visited[r][c] = true; // 방문처리;
		Queue<Integer> q = new LinkedList<>();

		q.offer(r);
		q.offer(c);

		while (!q.isEmpty()) {
			int rnode = q.poll();
			int cnode = q.poll();

			for (int k = 0; k < 4; k++) {
				int nr = rnode + row[k];
				int nc = cnode + col[k];

				if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 0 && !visited[nr][nc]) {
					q.offer(nr);
					q.offer(nc);
					visited[nr][nc] = true;
				} else if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 1 && !visited[nr][nc]) {
					grid[nr][nc] = 0;
					visited[nr][nc] = true;
				}
			}

		}
		cheeze_residues.add(cheeze_count(grid));
	}

	static int total_one;

	private static int cheeze_count(int[][] grid) {
		total_one = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					total_one++;
				}
			}
		}

		return total_one;
	}

}
