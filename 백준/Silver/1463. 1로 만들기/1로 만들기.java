
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 3으로 나누어 떨어지면 3으로 나눔
 * 2로 나누어 떨어지면 2로 나눔
 * 1을 뺌
 *
 * 위 3가지 연산을  통해 1로 만든다.
 * 연산 횟수 최솟값
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n+1];
        for (int i = 2; i <=n ; i++) {
            int minCnt = n;
            minCnt = Math.min(dp[i-1]+1,minCnt);
            if(i%2==0){
                minCnt = Math.min(dp[i/2]+1,minCnt);
            }
            if(i%3==0){
                minCnt = Math.min(dp[i/3]+1,minCnt);
            }
            dp[i] = minCnt;
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[n]);
    }
}
