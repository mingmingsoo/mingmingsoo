import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1번과 연결된 간선을 통해 정점 갯수 구하기
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        visited  = new boolean[V+1];

        grid = new ArrayList[V+1];
        for (int i = 1; i < V+1; i++) {
            grid[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            grid[from].add(to);
            grid[to].add(from);
        }

//        System.out.println(Arrays.toString(grid));
        cnt = 0;
        dfs(1);
        if(cnt == 0){
            System.out.println(0);
            return;
        }
        System.out.println(cnt-1);
    }
    static int cnt;
    static boolean[] visited;
    static  List<Integer>[] grid;
    private static void dfs(int start) {
//        System.out.println(start);
        for (int node:grid[start]){
            if(!visited[node]){
                visited[node] = true;
                cnt++;
                dfs(node);
            }
        }
    }

}
