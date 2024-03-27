/**
 * 1. 테스트케이스 개수를 입력한다.
 * 2. 배열의 크기와 파리채의 크기를 입력한다.
 * 	2-1. 누적합 배열의 크기 지정
 * 3. 배열의 값을 입력한다.
 * 	3-1. 누적합 값 저장
 * 	- [row][col]의 누적합 = 현재 파리 개수 + [row-1][col]의 누적합 + [row][col-1]의 누적합 - [row-1][col-1]의 누적합
 * 4. 각 칸 별로 패리채 영역의 파리 개수를 구한다
 * 	= [row][col]의 누적합 - [row-flapperSize][col]의 누적합 - [row][col-flapperSize]의 누적합 + [row-flapperSize][col-flapperSize]의 누적합
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int arraySize, flapperSize;		// 배열 크기, 파리채 크기
	static int flyCount;		// 각 칸 별 파리 개수
	static int[][] sumArray;	// 누적합
	static int maxFlyCount, currentFlyCount;	// 파리채로 잡을 수 있는 최대 파리 개수(결과), 현재 파리채로 잡을 수 있는 파리 개수
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트케이스 개수를 입력한다.
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 2. 배열의 크기와 파리채의 크기를 입력한다.
			st = new StringTokenizer(br.readLine().trim());
			arraySize = Integer.parseInt(st.nextToken());
			flapperSize = Integer.parseInt(st.nextToken());
			
			
			// 2-1. 누적합 배열의 크기 지정
			sumArray = new int[arraySize+1][arraySize+1];
			maxFlyCount = 0;	// result init
			
			// 3. 배열의 값을 입력한다.
			for (int row = 1; row <= arraySize; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 1; col <= arraySize; col++) {
					flyCount = Integer.parseInt(st.nextToken());
					
					// 3-1. 누적합 값 저장
					// [row][col]의 누적합 = 현재 파리 개수 + [row-1][col]의 누적합 + [row][col-1]의 누적합 - [row-1][col-1]의 누적합
					sumArray[row][col] = flyCount + sumArray[row-1][col] + sumArray[row][col-1] - sumArray[row-1][col-1];
				}
			}
			
			// 4. 각 칸 별로 패리채 영역의 파리 개수를 구한다
			for (int row = flapperSize; row <= arraySize; row++) {
				for (int col = flapperSize; col <= arraySize; col++) {
					// 파리채의 파리 개수 = [row][col] - [row-flapperSize][col] - [row][col-flapperSize] + [row-flapperSize][col-flapperSize]
					currentFlyCount = sumArray[row][col] - sumArray[row-flapperSize][col] - sumArray[row][col-flapperSize] + sumArray[row-flapperSize][col-flapperSize];
					
					// 결괏값 갱신
					maxFlyCount = Math.max(maxFlyCount, currentFlyCount);
				}
			}
			
			// 결과 출력
			sb.append("#").append(tc).append(" ").append(maxFlyCount).append("\n");
		}
		
		System.out.println(sb);
	}
}
