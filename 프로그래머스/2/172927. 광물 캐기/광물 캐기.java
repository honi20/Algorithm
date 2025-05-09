class Solution {
    final int DIAMOND = 0;
    final int IRON = 1;
    final int STONE = 2;
    
    final int[][] cost = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    
    int result;
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        result = Integer.MAX_VALUE;
        solve(0, 0, -1, 0, picks, minerals);
        
        return answer = result;
    }
    
    private void solve(int curIdx, int curCost, int prePick, int preCnt, int[] picks, String[] minerals) {
        // System.out.println(curIdx + " " + curCost + " " + prePick + " " + preCnt);
        
        // 모든 광물을 확인한 경우, 종료
        if (curIdx == minerals.length) {
            result = Math.min(result, curCost);
            return;
        }
        
        // 이미 최소 피로도가 정해진 경우, 종료
        if (curCost > result) {
            return;
        }
        
        boolean flag = false;
        
        // 사용할 수 있을 때까지 사용
        if ((prePick != -1) && (preCnt < 5)) {
            int mineralIdx = getMineralIdx(minerals[curIdx]);
            int nowCost = cost[prePick][mineralIdx];
            
            solve(curIdx + 1, curCost + nowCost, prePick, preCnt + 1, picks, minerals);
        }
        // 사용할 수 없는 경우
        else {
            // 모든 곡괭이를 사용한 경우
            if ((picks[DIAMOND] + picks[IRON] + picks[STONE]) == 0) {
                result = Math.min(result, curCost);
                return;
            }
            
            for (int idx = 0; idx < 3; idx++) {        
                if (picks[idx] <= 0) {
                    continue;
                }
                
                int mineralIdx = getMineralIdx(minerals[curIdx]);
                int nowCost = cost[idx][mineralIdx];

                --picks[idx];
                solve(curIdx + 1, curCost + nowCost, idx, 1, picks, minerals);
                ++picks[idx];
            }
        }
    }
    
    private int getMineralIdx(String mineral) {
        switch(mineral) {
            case "diamond": return 0;
            case "iron": return 1;
            case "stone": return 2;
        }
        
        return -1;
    }
}