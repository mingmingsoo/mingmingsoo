

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구현 문제
 * 시간 제한 2초
 * <p>
 * 입력값
 * 2차원 배열 크기 n과 m
 * 로봇 청소기 초기 좌표 r과 c, 방향 d (초기 좌표는 항상 청소되지 않은 상태, 벽X)
 * 2차원 배열 정보 (0이면 청소되지 않음, 1이면 벽)
 * <p>
 * 출력값
 * 청소한 방의 갯수
 * <p>
 * 1. 현재 칸이 청소 X시, 청소
 * 2. 주변 4칸 중 청소되지 않은 빈칸이 없는 경우(= 모두 청소 되있는 경우)
 * - 바라보는 방향 유지한 채로 한 칸 후진 후 다시 1번으로
 * - 후진 할 수 없으면 작동을 멈춤
 * 3. 주변 4칸 중 청소되지 않은 빈칸이 있는 경우
 * - 반시계 방향으로 90도 회전
 * - 바라보는 방향을 기준으로 앞쪽 칸이 청소되자 않았으면 한 칸 전진
 * - 1번으로 돌아감
 * <p>
 * 0 - 북
 * 1 - 동
 * 2 - 남
 * 3 - 서
 * <p>
 * 후진은 2로
 */
public class Main {

    static int[] row = {-1, 1, 0, 0};
    static int[] col = {0, 0, 1, -1};
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        while (true) { // 후진할 수 없으면 작동을 멈춤
            if (grid[r][c] == 0) {
                ans++;
                grid[r][c] = 2; // 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            }
            if (allClean(grid, r, c, d)) { // 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
                if (okBack(grid, r, c, d)) { // 2-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
                    if (d == 0) {
                        r++;
                    } else if (d == 1) {
                        c--;
                    } else if (d == 2) {
                        r--;
                    } else {
                        c++;
                    }
                } else {
                    break; // 2-2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다. = 탈출조건
                }
            } else {
                // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                // 3-1.반시계 방향으로 90도 회전한다.
                if (d == 0) {
                    d = 3;
                    // 3-2.바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
                    if (grid[r][c - 1] == 0) {
                        c--;
                    }
                } else if (d == 1) {
                    d = 0;
                    if (grid[r - 1][c] == 0) {
                        r--;
                    }
                } else if (d == 2) {
                    d = 1;
                    if (grid[r][c + 1] == 0) {
                        c++;
                    }
                } else {
                    d = 2;
                    if (grid[r + 1][c] == 0) {
                        r++;
                    }
                }
            }

        }
        System.out.println(ans);
    }

    private static boolean allClean(int[][] grid, int r, int c, int d) {
        for (int k = 0; k < 4; k++) {
            int nr = r + row[k];
            int nc = c + col[k];
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 0) { // 청소할 곳 이 있으면
                return false; // 해당 안됨
            }
        }
        return true; // 모두 청소 되있거나 벽임
    }

    private static boolean okBack(int[][] grid, int r, int c, int d) {
        if (d == 0 && r + 1 < n && grid[r + 1][c] != 1) {
            return true;
        } else if (d == 1 && c - 1 >= 0 && grid[r][c - 1] != 1) {
            return true;
        } else if (d == 2 && r - 1 >= 0 && grid[r - 1][c] != 1) {
            return true;
        } else if (d == 3 && c + 1 <= m && grid[r][c + 1] != 1) {
            return true;
        }
        return false;
    }


}
