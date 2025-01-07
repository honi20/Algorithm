/**
[조건]
- 유일성 : 릴레이션에 있는 모든 튜플에 대해 유일하게 식별 가능
- 최소성 : 릴레이션의 유일성을 유지하는데 꼭 필요한 속성들로만 이루어져야 함

[출력]
- 후보 키의 최대 개수
*/
import java.util.*;

class Solution {
    static int tupleCnt, attrCnt;
    static boolean[] isCandidate;
    static int bitCnt;
    
    public int solution(String[][] relation) {
        int answer = 0;
        
        tupleCnt = relation.length;
        attrCnt = relation[0].length;
        bitCnt = (1 << attrCnt);
        isCandidate = new boolean[bitCnt];
        
        Arrays.fill(isCandidate, true);
        
        for (int bits = 1; bits < bitCnt; bits++) {
            // 최소성과 유일성을 만족하는지 확인
            if (isCandidate[bits] && isUnique(bits, relation)) {
                // 현재 비트 집합을 포함하는 나머지 집합 false 처리 (최소성 불만족)
                updateMinimal(bits, 0);
                
                printBits(bits);
                ++answer;
            }
        }
        
        return answer;
    }
    
    public void updateMinimal(int curBits, int curIdx) {
        if (curIdx > attrCnt) {
            return;
        }
        
        isCandidate[curBits] = false;
            
        // 현재 비트값이 1인 경우, 다음 인덱스로 이동
        if (isIncludedInBits(curBits, curIdx)) {
            updateMinimal(curBits, curIdx+1);
        }
        else {
            // 현재 비트값을 바꾸지 않는 경우
            updateMinimal(curBits, curIdx+1);
            
            // 현재 비트값을 바꾸는 경우
            int nextBits = changeBit(curBits, curIdx);
            updateMinimal(nextBits, curIdx+1);
        }
    }
    
    public int changeBit(int bits, int idx) {
        return bits | (1 << idx);
    }
    
    public boolean isUnique(int bits, String[][] relation) {
        if (tupleCnt == 1) {
            return true;
        }
        
        for (int tuple1 = 1; tuple1 < tupleCnt; tuple1++) {
            for (int tuple2 = 0; tuple2 < tuple1; tuple2++) {
                boolean isSame = true;
                
                for (int attr = 0; attr < attrCnt; attr++) {
                    if (isIncludedInBits(bits, attr) && !relation[tuple1][attr].equals(relation[tuple2][attr])) {
                        isSame = false;
                        break;
                    }
                }
                
                if (isSame) {
                    return false;
                }
            }   
        }
        
        return true;
    }
    
    private boolean isIncludedInBits(int bits, int idx) {
        if ((bits & (1 << idx)) == 0) {
            return false;
        }
        
        return true;
    }
    
    private void printBits(int state) {
        System.out.println("====bits====");
        for (int idx = 0; idx < attrCnt; idx++) {
            if ((state & (1 << idx)) > 0) {
                System.out.print("1 ");
            }
            else {
                System.out.print("0 ");
            }
        }
        System.out.println("\n==========");
    }
}