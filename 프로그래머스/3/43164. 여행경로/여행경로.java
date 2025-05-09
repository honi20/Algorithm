import java.util.*;

class Solution {
    class Airport implements Comparable<Airport> {
        String start;
        String end;
        
        public Airport(String start, String end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Airport a) {
            if (this.start.equals(a.start)) {
                return this.end.compareTo(a.end);
            }
            return this.start.compareTo(a.start);
        }
    }
    
    List<Airport> airports;
    String[] result;
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        airports = new ArrayList<>();
        for (int idx = 0; idx < tickets.length; idx++) {
            airports.add(new Airport(tickets[idx][0], tickets[idx][1]));
        }
        Collections.sort(airports);
        
        String[] orders = new String[tickets.length + 1];
        int isVisited = 0;
        orders[0] = "ICN";
        
        solve(1, isVisited, orders);
        
        return answer = result;
    }
    
    private void solve(int curIdx, int isVisited, String[] orders) {
        // 모든 항공권을 탐색한 경우
        if (curIdx == (airports.size() + 1)) {
            result = Arrays.copyOf(orders, orders.length);
            return;
        }
        
        // 이미 구해진 경우
        if (result != null) {
            return;
        }
        
        // 다른 항공권 확인
        for (int idx = 0; idx < airports.size(); idx++) {            
            // 이미 방문한 항공권이 경우
            if ((isVisited & (1 << idx)) == (1 << idx)) {
                continue;
            }
            
            String preAirport = orders[curIdx - 1];
            Airport airport = airports.get(idx);
            
            if (airport.start.equals(preAirport)) {
                orders[curIdx] = airport.end;
                solve(curIdx + 1, isVisited | (1 << idx), orders);
            }
        }
    }
    
}