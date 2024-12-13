

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 명령어 대로 2048 게임을 실행함
 * 1<=n<=20
 * 한번의 구현
 * <p>
 * 과거에는 명령대로 모든 기능을 구현했지만
 * 회전으로 한번 시도해보겠음
 * <p>
 * 필요한 메서드
 * 1. 명령어대로 회전하는 메서드 - rotationGrid
 * 2. 합치는 메서드 - sumGrid
 * 3. 스왑하는 메서드 - swapGrid
 * <p>
 * 필요한 변수
 * int[][] grid
 * int n, String order
 */

public class Solution {

    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        int tt = 1;
        StringBuilder sb = new StringBuilder();
        while (tt <= t) {
            sb.append("#").append(tt).append(" ");
            sb.append("\n");
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            String order = st.nextToken();
            int[][] grid = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (order.equals("up")) {
                swapGrid(grid);
                sumGrid(grid);
                swapGrid(grid);
            } else if (order.equals("right")) {
                rotationGrid(grid, "right");
                swapGrid(grid);
                sumGrid(grid);
                swapGrid(grid);
                restoreGrid(grid, "right");
            } else if (order.equals("down")) {
                rotationGrid(grid, "down");
                swapGrid(grid);
                sumGrid(grid);
                swapGrid(grid);
                restoreGrid(grid, "down");
            } else {
                rotationGrid(grid, "left");
                swapGrid(grid);
                sumGrid(grid);
                swapGrid(grid);
                restoreGrid(grid, "left");
            }
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    sb.append(grid[i][j]).append(" ");
                }
                sb.append("\n");
            }

        tt++;
        }
        System.out.println(sb);
    }

    private static void restoreGrid(int[][] grid, String order) {
        if (order.equals("right")) {
            restoreEle(grid);
        } else if (order.equals("down")) {
            restoreEle(grid);
            restoreEle(grid);
        } else {
            restoreEle(grid);
            restoreEle(grid);
            restoreEle(grid);
        }
    }

    private static void restoreEle(int[][] grid) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = grid[n - j - 1][i];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = copy[i][j];
            }
        }
    }

    private static void swapGrid(int[][] grid) {
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                for (int k = i + 1; k < n; k++) {
                    if (grid[i][j] == 0 && grid[k][j] != 0) {
                        int tmp = grid[k][j];
                        grid[i][j] = tmp;
                        grid[k][j] = 0;
                    }
                }
            }
        }

    }

    private static void sumGrid(int[][] grid) {
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n - 1; i++) {
                if (grid[i][j] == grid[i + 1][j] & grid[i][j] != 0) {
                    int ele = grid[i][j] * 2;
                    grid[i][j] = ele;
                    grid[i + 1][j] = 0;
                }
            }
        }
    }

    private static void rotationGrid(int[][] grid, String order) {
//        right 는 한 번 왼쪽으로
        // down은 두 번 왼쪽으로
        // left는 세 번 왼쪽으로
        if (order.equals("right")) {
            rotationEle(grid);
        } else if (order.equals("down")) {
            rotationEle(grid);
            rotationEle(grid);
        } else {
            rotationEle(grid);
            rotationEle(grid);
            rotationEle(grid);
        }
    }

    private static void rotationEle(int[][] grid) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = grid[j][n - i - 1];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = copy[i][j];
            }
        }
    }



}
