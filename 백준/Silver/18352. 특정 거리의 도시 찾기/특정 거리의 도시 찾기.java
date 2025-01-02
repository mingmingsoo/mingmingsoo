
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 특정 도시 X에서 최단 거리가 정확히 K인 도시 번호 모두 출력.
 * 1. BFS로 풀기
 * 2. 다익스트라로 풀기
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); // 정점 수
        E = Integer.parseInt(st.nextToken()); // 간선 수
        K = Integer.parseInt(st.nextToken()); // 거리 K
        X = Integer.parseInt(st.nextToken()); // 출발 정점 X

        grid = new ArrayList[V+1];
        for (int i = 0; i < V+1; i++) {
            grid[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
//            int from = Integer.parseInt(st.nextToken());
//            int to = Integer.parseInt(st.nextToken());
            grid[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }
//        System.out.println(Arrays.toString(grid));
        bfs(X);
        if(ansList.isEmpty()){
            System.out.println(-1);
            return;
        }
        Collections.sort(ansList);
        StringBuilder sb = new StringBuilder();
        for (int ans : ansList){
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    static List<Integer> ansList = new ArrayList<>();
    static List<Integer>[] grid;
    static int K;
    static int V;
    static int E;
    static int X;
    private static void bfs(int start) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[V+1];
        q.add(new int[]{start, 0});
        visited[start] = true;

        while(!q.isEmpty()){
            int[] node = q.poll();
            int now = node[0];
            int dist = node[1];
            if(dist == K){
                ansList.add(now);
            }

            for (int next: grid[now]){
                if(!visited[next]){
                    q.add(new int[]{next, dist+1});
                    visited[next] = true;
                }
            }


        }

    }
}
