class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int s = 1;
        int e = getMaxDiff(diffs);
        
        while (s <= e) {
            int mid = (s + e) / 2;
            
            // 해당 숙련도의 전체 소요시간
            long total = 0;
            for (int idx = 0; idx < diffs.length; idx++) {
                total += getSolvedTime(mid, idx, diffs, times);
                
                // 이미 제한시간을 넘긴 경우, 패스
                if (total > limit) {
                    break;
                }
            }
            
            // 제한시간 안에 모든 퍼즐 해결이 가능한 경우
            if (total <= limit) {
                answer = mid;
                e = mid - 1;
            }
            else {
                s = mid + 1;
            }
        }
        
        return answer;
    }
    
    private long getSolvedTime(int level, int index, int[] diffs, int[] times) {       
        if (level >= diffs[index]) {
            return (long) times[index];
        }
        
        return (long) ((times[index] + times[index-1]) * (diffs[index] - level)) + times[index];
    }
    
    private int getMaxDiff(int[] diffs) {
        int result = 0;
        for (int idx = 0; idx < diffs.length; idx++) {
            result = Math.max(result, diffs[idx]);
        }
        
        return result;
    }
}