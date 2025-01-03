

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 설탕을 정확하게 N킬로그램 배달
 * 봉지는 3, 5키로 봉지가 있음
 * 최대한 적은 봉지로 들고가야함!
 * 가장 최소 봉지 수는?
 *
 * 3x+5y = 18
 * x가 0~6일때까지 계산
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int x3 = N / 3;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= x3; i++) {
            int kg5 = N - 3*i;
            if(kg5%5==0){
                int x5 = kg5 / 5;
                ans = Math.min(ans, i+x5);
            }
        }
        if(ans == Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        System.out.println(ans);

    }
}
