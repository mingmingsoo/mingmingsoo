
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 2*n 크기 직사각형을 채우는 방법
 * 9일 떄 55 이므로... 피보나치~
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine())+1;
        int[] dp = new int[n+1];
        /*
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
         */
        dp[1] = 1;
        for (int i = 2; i <= n ; i++) {
            dp[i] = (dp[i-1]+dp[i-2])%10007;
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[n]);
    }
}
