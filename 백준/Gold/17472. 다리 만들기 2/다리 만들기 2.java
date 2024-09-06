
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	static class Edge implements Comparable<Edge> {
		int A;
		int B;
		int W;

		public Edge(int a, int b, int w) {
			super();
			A = a;
			B = b;
			W = w;
		}

		@Override
		public String toString() {
			return "Edge [A=" + A + ", B=" + B + ", W=" + W + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.W, o.W);
		}

	}

	public static void main(String[] args) {

		// 최소신장트리
		// 1. 섬 갯수도 구해야함.
		// 2. 섬마다의 가중치를 모두 구한다.
		// 3. 가장 최소로 되는 모든 다리의 합.
		// * 다리의 길이는 2이상!! (사각형들의 끝 값에서 2이상이 되야함)

		Scanner sc = new Scanner(System.in);

		edgelist = new ArrayList<>();


		n = sc.nextInt();
		m = sc.nextInt();

		grid = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		islandnum = 0;
		int numbering = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1 && !visited[i][j]) {
					dfs(i, j, numbering);
					numbering++; // 섬 넘버링
					islandnum++; // 섬의 갯수
				}
			}
		}

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(grid[i][j] + " ");
//			}
//			System.out.println();
//		}

		visited = new boolean[n][m];

//		System.out.println(Arrays.deepToString(checkunique));

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] != 0) {
					caldist(i, j, grid[i][j]);
				}
			}
		}
//		System.out.println(edgelist);

		Collections.sort(edgelist);
		parents = new int[islandnum + 1];

		for (int i = 0; i < islandnum + 1; i++) {
			parents[i] = i;
		}

		int cnt = 0;
		int sum = 0;
//		System.out.println(edgelist);
		for (int i = 0; i < edgelist.size(); i++) {
			int ap = findSet(edgelist.get(i).A);
			int bp = findSet(edgelist.get(i).B);

			if (ap != bp) {
				union(ap, bp);
				sum += edgelist.get(i).W;
				cnt++;
			}
			if (cnt == islandnum - 1) {
				break;
			}

		}

//		System.out.println(Arrays.toString(parents));

		int root = findSet(1);
		for (int i = 2; i < parents.length; i++) {
			if (findSet(i) != root) {
				sum = -1;
				break;
			}
		}
		System.out.println(sum);

	}

	private static void union(int ap, int bp) {

		if (ap != bp) {
			parents[bp] = ap;
		}

	}

	private static int findSet(int x) {
		if (parents[x] != x) {
			parents[x] = findSet(parents[x]);
		}
		return parents[x];
	}

	static int[] parents;
	static int[] costs;

	static int[] row = { 1, -1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };
	static int n;
	static int m;
	static int[][] grid;
	static boolean[][] visited;
	static int idx = 0;
	static List<Edge> edgelist;
	static int islandnum;

	private static void caldist(int r, int c, int from) {
		for (int k = 0; k < 4; k++) {
			int nr = r + row[k];
			int nc = c + col[k];
			int dist = 0;
			while (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] != from) {
				if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] != 0) {
					int to = grid[nr][nc];
					if (dist >= 2 && from < to) {
						edgelist.add(new Edge(from, to, dist));
					}
					break;
				}
				nr += row[k];
				nc += col[k];
				dist++;
			}
		}
	}

	private static void dfs(int r, int c, int numbering) {
		visited[r][c] = true;
		grid[r][c] = numbering;

		for (int k = 0; k < 4; k++) {
			int nr = r + row[k];
			int nc = c + col[k];
			if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && grid[nr][nc] == 1) {
				visited[nr][nc] = true;
				grid[r][c] = numbering;
				dfs(nr, nc, numbering);
			}
		}

	}

}
