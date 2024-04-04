/**
 * 1. 격자 칸의 정보가 입력된다.
 * 2. 값이 1인 칸을 찾아 놓을 수 있는 색종이를 붙인다.
 * 	2-1. 종료조건1 : 1의 값을 갖는 칸이 더이상 없다면, 결과값 갱신 후 종료
 * 	2-2. 종료조건2 : 1의 칸을 찾았다면, 모든 색종이 종류를 탐색하며 붙일 수 있는지 탐색한다. & 종료
 * 		2-2-1. 해당 크기의 색종이가 없다면, 패스
 * 		2-2-2. 해당 크기의 색종이를 놓을 수 있는지 확인한다.
 * 			1) 현재 좌표를 색종이의 가장 왼쪽 상단의 좌표로 보며 색종이 범위에 속하는 칸들을 탐색한다.
 * 			2) 색종이가 격자의 범위를 벗어나면, 색종이 붙이기 불가능
 * 			3) 색종이 안에 0의 값을 갖는 칸이 존재하면, 색종이 붙이기 불가능
 * 		2-2-3. 2-2-3. 색종이를 붙일 수 있다면, 색종이에 속하는 부분을 0 처리 & 해당 색종이 개수 감소 & 남은 1값 칸의 개수 감소
 * 		2-2-4. 재귀를 통해 다음 놓을 수 있는 색종이를 찾는다.
 * 		2-2-5. 재귀 종료 후, 2-2-3에서 처리한 부분을 원래대로 돌려놓는다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int ARRAY_SIZE = 10;	// 격자 크기
	static final int PAPER_TYPE = 5;	// 색종이 종류 수
	
	static int[][] arr;		// 종이
	static int[] paperCnt;	// 각 색종이의 남은 개수
	static int cntOf1;		// 1의 값을 갖는 칸의 개수
	static int minResult;	// 최소 결과값
	
	// 총 5가지 각 색종이 개수를 5로 초기화
	public static void init() {
		paperCnt = new int[PAPER_TYPE];
		
		for (int idx = 0; idx < PAPER_TYPE; idx++)
			paperCnt[idx] = 5;
	}
	
	// 현재 칸이 색종이의 가장 왼쪽위 좌표일 때, 해당 크기만큼의 색종이 안에 포함되는 칸들의 모든 값이 1인지 여부 반환
	public static boolean isAllOne(int startRow, int startCol, int len) {
		// 1) 현재 좌표를 색종이의 가장 왼쪽 상단의 좌표로 보며 색종이 범위에 속하는 칸들을 탐색한다.
		for (int row = startRow; row <= startRow+len; row++) {
			for (int col = startCol; col <= startCol+len; col++) {
				// 2) 색종이가 격자의 범위를 벗어나면, 색종이 붙이기 불가능
				if (row >= ARRAY_SIZE || col >= ARRAY_SIZE)
					return false;
				
				// 3) 색종이 안에 0의 값을 갖는 칸이 존재하면, 색종이 붙이기 불가능
				if (arr[row][col] == 0)
					return false;
			}
		}
		return true;
	}
	
	// 색종이 범위의 격자 값 업데이트
	public static void updateArray(int startRow, int startCol, int len, int value) {
		for (int row = startRow; row <= startRow+len; row++) {
			for (int col = startCol; col <= startCol+len; col++) {
				arr[row][col] = value;
			}
		}
	}
	
	public static void attachPaper(int cnt) {
		// 2-1. 종료조건1 : 1의 값을 갖는 칸이 더이상 없다면, 결과값 갱신 후 종료
		if (cntOf1 == 0) {
			minResult = Math.min(minResult, cnt);
			return;
		}
		
		for (int row = 0; row < ARRAY_SIZE; row++) {
			for (int col = 0; col < ARRAY_SIZE; col++) {
				
				// 2-2. 종료조건2 : 1의 칸을 찾았다면, 모든 색종이 종류를 탐색하며 붙일 수 있는지 탐색한다. & 종료
				if (arr[row][col] == 1) {
					for (int idx = 0; idx < PAPER_TYPE; idx++) {
						// 2-2-1. 해당 크기의 색종이가 없다면, 패스
						if (paperCnt[idx] == 0)
							continue;
						
						// 2-2-2. 해당 크기의 색종이를 놓을 수 있는지 확인한다.
						if (isAllOne(row, col, idx)) {
							// 2-2-3. 색종이를 붙일 수 있다면, 색종이에 속하는 부분을 0 처리 & 해당 색종이 개수 감소 & 남은 1값 칸의 개수 감소
							updateArray(row, col, idx, 0);
							--paperCnt[idx];
							cntOf1 -= Math.pow(idx+1, 2);
							
							// 2-2-4. 재귀를 통해 다음 놓을 수 있는 색종이를 찾는다.
							attachPaper(cnt+1);
							
							// 2-2-5. 재귀 종료 후, 2-2-3에서 처리한 부분을 원래대로 돌려놓는다.
							updateArray(row, col, idx, 1);
							++paperCnt[idx];
							cntOf1 += Math.pow(idx+1, 2);
						}
					}
					
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		minResult = Integer.MAX_VALUE;
		
		// 1. 격자 칸의 정보가 입력된다.
		arr = new int[ARRAY_SIZE][ARRAY_SIZE];
		cntOf1 = 0;
		for (int row = 0; row < ARRAY_SIZE; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < ARRAY_SIZE; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
				
				if (arr[row][col] == 1)
					cntOf1++;
			}
		}
		
		// 2. 값이 1인 칸을 찾아 놓을 수 있는 색종이를 붙인다.
		init();
		attachPaper(0);
		
		System.out.println(minResult == Integer.MAX_VALUE ? -1 : minResult);
	}
}