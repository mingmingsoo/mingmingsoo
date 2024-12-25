
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * n*m 맵, 0: 이동 가능 / 1: 벽
 * 0,0 -> n-1, m-1로 최단 경로로 이동
 * 한 개의 벽을 부술 수 있음
 * <p>
 * n과 m의 범위가 1~1000으로 bfs 사용
 * <p>
 * 도착까지 불가능하다면 -1 출력
 * <p>
 * 필요한 메서드
 * bfs(0,0,0,true);
 */


public class Main {

    static int[] row = {-1, 1, 0, 0};
    static int[] col = {0, 0, 1, -1};
    static int ans = -1;
    static int n;
    static int m;
    static int[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0, 1, 0); // 좌표, 경로 길이, 벽 부실 수 있는지
        System.out.println(ans);


    }

    private static void bfs(int i, int j, int s, int b) {
        Queue<Info> q = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][2];
        visited[i][j][0] = true;

        Info info = new Info(i, j, s, b);
        q.add(info);

        while (!q.isEmpty()) {
            int Size = q.size();
            for (int size = 0; size < Size; size++) {
                Info information = q.poll();
//                System.out.println(information);
                int r = information.r;
                int c = information.c;
                int sum = information.sum;
                int bomb = information.bomb;

                if (r == n - 1 && c == m - 1) {
                    ans = Math.max(sum, ans);
                    return;
                } // 최단 경로이므로 return 해도 됨.

                for (int k = 0; k < 4; k++) {
                    int nr = r + row[k];
                    int nc = c + col[k];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 0 && !visited[nr][nc][bomb]) {
                        q.add(new Info(nr, nc, sum + 1, bomb));
                        visited[nr][nc][bomb] = true;
                    } else if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc][1] && grid[nr][nc] == 1 && bomb == 0) {
                        q.add(new Info(nr, nc, sum + 1, 1));
                        visited[nr][nc][1] = true;
                    }
                }


            }

        }


    }

    static class Info {
        int r;
        int c;
        int sum;
        int bomb;

        public Info(int r, int c, int sum, int bomb) {
            this.r = r;
            this.c = c;
            this.sum = sum;
            this.bomb = bomb;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "r=" + r +
                    ", c=" + c +
                    ", sum=" + sum +
                    ", bomb=" + bomb +
                    '}';
        }
    }
}
