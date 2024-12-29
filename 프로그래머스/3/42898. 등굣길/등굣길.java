import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int mod = 1000000007;
        int[][] map = new int[n][m];
        int[][] dp = new int[n][m];
        for(int i = 0; i<puddles.length;i++){
            map[puddles[i][1]-1][puddles[i][0]-1] = 1;
        }
        
        for(int j = 1; j<m;j++){
            if(map[0][j]==0){
                dp[0][j] =1;         
            }
            else{
                break;
            }
        }
        for(int i = 1; i<n;i++){
            if(map[i][0]==0){
                dp[i][0] =1;
            }
            else{
                break;
            }
        }

        
        for(int i = 1; i<n;i++){
            for(int j = 1; j<m;j++){
                if(map[i][j]==0){
                    if(map[i-1][j]==0&&map[i][j-1]==0){
                        dp[i][j] = (dp[i-1][j]+dp[i][j-1])%mod; 
                    }
                    else if(map[i-1][j]==0&&map[i][j-1]==1){
                        dp[i][j] = dp[i-1][j]%mod; 
                    }
                    else if(map[i-1][j]==1&&map[i][j-1]==0){
                        dp[i][j] = dp[i][j-1]%mod; 
                    }
                    else if(map[i-1][j]==1&&map[i][j-1]==1){
                        dp[i][j] = 0; 
                    }
                }
                else{
                    continue;
                }
             
            }
        }
        // System.out.println(Arrays.deepToString(map));
        // System.out.println(Arrays.deepToString(dp));
        
        return dp[n-1][m-1];
    }
}