
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int tt = 1;
		StringBuilder sb = new StringBuilder();

		while (tt <= t) {
			// 출발점
			int r1 = sc.nextInt();
			int c1 = sc.nextInt();

			// 목적지
			r2 = sc.nextInt();
			c2 = sc.nextInt();

			// 0 이면 가로
			// 1 이면 세로
			visited = new HashSet<>();
			int cnt1 = bfs(r1, c1, 0, 0);
			visited = new HashSet<>();
			int cnt2 = bfs(r1, c1, 1, 0);
			sb.append("#"+tt+" "+Math.min(cnt1, cnt2)+"\n");
			tt++;
		}
		System.out.println(sb);

	}

	static Set<String> visited;
	static int[] row = { -1, 1, 0, 0 };
	static int[] col = { 0, 0, 1, -1 };
	static int c2;
	static int r2;

	private static int bfs(int r1, int c1, int dir, int cnt) {
		Queue<int[]> q = new LinkedList<>();
		visited.add(r1+","+c1);
		q.add(new int[] { r1, c1, dir, cnt });

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] node = q.poll();
				int x = node[0];
				int y = node[1];
				int d = node[2];
				int c = node[3];
				if (x == r2 && y == c2) {
					return c;
				}
				

				if (d == 0) { // 전 이동이 가로였으면 세로로 가야함.
					for (int k = 2; k < 4; k++) {
						int nr = x + row[k];
						int nc = y + col[k];
						if (nr >= -100 && nr <= 100 && nc >= -100 && nc <= 100
								&& !visited.contains(nr+","+nc)) {
							visited.add(nr+","+nc);
							q.add(new int[] { nr, nc, 1, c + 1 });
						}
					}
				} else if (d == 1) { // 전 이동이 세로였으면 가로로 가야함.
					for (int k = 0; k < 2; k++) {
						int nr = x + row[k];
						int nc = y + col[k];
						if (nr >= -100 && nr <= 100 && nc >= -100 && nc <= 100
								&& !visited.contains(nr+","+nc)) {
							visited.add(nr+","+nc);
							q.add(new int[] { nr, nc, 0, c + 1 });
						}
					}

				}

			}
		}
		return 0;

	}

}
