

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/*
		 * 비어있는 곳. 물 * 돌 X 비버 굴 D 1개 고슴도치 위치 S 1개 고슴도치 사방 중 한 칸 이동(돌 이동X) 물은 사방으로 한칸 씩
		 * 확장(돌, 굴 있는 곳은 X) 고슴도치가 굴로 이동하는 최소 시간 1분동안 일어나는 일(순서있음) 1. 물 확산(bfs) 2. 고슴도치
		 * 이동(bfs) 종료조건? 1. 굴 도착하면 2. 고슴도치가 이동할 곳이 없으면
		 */

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

		Queue<int[]> waterQ = new LinkedList<>();
		Queue<int[]> gosumQ = new LinkedList<>();

		// 고슴도치 첫 위치 q에 넣기
		visited[sx][sy] = true;
		gosumQ.add(new int[] { sx, sy, 0 }); // 위치와 지난 시간
		// 물 위치 q에 넣기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == '*') {
					waterQ.add(new int[] { i, j });
				}
			}
		}

		while (!gosumQ.isEmpty()) {
			// 물 확산
			int water = waterQ.size();
			for (int i = 0; i < water; i++) {
				int[] node = waterQ.poll();
				int rr = node[0];
				int cc = node[1];
				for (int k = 0; k < 4; k++) {
					int nr = rr + row[k];
					int nc = cc + col[k];
					if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == '.') {
						grid[nr][nc] = '*';
						waterQ.add(new int[] { nr, nc });
					}
				}
			}

			// 고슴도치 이동
			int gosum = gosumQ.size();
			for (int i = 0; i < gosum; i++) {
				int[] node = gosumQ.poll();
				int rr = node[0];
				int cc = node[1];
				int time = node[2];
//				System.out.println("물확산");
//				System.out.println(Arrays.deepToString(grid));
//				System.out.println("고슴도치 현재 위치"+rr+" "+cc);
				if (rr == ex && cc == ey) {
					System.out.println(time);
					return;
				}
				for (int k = 0; k < 4; k++) {
					int nr = rr + row[k];
					int nc = cc + col[k];
					if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && grid[nr][nc] != 'X'
							&& grid[nr][nc] != '*') {
						visited[nr][nc] = true;
						gosumQ.add(new int[] { nr, nc, time + 1 });
					}
				}
			}
		}
		System.out.println("KAKTUS");
	}
}