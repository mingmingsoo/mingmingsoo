

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// 이거야 말로 등산로 조성하고 비슷한듯
		// dfs로 가는데 매개변수에 부시는 boolean 넣기
		// 도달했을 때 거리도 매개변수로 담기
		// dfs(0,0,1,true); 시작점, 거리, 부실 수 있는가
		// dfs 안돼서 bfs로 했음
		// visited 3차원 필요!! -> 해당 위치 부쉈는지 안부쉈는지 담아줘야해서

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		grid = new int[n][m];
		visited = new boolean[n][m][2]; // 벽을 부순 상태를 3차원 배열에 저장
		for (int i = 0; i < n; i++) {
			String tmp = sc.next();
			for (int j = 0; j < m; j++) {
				grid[i][j] = tmp.charAt(j) - '0';
			}
		}
		ans = Integer.MAX_VALUE;
//		dfs(0, 0, 1, true);
		bfs(0, 0, 1); // 시작 좌표, 거리
		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(ans);

	}

	static boolean[][][] visited;
	static int[][] grid;
	static int n;
	static int m;
	static int ans;
	static int[] row = { -1, 1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };


	private static void bfs(int r, int c, int count) {
		Queue<int[]> q = new LinkedList<>(); // 벽을 안부스면 0 부스면 1
		visited[r][c][0] = true; // r, c : 좌표 , 0 : 벽 안부쉈음
		q.add(new int[] { r, c, count, 0 }); 
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] node = q.poll();
				int rr = node[0];
				int cc = node[1];
				int cnt = node[2]; // 거리
				int bomb = node[3]; // 벽 부쉈는지 여부
				if (rr == n - 1 && cc == m - 1) {
					ans = Math.min(ans, cnt);
					return;
				}
				for (int k = 0; k < 4; k++) {
					int nr = rr + row[k];
					int nc = cc + col[k];
					if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
						if (grid[nr][nc] == 0 && !visited[nr][nc][bomb]) { // 그냥 지나갈 수 있고, 방문한 적 없다면(bomb가 뭔지는 상관없음 -> 어차피 지나갈 수 있어서)
							visited[nr][nc][bomb] = true; // 방문처리
							q.add(new int[] { nr, nc, cnt + 1, bomb }); // q에 넣기
						} else if (grid[nr][nc] == 1 && bomb == 0 && !visited[nr][nc][1]) { // 벽이고, 벽을 뚫을 수 있꼬, 방문한 적 없다면
							visited[nr][nc][bomb] = true; // 벽 부스고
							q.add(new int[] { nr, nc, cnt + 1, 1 }); // q 에 넣어주는데 bomb 1 로 넣어주기
						}
					}
				}
			}

		}

	}
	
//	private static void dfs(int r, int c, int cnt, boolean bomb) {
//		if (r == n - 1 && c == m - 1) {
//			ans = Math.min(ans, cnt);
////			System.out.println(ans);
//			return;
//		}
//
//		visited[r][c] = true;
//		for (int k = 0; k < 4; k++) {
//			int nr = r + row[k];
//			int nc = c + col[k];
//			if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && grid[nr][nc] == 0) {
//				dfs(nr, nc, cnt + 1, bomb);
//			} else if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && grid[nr][nc] == 1 && bomb) {
//				bomb = false; // 벽부시고
//				dfs(nr, nc, cnt + 1, bomb);
//				bomb = true; // 안됐으면 다시 복구
//			}
//		}
//
//		visited[r][c] = false;
//
//	}

}
