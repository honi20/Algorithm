class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        int[] operating = new int[players.length];
        int[] addition = new int[players.length];
        
        for (int idx = 0; idx < players.length; idx++) {
            // 현재 운영 중인 서버
            operating[idx] = getOperatingServerCnt(idx, addition, k);
            
            // 현재 필요한 서버
            int neededCnt = players[idx] / m;
            
            // 서버가 더 필요한 경우
            if (operating[idx] < neededCnt) {
                addition[idx] = neededCnt - operating[idx];
                operating[idx] += neededCnt;
                answer += addition[idx];
            }
        }
        
        return answer;
    }
    
    private int getOperatingServerCnt(int cur, int[] addition, int k) {
        int cnt = 0;
        
        for (int idx = 0; idx < k; idx++) {
            if (cur - idx < 0) {
                break;
            }
            
            cnt += addition[cur - idx];
        }
        
        return cnt;
    }
}