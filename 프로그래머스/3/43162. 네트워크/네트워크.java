class Solution {
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        for(int i = 0; i<n;i++){
            if(!visited[i]){
                answer++;
                dfs(i,computers,n);
            }
        }
        return answer;
    }
    
    public static void dfs(int r, int[][] computers,int n){
        visited[r] = true;
        
        for(int j = 0; j<n;j++){
            if(!visited[j]&&computers[r][j]==1){
                dfs(j,computers,n);
            }
        }
            
    }
}