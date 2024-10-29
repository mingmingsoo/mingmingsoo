
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		/*
		 * visited 써서 방문했으면 or 범위 벗어나면 달팽이처럼 방향 회전하면 될 것 같음(그때 cnt)
		 * 
		 */

		boolean[][] visited = new boolean[n][m];

		int[] dirx = new int[] { 0, 1, 0, -1 };
		int[] diry = new int[] { 1, 0, -1, 0 };

		// 현재 위치
		int x = 0;
		int y = 0;
		visited[x][y] = true;
		// 현재 방향
		int d = 0;

		// 꺾은 횟수
		int cnt = 0;

		while (true) {
			// 이동해
			if ((x + dirx[d]) >= 0 && (x + dirx[d]) < n && (y + diry[d]) >= 0 && (y + diry[d]) < m
					&& !visited[x + dirx[d]][y + diry[d]]) {
				x += dirx[d];
				y += diry[d];
				visited[x][y] = true;
//				System.out.println("방향 그대로 "+ x + " " + y);
			} else {
				d = (d + 1) % 4;
				x += dirx[d];
				y += diry[d];
				visited[x][y] = true;
//				System.out.println("방향 바뀜 "+ x + " " + y);
				cnt++;
			}

//			print(visited,n,m);
			// 탈출조건 : 모두 visited 됐으면
			if (alltrue(visited, n, m)) {
				break;
			}
		}
		System.out.println(cnt);

	}

	private static void print(boolean[][] visited, int n, int m) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(visited[i][j]+" ");
			}
			System.out.println();
		}
		
	}

	private static boolean alltrue(boolean[][] visited, int n, int m) {
		boolean check = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

}
