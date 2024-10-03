
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		char[][] grid = new char[n][m];
		boolean[][] visited = new boolean[n][m];
		int sx = -1, sy = -1; // 고슴도치 시작 위치
		int ex = -1, ey = -1; // 굴 위치

		for (int i = 0; i < n; i++) {
			String tmp = sc.next();
			for (int j = 0; j < m; j++) {
				grid[i][j] = tmp.charAt(j);
				if (grid[i][j] == 'D') { // 굴 위치
					ex = i;
					ey = j;
				} else if (grid[i][j] == 'S') { // 고슴도치 위치
					sx = i;
					sy = j;
					grid[i][j] = '.'; // 빈 공간으로 바꿈
				}
			}
		}

		int[] row = { -1, 1, 0, 0 };
		int[] col = { 0, 0, 1, -1 };

		Queue<int[]> gosumQ = new LinkedList<>(); // 고슴도치 이동 큐
		Queue<int[]> waterQ = new LinkedList<>(); // 물 확산 큐

		visited[sx][sy] = true;
		gosumQ.add(new int[] { sx, sy, 0 }); // 고슴도치 시작 위치와 시간
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == '*') {
					waterQ.add(new int[] { i, j }); // 물의 위치 추가
				}
			}
		}

		while (!gosumQ.isEmpty()) {
			// 물 이동
			int waterSize = waterQ.size();
			for (int i = 0; i < waterSize; i++) {
				int[] waterNode = waterQ.poll();
				int wr = waterNode[0];
				int wc = waterNode[1];
				for (int k = 0; k < 4; k++) {
					int nr = wr + row[k];
					int nc = wc + col[k];
					if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == '.') {
						grid[nr][nc] = '*';
						waterQ.add(new int[] { nr, nc });
					}
				}
			}

			// 고슴도치 이동
			int gosumSize = gosumQ.size();
			for (int i = 0; i < gosumSize; i++) {
				int[] node = gosumQ.poll();
				int rr = node[0];
				int cc = node[1];
				int time = node[2];

				if (rr == ex && cc == ey) { // 굴에 도착한 경우
					System.out.println(time);
					return;
				}

				for (int k = 0; k < 4; k++) {
					int nr = rr + row[k];
					int nc = cc + col[k];
					if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] != 'X' && grid[nr][nc] != '*'
							&& !visited[nr][nc]) {
						visited[nr][nc] = true;
						gosumQ.add(new int[] { nr, nc, time + 1 });
					}
				}
			}
		}

		System.out.println("KAKTUS");
	}
}