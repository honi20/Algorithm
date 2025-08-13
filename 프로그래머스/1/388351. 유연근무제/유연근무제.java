class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for (int idx = 0; idx < timelogs.length; idx++) {
            // 해당 직원의 출근 인정 시각
            int admitTime = getAdmitTime(schedules[idx]);
            boolean check = true;
            
            // 월(1), 화(2), ..., 7(일)
            for (int day = 0; day < timelogs[idx].length; day++) {
                int curDay = startday + day;
                
                if (curDay % 7 == 0) {
                    curDay = 7;
                }
                else {
                    curDay %= 7;
                }
                
                // 주말은 패스
                if ((curDay == 6) || (curDay == 7)) {
                    continue;
                }
                
                // 지각한 경우
                if (timelogs[idx][day] > admitTime) {
                    check = false;
                    break;
                }
            }
            
            if (check) {
                ++answer;
            }
        }
        return answer;
    }
    
    private int getAdmitTime(int time) {
        int admitTime = time + 10;
        
        int hour = admitTime / 100;
        int minute = admitTime % 100;
        
        if (minute >= 60) {
            admitTime = (hour + 1) * 100 + (minute - 60);
        }
        
        return admitTime;
    }
}