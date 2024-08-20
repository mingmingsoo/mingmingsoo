import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();

		grid = new int[n][n];

		for (int i = 0; i < n; i++) {
			String s = sc.next();
			for (int j = 0; j < n; j++) {
				grid[i][j] = s.charAt(j) - '0';
			}
		}

		// 단지 정보 담는 list
		dong_list = new ArrayList<>();

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					dong_list.add(dfs(grid, i, j));
					cnt++;
				}
			}
		}
		System.out.println(cnt); // 총 동의 갯수

		Collections.sort(dong_list); // 동리스트 오름차순

		for (int i = 0; i < dong_list.size(); i++) {
			System.out.println(dong_list.get(i));
		}

	}

	static int n;
	static List<Integer> dong_list;
	static int[][] grid;

	private static int dfs(int[][] grid, int i, int j) {
		int each = 1;
		grid[i][j] = 0; // 방문처리

		int[] row = { 1, -1, 0, 0 };
		int[] col = { 0, 0, 1, -1 };

		for (int k = 0; k < 4; k++) {
			int nr = i + row[k];
			int nc = j + col[k];

			if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 1) {
				each += dfs(grid, nr, nc);
			}

		}
		return each;

	}

}
