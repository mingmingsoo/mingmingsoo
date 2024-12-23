

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int score;
    static int[] arr;
    static int[] sel;
    static int[] order;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        order = new int[10];

        for (int i = 0; i < 10; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }
//        System.out.println(Arrays.toString(order));

        map = new int[5][21];
        map[0] = new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40};
        map[1] = new int[]{10, 13, 16, 19, 25};
        map[2] = new int[]{20, 22, 24, 25};
        map[3] = new int[]{30, 28, 27, 26, 25};
        map[4] = new int[]{25, 30, 35, 40};

        arr = new int[]{1, 2, 3, 4};
        sel = new int[10];
        score = -1;
        duplePerm(0);
        System.out.println(score);


    }

    private static void duplePerm(int idx) {
        if (idx == 10) {
//            if(sel[0]==4&&sel[1]==4&&sel[2]==3&&sel[3]==4&&sel[4]==2&&sel[5]==2&&sel[6]==2&&sel[7]==4&&sel[8]==2&&sel[9]==2){
            // sel이 말 순서
            // order가 주사위칸(1~5칸)
//            System.out.println(Arrays.toString(sel));
            String[] visited = new String[]{"", "0,0", "0,0", "0,0", "0,0"};
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                int dice = order[i]; // 1칸
                int horse = sel[i]; // 4번말
                String[] before = visited[horse].split(",");
                int row = Integer.parseInt(before[0]);
                int col = Integer.parseInt(before[1]) + dice;
//                System.out.println("horse: "+horse+", row: " + row + ", col: " + col);
                // 고려할 점 2가지
                // 1. 10, 20, 30은 파란색임 -> 위치 변환
                if (row == 0 && col == 5) {
                    row = 1;
                    col = 0;
                } else if (row == 0 && col == 10) {
                    row = 2;
                    col = 0;
                } else if (row == 0 && col == 15) {
                    row = 3;
                    col = 0;
                } else if (row == 1 && col >= 5) {
                    row = 4;
                    col = col - 4;
                } else if (row == 2 && col >= 4) {
                    row = 4;
                    col = col - 3;
                } else if (row == 3 && col >= 5) {
                    row = 4;
                    col = col - 4;
                }

                if (row == 4 && col >= 4) {
                    row = 4;
                    col = 4; // 종료
                }
                else if (row == 0 && col >= 21) {
                    row = 0;
                    col = 21; // 종료
                }
                else if (row == 4 && col == 3) {
                    row = 0;
                    col = 20;
                }
//                System.out.println("horse: "+horse+", row: " + row + ", col: " + col);
                // 2. 이동하는 위치에 말이 없어야 이동
                String now = row + "," + col;
                if (now.equals("4,4") || now.equals("0,21")) {
                    visited[horse] = "0,0";
                    continue;
                }
                for(int j = 1; j<5; j++){
                    if(j!=horse&&visited[j].equals(now)){
                        return;
                    }
                }
                sum += map[row][col];
                visited[horse] = now;

//                System.out.println(Arrays.toString(visited));
//                System.out.println(i + "- " + sum);
            }
            if (score < sum) {
                score = sum;
            }


//            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            sel[idx] = arr[i];
            duplePerm(idx + 1);
        }
    }

}
