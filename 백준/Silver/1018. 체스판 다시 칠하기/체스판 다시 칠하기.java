

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// Main
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= N - 8; i++) {
			for (int j = 0; j <= M - 8; j++) {
				min = Math.min(min, whatIsFirst('W', i, j));
				min = Math.min(min, whatIsFirst('B', i, j));
			}
		}
		System.out.println(min);

	}

	static char[][] map;
	static int N;
	static int M;

	private static int whatIsFirst(char color, int r, int c) {
		int change = 0;
		for (int i = r; i < r + 8; i++) {
			for (int j = c; j < c + 8; j++) {
				if ((i + j) % 2 == 0 && map[i][j] != color) {
					change++;
				}
				if ((i + j) % 2 == 1 && map[i][j] == color) {
					change++;
				}
			}
		}

		return change;
	}

}
