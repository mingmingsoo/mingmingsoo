/**
다양한 선수들이 우승하길 바래서 전 우승자인 라이언에겐 불리한 조건을 설정
1. 어피치가 화살 n발을 다 쏜 후에 라이언이 n발을 쏨
2. 점수 계산
    - 0~10점의 점수
    - k점을 어피치가 a발, 라이언이 b발을 맞춘 경우 더 많이 맞힌 선수가 k점을 가져감
      단, a = b 일 경우 어피치가 k점을 가져감
      * k점을 여러발 맞혀도 k점만 가져가는 것
    - 최종점수가 높은 사람이 우승자인데, 점수가 같을 경우 어피치가 우승
화살의 갯수 n, 어피치의 점수 정보 info - 10,9,8, ... 순임!
라이언이 가장 큰 점수 차이로 우승하기 위해 n발의 화살은 어디다 맞혀야 하는지 배열을 생성해야함.
* 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우를 return 해주세요.
라이언이 우승할 수 없는 경우는 [-1] 출력

필요한 메서드
- perm(0,0) : 0~10의 숫자를 n개의 중복순열 생성
- calcul : 어피치와의 점수 비교


*/
import java.util.*;
class Solution {
    static int[] arr;
    static int[] sel;
    static int[] answer;
    static int minus = 0;
    public int[] solution(int n, int[] info) {
        answer = new int[11]; // 라이언 양궁 점수
        
        arr = new int[]{0,1,2,3,4,5,6,7,8,9,10};
        sel = new int[n]; // 선택할 배열
        
        dupleCombi(0,0,n,info);
        
        if(minus==0){
            return new int[]{-1};
        }
        
        return answer;
    }

    static public void dupleCombi(int idx, int sidx, int N, int[] apeaches) {

        if (sidx == N) {

            // 라이언배열 생성
            int[] lions = new int[11];
            for (int i = 0; i < N; i++) {
                lions[10 - sel[i]]++;
            }

            int apeach = 0;
            int lion = 0;

            // 점수 계산
            for (int i = 0; i < 11; i++) {
                if(apeaches[i]==0 && lions[i]==0){
                    continue;
                }
                if (apeaches[i] > lions[i]) {
                    apeach += (10 - i);
                } else if (apeaches[i] == lions[i]) {
                    apeach += (10 - i);
                } else if (apeaches[i] < lions[i]) {
                    lion += (10 - i);
                }
            }
            int m = lion - apeach;
            
            if(m<=minus){
                return;
            }

            if (m > minus) {
                minus = m;
                System.arraycopy(lions, 0, answer, 0, 11);
            }

            return;
        }
        if(idx == 11){
            return;
        }
        sel[sidx] = arr[idx];
        dupleCombi(idx, sidx+1, N, apeaches);
        dupleCombi(idx+1, sidx, N, apeaches);
    }
   
}