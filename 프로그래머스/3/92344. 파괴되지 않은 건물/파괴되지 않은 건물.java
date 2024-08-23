class Solution {
    public int solution(int[][] board, int[][] skill) {
        
		int answer = 0;

		int R = board.length;
		int C = board[0].length;
		int[][] mark = new int[R + 1][C + 1];

		// skill 포인트 찍기 [type, r1, c1, r2, c2, degree]
		for (int[] s : skill) {
			if (s[0] == 2) {
				mark[s[1]][s[2]] += s[5];
				mark[s[1]][s[4] + 1] -= s[5];
				mark[s[3] + 1][s[2]] -= s[5];
				mark[s[3] + 1][s[4] + 1] += s[5];
			} else {
				mark[s[1]][s[2]] -= s[5];
				mark[s[1]][s[4] + 1] += s[5];
				mark[s[3] + 1][s[2]] += s[5];
				mark[s[3] + 1][s[4] + 1] -= s[5];
			}
		}

//		for (int r = 0; r < R + 1; r++) {
//			for (int c = 0; c < C; c++) {
//				System.out.print(mark[r][c] + " ");
//			}
//			System.out.println();
//		}
//
//		System.out.println();

		// 가로 누적합 구하기
		for (int r = 0; r < R + 1; r++) {
			for (int c = 0; c < C; c++) {
				mark[r][c + 1] += mark[r][c];
			}
		}

		// 세로 누적합 구하기
		for (int c = 0; c < C + 1; c++) {
			for (int r = 0; r < R; r++) {
				mark[r + 1][c] += mark[r][c];
			}
		}

//		for (int r = 0; r < R + 1; r++) {
//			for (int c = 0; c < C; c++) {
//				System.out.print(mark[r][c] + " ");
//			}
//			System.out.println();
//		}

		// 파괴되지 않은 건물 찾기
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (mark[r][c] + board[r][c] > 0)
					++answer;
			}
		}
		return answer;
        
    }
}