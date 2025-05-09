class Solution {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        
        answer[0] = gcd(n, m);
        answer[1] = (n * m) / answer[0];
        
        return answer;
    }
    
    private int gcd(int num1, int num2) {
        // num1이 값이 더 크도록
        if (num1 < num2) {
            int tmp = num2;
            num2 = num1;
            num1 = tmp;
        }
        
        while (num2 != 0) {
            int tmp = num1 % num2;
            num1 = num2;
            num2 = tmp;
        }
        
        return num1;
    }
}