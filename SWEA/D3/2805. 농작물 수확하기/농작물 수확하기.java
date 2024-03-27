/**
 * 1. 테스트 케이스 개수 입력 받기
 * 2. 농장의 크기 입력 받기
 * 3. 농장 크기만큼 배열 생성
 * 4. 농장의 농작물 가치 입력 받기
 * 5. 계산 범위가 1인 행부터 계산
 * 	- 파라미터 : 계산 범위
 * 	5-1. 기저 조건
 * 		- 계산 범위 = 농장 크기 (중간 행)
 * 		- 현재 행의 수익 계산 후 종료
 * 	5-2. 현재 계산 범위를 가질 수 있는 행 인덱스 찾기
 * 		1) 농장크기/2 - ((농장크기 - 계산범위)/2)
 * 		2) 농장크기/2 - ((농장크기 - 계산범위)/2)
 * 	5-3. 해당 행의 수익 계산
 * 		1) 계산 시작 열의 인덱스 구하기
 * 		2) 해당 열 인덱스부터 범위 안의 값들을 수익에 더하기
 * 	5-4. 재귀 호출
 * 		- 다음 계산 범위(+2) 호출
 * 	
 * 6. 수익 출력
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int farmSize;		// 농장 크기
	static int[][] cropsValue;	// 농작물의 가치
	static int farmProfit;		// 농장의 수익
	
	// 특정 행의 수익 계산
	public static void calculateRowProfit(int currentRow, int currentSize) {
		// 1) 계산 시작 열의 인덱스 구하기
		int startColIdx = (farmSize - currentSize) / 2;
		
		// 2) 해당 열 인덱스부터 범위 안의 값들을 수익에 더하기
		for (int count=0; count<currentSize; count++) {
			farmProfit += cropsValue[currentRow][startColIdx + count];
		}
		return;
	}
	
	public static void calculateProfit(int currentSize) {
		// 5-1. 기저 조건
		// 계산 범위 = 농장 크기 (중간 행인 경우)
		if (currentSize == farmSize) {
			// 현재 행의 수익 계산 후 종료
			calculateRowProfit(farmSize/2, currentSize);
			return;
		}
		
		// 5-2. 현재 계산 범위를 가질 수 있는 행 인덱스 찾기
		// 1) 농장크기/2 - ((농장크기 - 계산범위)/2)
		int rowIdx1 = farmSize/2 - (farmSize - currentSize)/2;
		// 2) 농장크기/2 + ((농장크기 - 계산범위)/2)
		int rowIdx2 = farmSize/2 + (farmSize - currentSize)/2;
		
		// 5-3. 해당 행의 수익 계산
		calculateRowProfit(rowIdx1, currentSize);
		calculateRowProfit(rowIdx2, currentSize);
		
		// 5-4. 재귀 호출
		// 다음 계산 범위(+2) 호출
		calculateProfit(currentSize + 2);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 테스트 케이스 개수 입력 받기
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// init
			sb.setLength(0);
			farmProfit = 0;
			
			// 2. 농장의 크기 입력 받기
			farmSize = Integer.parseInt(br.readLine().trim());
			
			// 3. 농장 크기만큼 배열 생성
			cropsValue = new int[farmSize][farmSize];
			
			// 4. 농장의 농작물 가치 입력 받기
			for (int row = 0; row < farmSize; row++) {
				String crops = br.readLine().trim();
				
				for (int col = 0; col < farmSize; col++) {
					cropsValue[row][col] = crops.charAt(col) - '0';
				}
			}
			
			// 5. 계산 범위가 1인 행부터 계산
			calculateProfit(1);
			
			// 6. 수익 출력
			sb.append("#").append(tc).append(" ").append(farmProfit);
			System.out.println(sb);
		}
	}
}
