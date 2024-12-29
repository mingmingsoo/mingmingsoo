

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 각 점에서 순환선까지의 거리를 계산하는데,
 * 순환선에 포함되는 정점은 0 이고
 * 순환선에 포함되지 않는 정점은 최소 거리를 출력
 * <p>
 * 1. 노드를 연결하고
 * 2. dfs 확인해서 연결되는 순환선이면 state 를 true
 * 3. false인 애들의 최소 거리를 구하면 됨
 */
public class Main {

    static class Node{
        int num;
        int dist;

        public Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }

    static int V;
    static List<Integer> list;
    static List<Integer>[] grid;
    static boolean[] cycleState;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        grid = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++) {
            grid[i] = new ArrayList<>();
        }
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            grid[from].add(to);
            grid[to].add(from);
        }
//        System.out.println(Arrays.toString(grid));

        cycleState = new boolean[V+1];

        for (int i = 1; i < V+1; i++) {
            if(checkCycleDfs(i,i,i)){ // 싸이클 찾기
                break; // 싸이클은 하나만 있으므로 break
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < V+1; i++) {
            if(!cycleState[i]){ // 싸이클이 아니면 거리를 계산해주기
                int d = calDistanceBfs(i);
                sb.append(d).append(" ");
            }
            else{
                sb.append(0).append(" "); // 싸이클이면 0
            }
        }
        System.out.println(sb);
    }
    private static boolean checkCycleDfs(int start, int prev, int now) {
        cycleState[now] = true; // 현재 노드
        for (int next: grid[now]){
            if(!cycleState[next]){
                if(checkCycleDfs(start,now,next)){
                    return true;
                }
            } else if (prev != next && next == start) {
                // 이전 값이 다음값과 같지 않고, 시작점으로 다시 돌아왔다면
                return true;
            }
        }
        cycleState[now] = false; // 원상복귀
        return false;
    }

    private static int calDistanceBfs(int start) {
        boolean[] visited = new boolean[V+1];
        Queue<Node> q= new LinkedList<>();
        q.offer(new Node(start,0));
        visited[start] =  true;

        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node now = q.poll();
                if(cycleState[now.num]){
                    // 싸이클에 도착하면 거리 출력
                    return now.dist;
                }

                for (int next : grid[now.num]){
                    if(!visited[next]){
                        visited[next] = true;
                        q.offer(new Node(next, now.dist+1));
                    }
                }
            }
        }
        return 0;
    }

}
