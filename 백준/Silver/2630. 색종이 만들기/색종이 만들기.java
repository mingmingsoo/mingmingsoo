
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		grid = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		white = 0;
		blue = 0;

		partition(0, 0, n); // r c size
		
		System.out.printf("%d %d",white,blue);

	}

	static int white;
	static int blue;
	static int[][] grid;

	private static void partition(int row, int col, int size) {
		// 기저조건
		if (colorcheck(row, col, size)) {
			if (grid[row][col] == 0) { // 시작점 색깔만 봐줘도 됨
				white++;
			} else {
				blue++;
			}
			return;
		}
		int newsize = size / 2;
		
		// 색조이 쪼개기

		partition(row, col, newsize); // 2
		partition(row, col + newsize, newsize); // 1
		partition(row + newsize, col, newsize); // 3
		partition(row + newsize, col + newsize, newsize); // 4 사분면

	}

	private static boolean colorcheck(int row, int col, int size) {

		// 첫번째 자리의 색깔과 나머지 색깔이 다르면 false임
		// 모두 같으면 true

		int color = grid[row][col]; // 첫번째 자리 색깔

		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (color != grid[i][j]) { // 샊깔이 다르면 fasle
					return false;
				}
			}
		}

		return true; // 모두 같으면 true
	}

}
