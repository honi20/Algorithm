/**
 * 1. 정수가 입력된다.
 * 2. 1부터 입력된 정수까지 연산을 사용하는 횟수의 최솟값을 계산한다.
 * 3. 입력된 정수를 1로 만드는데 필요한 연산의 최소 횟수 출력
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int number;	// 정수
	static int[] operationCnt;	// 연산 최소 횟수
	
	public static void getMinOperationCnt() {
		for (int num = 1; num <= number; num++) {
			if (num == 1)
				operationCnt[num] = 0;
			else if (num == 2 || num == 3)
				operationCnt[num] = 1;
			// 3번 연산(1을 뺀다) 실행
			else
				operationCnt[num] = operationCnt[num-1] + 1;
			
			// 2로 나누어 지는 경우, 2번 연산(2로 나눈다) 실행
			if (num % 2 == 0)
				operationCnt[num] = Math.min(operationCnt[num], operationCnt[num/2] + 1);
			// 3으로 나누어 지는 경우, 1번 연산(3으로 나눈다) 실행
			if (num % 3 == 0)
				operationCnt[num] = Math.min(operationCnt[num], operationCnt[num/3] + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 정수가 입력된다.
		number = Integer.parseInt(br.readLine().trim());
		
		// 2. 1부터 입력된 정수까지 연산을 사용하는 횟수의 최솟값을 계산한다.
		operationCnt = new int[number + 1];
		Arrays.fill(operationCnt, Integer.MAX_VALUE);
		getMinOperationCnt();
		
		// 3. 입력된 정수를 1로 만드는데 필요한 연산의 최소 횟수 출력
		System.out.println(operationCnt[number]);
	}
}