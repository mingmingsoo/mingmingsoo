

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * X일 동안 가장 많이 들어온 방문자 수와 그 기간 찾기.
 *
 * 입력
 * 총 N일
 * 가장 많이 들어온 기간 X일
 *
 * 출력
 * X일동안 가장 많이 들어온 방문자 수
 * 기간이 총 몇개 인지
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] dailyInfo = new int[N]; // 데일리 방문자 수 기록
        for (int i = 0; i < N; i++) {
            dailyInfo[i] = Integer.parseInt(st.nextToken());
        }

        int[] prefixSum = new int[N]; // 누적합 배열


        int start = 0; // 누적합이므로 X일부터 시작해도 됨으로 초깃값 계산
        for (int i = 0; i < X; i++) {
            start+=dailyInfo[i];
        }
        prefixSum[X-1] = start;
        int maxSum = start;
        for (int i = X; i < N; i++) { // 누적합 계산
            prefixSum[i] = prefixSum[i-1]+dailyInfo[i]-dailyInfo[i-X];
            maxSum = Math.max(maxSum, prefixSum[i]);
        }

        if(maxSum == 0){
            System.out.println("SAD");
            return;
        }

        int maxSumCnt = 0;
        for (int i = X-1; i < N; i++) {
            if(prefixSum[i] == maxSum){
                maxSumCnt++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(maxSum).append("\n").append(maxSumCnt);
        System.out.println(sb);



    }

}
