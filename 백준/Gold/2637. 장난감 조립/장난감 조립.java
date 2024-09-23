

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();  
        int E = sc.nextInt(); 
        int[][] adj = new int[V + 1][V + 1];  
        int[] degree = new int[V + 1];        
        int[][] each = new int[V + 1][V + 1]; // 각 부품 수량


        for (int i = 0; i < E; i++) {
            int to = sc.nextInt();
            int from = sc.nextInt();
            int weight = sc.nextInt();
            adj[from][to] = weight;
            degree[to]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= V; i++) {
            if (degree[i] == 0) { // 기본부품
                q.add(i);
                each[i][i] = 1;  // 최소 1개씩
            }
        }

        while (!q.isEmpty()) {
            int ele = q.poll();  // 현재 부품 번호
            
            // 현재 부품을 이용해 만들 수 있는 부품들
            for (int i = 1; i <= V; i++) {
                if (adj[ele][i] > 0) {  // ele 부품을 이용해 i 부품을 만듦
                    for (int j = 1; j <= V; j++) {
                        each[i][j] += each[ele][j] * adj[ele][i];  
                    }
                    degree[i]--;  
                    
                    if (degree[i] == 0) {
                        q.add(i);  
                    }
                }
            }
        }

        for(int i = 1 ;i <V+1; i++) {
        	if(degree[i]==0&&each[V][i]>0) {
        		System.out.println(i+" "+each[V][i]);
        	}
        }
    }
}