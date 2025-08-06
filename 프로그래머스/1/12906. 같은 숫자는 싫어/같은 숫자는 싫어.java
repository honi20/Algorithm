import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        List<Integer> result = new ArrayList<>();
        for (int idx = 0; idx < arr.length; idx++) {
            if (!result.isEmpty() && result.get(result.size()-1) == arr[idx]) {
                continue;
            }
            
            result.add(arr[idx]);
        }
        
        int[] answer = new int[result.size()];
        for (int idx = 0; idx < result.size(); idx++) {
            answer[idx] = result.get(idx);
        }
        
        return answer;
    }
}