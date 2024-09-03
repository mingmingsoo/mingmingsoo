

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		int tt = 1;
		
		while(tt<=t) {
			n = sc.nextInt();

			int curx = sc.nextInt();
			int cury = sc.nextInt();

			int gox = sc.nextInt();
			int goy = sc.nextInt();

			map = new int[n][n];

			// bfs 돌면서 숫자 늘려주고 gox, goy 값 보기

			bfs(curx, cury);
			
			
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
			
			System.out.println(map[gox][goy]-1); // visited 안쓰기 위해 map[curx][cury] = 1; 해줘서 첫 위치값인 1 빼주기
			tt++;
		}
	}

	static int[] row = { 2, 1, -1, -2, -2, -1, 1, 2 };
	static int[] col = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[][] map;
	static int n;

	private static void bfs(int curx, int cury) {
		Queue<Integer> q = new LinkedList<>();
		map[curx][cury] = 1;

		q.offer(curx);
		q.offer(cury);

		while (!q.isEmpty()) {
			int size = q.size() / 2;
			for (int i = 0; i < size; i++) {
				int xnode = q.poll();
				int ynode = q.poll();

				for (int k = 0; k < 8; k++) {
					int nr = xnode + row[k];
					int nc = ynode + col[k];

					if (nr >= 0 && nr < n && nc >= 0 && nc < n && map[nr][nc] == 0) {
						q.offer(nr);
						q.offer(nc);
						map[nr][nc] = map[xnode][ynode] + 1; // 후 노드에 전 노드 + 1 해주기
					}

				}

			}

		}

	}
}
