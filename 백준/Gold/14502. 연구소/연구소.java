

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 바이러스는 퍼지고
 * 벽을 3개 세울 수 있음
 * <p>
 * 0은 빈 칸, 1은 벽, 2는 바이러스
 * <p>
 * 바이러스가 퍼지고 나서 안전 영역의 최대값을 구하기
 * <p>
 * 모든 경우의 수를 따져서 바이러스가 퍼지지 않는 영역을 구해야함.
 * <p>
 * 필요한 메서드
 * 1. 벽 3가지를 선택하는 법은 3중포문 쓰고
 * 2. i,j,k가 퍼지는 것은 bfs를 사용해서 계산
 * 3. n*m - 벽의 갯수 - 바이러스 갯수
 *
 * 필요한 변수
 * 2인 좌표를 담는 리스트 - twoList
 * <p>
 * <p>
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        int one = 3;

        List<int[]> twoList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j]==1){
                    one++;
                }
                else if(grid[i][j]==2){
                    twoList.add(new int[]{i,j});
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n * m; i++) {
            int r1 = i / m;
            int c1 = i % m;
            for (int j = i + 1; j < n * m; j++) {
                int r2 = j / m;
                int c2 = j % m;
                for (int k = j + 1; k < n * m; k++) {
                    int r3 = k / m;
                    int c3 = k % m;
                    if (grid[r1][c1] == 0 && grid[r2][c2] == 0 && grid[r3][c3] == 0) {
//                        System.out.println("(" + r1 + "," + c1 + "), (" + r2 + "," + c2 + "), (" + r3 + "," + c3 + ")");
                        grid[r1][c1] = 1;
                        grid[r2][c2] = 1;
                        grid[r3][c3] = 1;
                        visited = new boolean[n][m];
                        twoQue = new LinkedList<>();
                        for (int l = 0; l < twoList.size(); l++) {
                            int[] node = twoList.get(l);
                            int r = node[0];
                            int c = node[1];
                            visited[r][c] = true;
                            twoQue.add(new int[]{r,c});
                        }
                        int virus = bfs();
                        virus = n*m - one - virus;
                        if(virus>ans){
                            ans = virus;
                        }
                        grid[r1][c1] = 0;
                        grid[r2][c2] = 0;
                        grid[r3][c3] = 0;
                    }
                }
            }
        }

        System.out.println(ans);


    }
    static boolean[][] visited;
    static int[][] grid;
    static Queue<int[]> twoQue;
    static int[] row = {-1,1,0,0};
    static int[] col = {0,0,1,-1};
    static int n;
    static int m;

    private static int bfs() {

        int cnt = 0;

        while (!twoQue.isEmpty()){
            int size = twoQue.size();
            for(int i = 0; i<size; i++){
                int[] node =twoQue.poll();
                int r = node[0];
                int c = node[1];
                cnt++;
                for (int k = 0; k < 4; k++) {
                    int nr= r+row[k];
                    int nc= c+col[k];
                    if(nr>=0&& nr<n&& nc>=0&&nc<m&&grid[nr][nc]==0&&!visited[nr][nc]){
                        visited[nr][nc] = true;
                        twoQue.add(new int[]{nr, nc});
                    }
                }
            }
        }

        return cnt;
    }
}
