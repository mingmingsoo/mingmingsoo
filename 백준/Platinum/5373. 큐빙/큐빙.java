

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int t = 0;

		while (t < T) {
			cube = new char[6][3][3];

			// 큐브 채우기
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					for (int w = 0; w < 3; w++) {
						if (i == 0) {
							cube[i][j][w] = 'w';
						} else if (i == 1) {
							cube[i][j][w] = 'y';
						} else if (i == 2) {
							cube[i][j][w] = 'r';
						} else if (i == 3) {
							cube[i][j][w] = 'o';
						} else if (i == 4) {
							cube[i][j][w] = 'g';
						} else if (i == 5) {
							cube[i][j][w] = 'b';
						}
					}
				}
			}

			int orderNum = Integer.parseInt(br.readLine());
			String orders = br.readLine();
			String[] orderArray = orders.split(" ");
			for (int o = 0; o < orderNum; o++) {
				char dir = orderArray[o].charAt(0);
				char rot = orderArray[o].charAt(1);

				if (dir == 'U') {
					if (rot == '+') {
						upRotation();
					} else {
						upRotation();
						upRotation();
						upRotation();
					}
				} else if (dir == 'D') {
					if (rot == '+') {
						downRotation();
					} else {
						downRotation();
						downRotation();
						downRotation();

					}
				} else if (dir == 'F') {
					if (rot == '+') {
						frontRotation();
					} else {
						frontRotation();
						frontRotation();
						frontRotation();

					}
				} else if (dir == 'B') {
					if (rot == '+') {
						backRotation();
					} else {
						backRotation();
						backRotation();
						backRotation();
					}
				} else if (dir == 'L') {
					if (rot == '+') {
						leftRotation();
					} else {
						leftRotation();
						leftRotation();
						leftRotation();
					}
				} else if (dir == 'R') {
					if (rot == '+') {
						rightRotation();
					} else {
						rightRotation();
						rightRotation();
						rightRotation();
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					sb.append(cube[0][i][j]);
				}
				if (i != 2) {
					sb.append("\n");
				}
			}
			System.out.println(sb);
			t++;
		}

	}

	private static void print() {
		for (int i = 0; i < 6; i++) {
			if (i == 0) {
				System.out.println("윗면");
			} else if (i == 1) {
				System.out.println("아랫면");
			} else if (i == 2) {
				System.out.println("앞면");
			} else if (i == 3) {
				System.out.println("뒷면");
			} else if (i == 4) {
				System.out.println("왼쪽면");
			} else if (i == 5) {
				System.out.println("오른쪽면");
			}
			for (int j = 0; j < 3; j++) {
				for (int w = 0; w < 3; w++) {
					System.out.print(cube[i][j][w] + " ");
				}
				System.out.println();
			}
		}

	}

	static char[][][] cube;

	private static void rightRotation() { // ok

		int dir = 5;
		char[][] cubeCopy = new char[3][3];
		Copy(cubeCopy, cube[dir]);
		// 앞면
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cube[dir][i][j] = cubeCopy[2 - j][i];
			}
		}

//			// 주위 면들
		char[] tmp = new char[3];
		for (int i = 0; i < 3; i++) {
			tmp[i] = cube[0][i][2];
		}
		for (int i = 0; i < 3; i++) {
			cube[0][i][2] = cube[2][i][2];
		}
		for (int i = 0; i < 3; i++) {
			cube[2][i][2] = cube[1][i][2];
		}
		for (int i = 0; i < 3; i++) {
			cube[1][i][2] = cube[3][2 - i][0];
		}
		for (int i = 0; i < 3; i++) {
			cube[3][i][0] = tmp[2-i];
		}

	}

	private static void leftRotation() { // ok

		int dir = 4;

		char[][] cubeCopy = new char[3][3];
		Copy(cubeCopy, cube[dir]);
		// 앞면
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cube[dir][i][j] = cubeCopy[2 - j][i];
			}
		}

