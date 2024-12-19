

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시작점이 W이거나 B이거나
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new Character[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = str.charAt(j);
            }
        }
        int startI;
        int startJ;
        int ansW = Integer.MAX_VALUE;
        int ansB = Integer.MAX_VALUE;
        for (int i = 0; i <= n-8; i++) {
            for (int j = 0; j <= m-8; j++) {
                startI = i;
                startJ = j;
                int tmpW = changeW(startI, startJ);
                int tmpB = changeB(startI, startJ);
                if(tmpW<ansW){
                    ansW = tmpW;
                }
                if(tmpB<ansB){
                    ansB = tmpB;
                }
            }
        }

//        System.out.println(ansW);
//        System.out.println(ansB);
        if(ansW > ansB){
            System.out.println(ansB);
        }
        else{
            System.out.println(ansW);
        }
    }

    private static int changeB(int startI, int startJ) {
        int cnt = 0;

        for (int i = startI; i < startI+8; i++) {
            for (int j = startJ; j < startJ+8; j++) {
                if((i+j)%2==0&& grid[i][j]=='W'){
                    cnt++;
                }
                else if((i+j)%2==1&&grid[i][j]=='B'){
                    cnt++;
                }

            }
        }





        return cnt;
    }

    static int n;
    static int m;
    static Character[][] grid;

    private static int changeW(int startI, int startJ) {
        int cnt = 0;

        for (int i = startI; i < startI+8; i++) {
            for (int j = startJ; j < startJ+8; j++) {
                if((i+j)%2==0&& grid[i][j]=='B'){
                    cnt++;
                }
                else if((i+j)%2==1&&grid[i][j]=='W'){
                    cnt++;
                }

            }
        }




        return cnt;
    }
}
