
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		// 가만히 있을 수 도 있으므로 최대 time까지 방문한 곳 갯수를 세고.
		// -> 최대 어디까지 갈 수 있는가? 이므로 visited 초기화 필요 X
		// 시간 별로 퍼져나가므로 bfs
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		int tt =1;
		while(tt<=t) {
			n = sc.nextInt();
			m = sc.nextInt();
			int r = sc.nextInt();
			int c = sc.nextInt();
			time_limit = sc.nextInt();

			grid = new int[n][m];
			visited = new boolean[n][m];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					grid[i][j] = sc.nextInt();
				}
			}

			cnt = 0;
			bfs(r, c);
			System.out.println("#"+tt+" "+cnt);
			tt++;
//			for (int i = 0; i < list.size(); i++) {
//				System.out.println(Arrays.toString(list.get(i)));
//			}
		}


	}

	static int n;
	static int m;
	static int time_limit;
	static int[][] grid;
	static boolean[][] visited;
	static int cnt;
	static List<int[]> list = new ArrayList<>();

	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c, 1 }); // 현재 위치와 time
		visited[r][c] = true;

		// 이동하는 과정
		while (!q.isEmpty()) {
			int[] node = q.poll();
			int rnode = node[0];
			int cnode = node[1];
			int time = node[2];

			// 시간 제한 조건
			if (time > time_limit) {
				break;
			}
			// 경우의 수
			list.add(node);
			cnt++; // 나 지금 이동 했으므로

			// 다음 이동 : 갈 방향을 얻어야함.
			for (int[] dir : getdir(grid[rnode][cnode])) { // 현재 위치에서 갈 수 있는 곳 찾기.
				int nr = rnode + dir[0];
				int nc = cnode + dir[1];
				if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && isok(grid[nr][nc], dir)) {
					q.add(new int[] { nr, nc, time + 1 });
					visited[nr][nc] = true;
				}
			}
		}
	}

	private static int[][] getdir(int type) {
		switch (type) {
		case 1:
			return new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
		case 2:
			return new int[][] { { -1, 0 }, { 1, 0 } };
		case 3:
			return new int[][] { { 0, 1 }, { 0, -1 } };
		case 4:
			return new int[][] { { -1, 0 }, { 0, 1 } };
		case 5:
			return new int[][] { { 1, 0 }, { 0, 1 } };
		case 6:
			return new int[][] { { 1, 0 }, { 0, -1 } };
		case 7:
			return new int[][] { { -1, 0 }, { 0, -1 } };
		default:
			return new int[0][0]; // 이동 안할때
		}
	}

	private static boolean isok(int next, int[] dir) {
		int x = dir[0];
		int y = dir[1];

		if (x == 0 && y == 1) {
			return next == 1 || next == 3 || next == 6 || next == 7;
		} else if (x == 0 && y == -1) {
			return next == 1 || next == 3 || next == 4 || next == 5;
		} else if (x == 1 && y == 0) {
			return next == 1 || next == 2 || next == 4 || next == 7;
		} else if (x == -1 && y == 0) {
			return next == 1 || next == 2 || next == 5 || next == 6;
		} else {
			return false;
		}

	}

}