
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * n개만큼 구슬을 쏴서 최대한 많은 구슬을 없애기
 * Java 3초, n w h 범위가 크지 않으므로 완전탐색!
 * 필요한 메서드
 * 1. 중복순열(어디서 쏠지를 정함 - 0~w까지 n개) - duplePerm
 * 2-0. 원본 배열 복사 - copyGrid
 * 2. 해당 배열 크기만큼 구슬 제거 - removeBall
 * 3. 구슬 당기기 - pullBall
 * 4. 남은 구슬 세기 - countBall
 * <p>
 * 필요한 변수
 * int n, w, h
 * int[][] grid, int[][] gridOrigin
 * int ans, min
 */


public class Solution {

    static int n;
    static int w;
    static int h;
    static int[][] grid_origin;

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st; // 한 줄 자체로 받을 때 분리해주는 도구
//        st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int tt = 1;
        while (tt <= t) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            grid_origin = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    grid_origin[i][j] = Integer.parseInt(st.nextToken());
                }

            }
//        visited = new boolean[w];
            arr = new int[w];
            sel = new int[n];

            for (int i = 0; i < w; i++) {
                arr[i] = i;
            }
            ans = Integer.MAX_VALUE;
            duplePerm(0);
            sb.append("#").append(tt).append(" ").append(ans).append("\n");
            tt++;
        }
        System.out.println(sb);
    }

    //    static boolean[] visited;
    static int[] arr;
    static int[] sel;
    static int ans;

    private static void duplePerm(int idx) {
        // 중복순열 코드
        if (idx == n) {
//            System.out.println(Arrays.toString(sel));
            int[][] grid = copyGrid();

            // 제거
            for (int i = 0; i < n; i++) {
                removeBall(sel[i], grid);
//                if (sel[0] == 2 && sel[1] == 2 && sel[2] == 6) {
//                    printt(grid);
//                }
                pullBall(grid);
//                if (sel[0] == 2 && sel[1] == 2 && sel[2] == 6) {
//                    printt(grid);
//                }
            }

            // 숫자 세기
            int min = countBall(grid);
            ans = Math.min(min, ans);
//            System.out.println(ans);

            return;
        }
        for (int i = 0; i < w; i++) {
            sel[idx] = i;
            duplePerm(idx + 1);
        }
    }

    private static void printt(int[][] grid) {
        System.out.println("-------------------------------");
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void pullBall(int[][] grid) {
        for (int j = 0; j < w; j++) {
            for (int i = h - 1; i >= 0; i--) {
                for (int k = i - 1; k >= 0; k--) {
                    if (grid[i][j] == 0 && grid[k][j] != 0) {
                        int tmp = grid[k][j];
                        grid[k][j] = 0;
                        grid[i][j] = tmp;
                    }
                }
            }
        }

    }

    private static int countBall(int[][] grid) {
        int cnt = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j] != 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static int[] row = {1, 0, 0};
    static int[] col = {0, 1, -1};

    private static void removeBall(int j, int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> q2 = new LinkedList<>();
        boolean visited[][] = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            if (grid[i][j] != 0) {
                q.add(new int[]{i, j}); // 제거시킬 시작 위치
                visited[i][j] = true;
                break;
            }
        }
//        System.out.println(Arrays.toString(q.peek()));
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int[] node = q.poll();
                int x = node[0];
                int y = node[1];
                int bomb = grid[x][y];
                q2.add(new int[]{x, y});
//                System.out.println(x + " " + y + " " + bomb);
                // 상하
                for (int i = x - bomb + 1; i < x + bomb && i < h; i++) {
                    if (i >= 0 && i < h && grid[i][y] != 0 && !visited[i][y]) {
                        q.add(new int[]{i, y});
                        visited[i][y] = true;
                    }
                }
                // 좌우
                for (int jj = y - bomb + 1; jj < y + bomb; jj++) {
                    if (jj >= 0 && jj < w && grid[x][jj] != 0 && !visited[x][jj]) {
                        q.add(new int[]{x, jj});
                        visited[x][jj] = true;
                    }
                }
            }
        }

        while (!q2.isEmpty()) {
            int[] node = q2.poll();
            grid[node[0]][node[1]] = 0;
        }
    }

    private static int[][] copyGrid() {
        int[][] grid = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                grid[i][j] = grid_origin[i][j];
            }
        }
        return grid;
    }


    //    private static void combi(int idx, int sidx) {
//        // 조합 코드
//        if(sidx == n){
//            System.out.println(Arrays.toString(sel));
//            return;
//        }
//        if(idx == w){
//            return;
//        }
//        sel[sidx] = arr[idx];
//        combi(idx+1, sidx+1);
//        combi(idx+1, sidx);
//    }

//    private static void Perm(int idx) {
//        // 순열 코드
//        if (idx == n) {
//            System.out.println(Arrays.toString(sel));
//            return;
//        }
//        for (int i = 0; i <w; i++) {
//            if (!visited[i]) {
//                visited[i] = true;
//                sel[idx] = i;
//                Perm(idx + 1);
//                visited[i] = false;
//            }
//        }
//    }
}