//			// 주위 면들
		char[] tmp = new char[3];
		for (int i = 0; i < 3; i++) {
			tmp[i] = cube[0][i][0];
		}
		for (int i = 0; i < 3; i++) {
			cube[0][i][0] = cube[3][2 - i][2];
		}
		for (int i = 0; i < 3; i++) {
			cube[3][i][2] = cube[1][2 - i][0];
		}
		for (int i = 0; i < 3; i++) {
			cube[1][i][0] = cube[2][i][0];
		}
		for (int i = 0; i < 3; i++) {
			cube[2][i][0] = tmp[i];
		}

	}

	private static void backRotation() { // 세모
		int dir = 3;

		char[][] cubeCopy = new char[3][3];
		Copy(cubeCopy, cube[dir]);
		// 앞면
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cube[dir][i][j] = cubeCopy[2 - j][i];
			}
		}

//			// 주위 면들
		char[] tmp = Arrays.copyOf(cube[0][0], 3);
		for (int i = 0; i < 3; i++) {
			cube[0][0][i] = cube[5][i][2];
		}
		for (int i = 0; i < 3; i++) {
			cube[5][i][2] = cube[1][2][2 - i];
		}
		for (int i = 0; i < 3; i++) {
			cube[1][2][i] = cube[4][i][0];
		}
		for (int i = 0; i < 3; i++) {
			cube[4][i][0] = tmp[2 - i];
		}

	}

	private static void frontRotation() { // ok
		// 앞면은 앞면이 90도 회전
		// 위, 오른쪽, 밑, 왼쪽이 90도씩 회전
		int dir = 2;
		char[][] cubeCopy = new char[3][3];
		Copy(cubeCopy, cube[dir]);
		// 앞면
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cube[dir][i][j] = cubeCopy[2 - j][i];
			}
		}

//			// 주위 면들
		char[] tmp = Arrays.copyOf(cube[0][2], 3);
		for (int i = 0; i < 3; i++) {
			cube[0][2][i] = cube[4][2 - i][2];
		}
		for (int i = 0; i < 3; i++) {
			cube[4][i][2] = cube[1][0][i];
		}
		for (int i = 0; i < 3; i++) {
			cube[1][0][i] = cube[5][2 - i][0];
		}
		for (int i = 0; i < 3; i++) {
			cube[5][i][0] = tmp[i];
		}

	}

	private static void Copy(char[][] cubeCopy, char[][] cubeOrigin) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cubeCopy[i][j] = cubeOrigin[i][j];
			}
		}
	}

	private static void downRotation() {
		int dir = 1;

		char[][] cubeCopy = new char[3][3];
		Copy(cubeCopy, cube[dir]);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cube[dir][i][j] = cubeCopy[2 - j][i];
			}
		}

		// 주위 면들
		char[] tmp = Arrays.copyOf(cube[2][2], 3);
		for (int i = 0; i < 3; i++) {
			cube[2][2][i] = cube[4][2][i];
		}
		for (int i = 0; i < 3; i++) {
			cube[4][2][i] = cube[3][2][i];
		}
		for (int i = 0; i < 3; i++) {
			cube[3][2][i] = cube[5][2][i];
		}
		for (int i = 0; i < 3; i++) {
			cube[5][2][i] = tmp[i];
		}

	}

	private static void upRotation() {
		// 앞면은 앞면이 90도 회전
		// 위, 오른쪽, 밑, 왼쪽이 90도씩 회전
		int dir = 0;
		char[][] cubeCopy = new char[3][3];
		Copy(cubeCopy, cube[dir]);
		// 앞면
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cube[dir][i][j] = cubeCopy[2 - j][i];
			}
		}

//					// 주위 면들
		char[] tmp = Arrays.copyOf(cube[2][0], 3);
		for (int i = 0; i < 3; i++) {
			cube[2][0][i] = cube[5][0][i];
		}
		for (int i = 0; i < 3; i++) {
			cube[5][0][i] = cube[3][0][i];
		}
		for (int i = 0; i < 3; i++) {
			cube[3][0][i] = cube[4][0][i];
		}
		for (int i = 0; i < 3; i++) {
			cube[4][0][i] = tmp[i];
		}
	}

}
