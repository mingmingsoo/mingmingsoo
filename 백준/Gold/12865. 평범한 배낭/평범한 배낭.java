

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] weights = new int[N+1];
        int[] costs = new int[N+1];
        int[][] dp = new int[N+1][W+1];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            costs[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < N+1; i++) {
            for (int w = 1; w < W+1; w++) {
                if(weights[i]<=w){
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weights[i]]+costs[i]);
                }
                else{
                    dp[i][w] = dp[i-1][w];
                }
            }
        }
        System.out.println(dp[N][W]);
    }
}
