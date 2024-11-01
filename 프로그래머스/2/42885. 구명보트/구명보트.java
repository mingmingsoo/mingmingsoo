import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int total = people.length;
        int twoSize = 0;
        Arrays.sort(people);
        
        int left = 0;
        int right = total-1;
        
        while(left<right){
            int sum = people[left]+people[right];
            if(sum<=limit){
                twoSize++;
                left++;
                right--;
            }
            else{
                right--;
            }      
        }
  
        return total-twoSize;
    }
}