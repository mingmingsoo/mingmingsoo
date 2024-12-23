

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1은 집, 0은 집X
 * 철수는 연결된 집에 단지 번호를 붙히려고함
 * 총 단지수를 출력
 * 단지에 속하는 집의 수를 오름차순으로 출력
 * <p>
 * BFS로 풀기
 * <p>
 * 필요한 메서드
 * bfs
 * <p>
 * 필요한 변수
 * num(단지번호)
 * List<int> list
 */
public class Main {

    static boolean[][] visited;
    static int[][] grid;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = tmp.charAt(j) - '0';
            }
        }
        int num = 0; // 단지 수
        List<Integer> list = new ArrayList<>();
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    int cnt = bfs(i, j, 0);
                    num++;
                    list.add(cnt);
                }
            }
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        sb.append(num).append("\n");
        while (!list.isEmpty()) {
            sb.append(list.remove(0)).append("\n");
        }
        System.out.println(sb);

    }

    private static int bfs(int i, int j, int cnt) {
        int[] row = new int[]{-1,1,0,0};
        int[] col = new int[]{0,0,1,-1};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;
        while (!q.isEmpty()) {
            for (int k = 0; k < q.size(); k++) {
                int[] node = q.poll();
                int r = node[0];
                int c = node[1];
                cnt++;

                for (int d = 0; d < 4; d++) {
                    int nr = r + row[d];
                    int nc = c + col[d];
                    if(nr>=0 && nr<n&& nc>=0 && nc<n&& !visited[nr][nc]&& grid[nr][nc]==1){
                        q.add(new int[]{nr,nc});
                        visited[nr][nc] = true;
                    }
                }

            }

        }
        return cnt;
    }

}
