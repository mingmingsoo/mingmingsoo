class Solution {
    static int ans;
    public int solution(int[] numbers, int target) {

        dfs(numbers,0,0,target);
        return ans;

    }
    
    public void dfs(int[] numbers, int depth, int sum, int target){
        if(depth==numbers.length){
            if(sum==target){
                ans++;
            }
            return;
        }
        dfs(numbers, depth+1, sum+numbers[depth] ,target);
        dfs(numbers, depth+1, sum-numbers[depth] ,target);
  
    }
}