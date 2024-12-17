
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 톱니바퀴 날 8개
 * 겹치는 부분이 극이 다르면 반대로 회전
 * 겹치는 부분이 극이 같으면 회전 X
 * <p>
 * 입력값
 * 총 톱니바퀴 갯수
 * 톱니바퀴 상태 0, 1 - 12시 방향부터 시계방향 순으로
 * 회전 횟수
 * 회전 방법 (톱니바퀴 번호, 방향) - 방향은 1이면 시계 -1이면 반시계
 * <p>
 * 짝수번째 겹치는 부분 - 2
 * 홀수번째 겹치는 부분 - 6
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        int[][] grid = new int[t][8];
        for (int i = 0; i < t; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < 8; j++) {
                grid[i][j] = tmp.charAt(j) - '0';
            }
        }

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir_right = Integer.parseInt(st.nextToken());
            int dir_left = dir_right;
//            System.out.println("num " + num + ", dir " + dir_right);

            int[] state = new int[t]; // 변화여부를 담을 배열
            state[num] = dir_right; // 시작점은 무조건 회전
            // 오른쪽 먼저
            for (int ii = num + 1; ii < t; ii++) {
                if (ii%2 !=0 &&grid[ii - 1][2] != grid[ii][6]) {
                    dir_right *= -1;
                    state[ii] = dir_right;
                }
                else if(ii%2 ==0 &&grid[ii - 1][2] != grid[ii][6]) {
                    dir_right *= -1;
                    state[ii] = dir_right;
                }
                else {
                    break;
                }
            }

            // 왼쪽
            for (int ii = num-1; ii >=0; ii--) {
                if (ii%2 !=0 &&grid[ii +1][6] != grid[ii][2]) {
                    dir_left *= -1;
                    state[ii] = dir_left;
                }
                else if(ii%2 ==0 &&grid[ii + 1][6] != grid[ii][2]) {
                    dir_left *= -1;
                    state[ii] = dir_left;
                }
                else {
                    break;
                }
            }

            for(int ii = 0; ii<t; ii++){
                if(state[ii]==-1){
                    rotation2(grid[ii]);
                }
                else if(state[ii] ==1){
                    rotation1(grid[ii]);
                }
            }
        }

//        print(grid);

        int ans = 0;
        for (int i = 0; i < t; i++) {
            if (grid[i][0] == 1) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static void print(int[][] grid) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------");
    }

    private static void rotation1(int[] arr) {
        // 시계방향으로 회전
        int tmp = arr[7];
        for (int i = 7; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = tmp;

    }

    private static void rotation2(int[] arr) {
        // 반시계방향으로 회전
        int tmp = arr[0];
        for (int i = 0; i < 7; i++) {
            arr[i] = arr[i + 1];
        }
        arr[7] = tmp;
    }

}
