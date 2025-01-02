

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 */

public class Main {

    static int[] row = {-1, 1, 0, 0};
    static int[] col = {0, 0, 1, -1};
    static boolean[][] visited;
    static int n;
    static int m;
    static int[][] grid;
    static int ele = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, 0, grid[i][j]);
                visited[i][j] = false;
            }
        }

        // ㅗ 모양
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                checkF(i,j);
            }
        }

        System.out.println(ele);


    }

    private static void checkF(int i, int j) {
        // ㅏ 모양
        if(i+2<n && j+1<m){
            int sum = grid[i][j] + grid [i+1][j]+grid[i+2][j]+grid[i+1][j+1];
            ele = Math.max(sum, ele);
        }
        // ㅓ 모양
        if(i+2<n && j-1>=0){
            int sum = grid[i][j] + grid [i+1][j]+grid[i+2][j]+grid[i+1][j-1];
            ele = Math.max(sum, ele);
        }
        // ㅜ 모양
        if(i+1<n && j+2<m){
            int sum = grid[i][j] + grid [i][j+1]+grid[i][j+2]+grid[i+1][j+1];
            ele = Math.max(sum, ele);
        }
        // ㅗ 모양
        if(i-1>=0 && j+2<m){
            int sum = grid[i][j] + grid [i][j+1]+grid[i][j+2]+grid[i-1][j+1];
            ele = Math.max(sum, ele);
        }
    }

    private static void print(boolean[][] visited) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    private static void dfs(int r, int c, int num, int sum) {
        if (num >= 3) {
//            System.out.println("-------------------");
//            System.out.println(sum);
//            print(visited);
            ele = Math.max(ele, sum);
            return;
        }


        for (int k = 0; k < 4; k++) {
            int nr = r + row[k];
            int nc = c + col[k];
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc]) {
                visited[nr][nc] = true;
                dfs(nr, nc, num + 1, sum + grid[nr][nc]);
                visited[nr][nc] = false;
            }
        }

    }
}
