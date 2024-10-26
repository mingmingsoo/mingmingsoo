import java.util.*;
class Solution {
    static boolean[][] visited;
    static int n;
    static int m;
    static int ans;
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];
        ans = Integer.MAX_VALUE;
        bfs(0,0,1,maps);
        if(ans ==Integer.MAX_VALUE){
            ans = -1;
        }
        return ans;
    }
    static int[] row = {1,-1,0,0};
    static int[] col ={0,0,1,-1};
    
    static void bfs(int r, int c,int sum, int[][] maps){
        visited[r][c] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r,c,sum});
        while(!q.isEmpty()){
            int[] node = q.poll();
            int rr = node[0];
            int cc = node[1];
            int ss = node[2];
            if(rr == n-1&& cc ==m-1){
                ans = Math.min(ans,ss);
            }
            
            for(int k = 0; k<4;k++){
                int nr = rr+row[k];
                int nc = cc+col[k];
                if(nr>=0&&nr<n&&nc>=0&&nc<m&&!visited[nr][nc]&&maps[nr][nc]==1){
                    visited[nr][nc]= true;
                    q.add(new int[]{nr,nc,ss+1});
                }
                
            }
            
        }
 
    }
}