

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 야구는 9명으로 이루어진 두 팀이 공격과 수비를 번갈아서
 * 하나의 이닝은 공격과 수비로 이루어져있고 총 N이닝 동안 게임 진행
 * 한 이닝에 3아웃 발생시 이닝 종료, 두 팀이 공격과 수비를 바꿈
 * 경기 시작 전 타순을 정해야함
 * 9번 타자까지 공을 쳤는데 3아웃이 발생하지 않으면 1번 타자가 다시 타석에 선다
 * -> 2 이닝 6번 타자가 마지막 타자면 그 다음 이닝은 7번 타자부터 타석에 섬
 * 안타/2루타/3루타/홈런/아웃
 * <p>
 * 1번 선수(=0번 선수)는 항상 4번 타자
 * <p>
 * 안타: 1
 * 2루타: 2
 * 3루타: 3
 * 홈런: 4
 * 아웃: 0
 * <p>
 * 필요한 메서드
 * 타자 순서를 정하는 메서드 - perm()
 * 야구를 진행하는 메서드 - shoot()
 * <p>
 * 필요한 변수
 * 타율을 기록 - info[][]
 * 타석을 기록 - boolean state[]
 * 점수를 기록 - int score
 */
public class Main {

    static int N;
    static int[][] info;
    static int[] sel;
    static boolean[] visited = new boolean[9];
    static int totalScore;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        info = new int[N][9];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        totalScore = -1;
        sel = new int[9];
        perm(0);

        System.out.println(totalScore);
    }

    private static void perm(int idx) {
        if (idx == 9 ) {
            int maxScore = 0;
            int order = 0;

            for (int inning = 0; inning < N; inning++) {
                int out = 0;

                boolean[] state = new boolean[3];
                while (true) {

                    int people = sel[order%9]; // 1번 선수
                    int score = info[inning][people];
                    if (score == 0) {
                        out++;
                    } else if (score == 1) {
                        boolean last = state[2];
                        for (int i = 2; i > 0; i--) {
                            state[i] = state[i - 1];
                        }
                        state[0] = true;
                        if(last){
                            maxScore++;
                        }
                    } else if (score == 2) {
                        boolean last = state[2];
                        boolean last2 = state[1];
                        state[2] = state[0];
                        state[1] = true;
                        state[0] = false;
                        if(last){
                            maxScore++;
                        }
                        if(last2){
                            maxScore++;
                        }


                    } else if (score == 3) {
                        boolean last = state[2];
                        boolean last2 = state[1];
                        boolean last3 = state[0];
                        state[2] = true;
                        state[1] = false;
                        state[0] = false;
                        if(last){
                            maxScore++;
                        }
                        if(last2){
                            maxScore++;
                        }
                        if(last3){
                            maxScore++;
                        }


                    } else if (score == 4) {
                        maxScore++;
                        for (int i = 0; i < 3; i++) {
                            if(state[i]){
                                maxScore++;
                            }
                        }
                        Arrays.fill(state,false);
                    }
                    order++;
                    if (out >= 3) {
                        break ;
                    }
                }
            }

            if(maxScore >totalScore){
                totalScore= maxScore;
            }
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (!visited[i]) {
                if (idx == 3 && i != 0) {
                    continue;
                }
                sel[idx] = i;
                visited[i] = true;
                perm(idx + 1);
                visited[i] = false;
            }
        }

    }
}
