

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 연결 노드에서
 * 하나의 노드를 지웠을 때
 * 자식 없는 리프노드를 구하기.
 * <p>
 * 1. 연결 노드 만들기 List<Integer>[] grid
 * 2. deleteNodeDfs(지울 노드) - grid 수정 필요
 * 3. countParent() - 리프노드 세기
 */
public class Main {
    static List<Integer>[] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int V = Integer.parseInt(br.readLine());
        grid = new ArrayList[V];
        st = new StringTokenizer(br.readLine()); // 부모노드 정보
        for (int i = 0; i < V; i++) {
            grid[i] = new ArrayList<>();
        }
        int top = -1;
        for (int i = 0; i < V; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1) {
                grid[parent].add(i);
            } else {
                top = i;
            }
        }
        int deleteNode = Integer.parseInt(br.readLine());
//        System.out.println(Arrays.toString(grid));
        deleteNodeDfs(deleteNode);

//        System.out.println(Arrays.toString(grid));
        if (grid[top] != null) {
            for (int i = 0; i < grid[top].size(); i++) {
                if (grid[top].get(i) == deleteNode) {
                    grid[top].remove(i);
                    break;
                }
            }
        }
//        System.out.println(Arrays.toString(grid));

        for (int i = 0; i < V; i++) {
            if (grid[i] != null) {
                for (int j = 0; j < grid[i].size(); j++) {
                    if (grid[i].get(j) == deleteNode) {
                        grid[i].remove(j);
                    }
                }
            }
        }

        // 남은 노드 갯수
        int leaf = 0;
        for (int i = 0; i < V; i++) {
            if (grid[i] != null && grid[i].size() == 0) {
                leaf++;
            }
        }
        System.out.println(leaf);

    }

    private static void deleteNodeDfs(int node) {
        for (int next : grid[node]) {
            if (grid[next] != null) {
                deleteNodeDfs(next);
            }
        }
        grid[node] = null;
    }
}
