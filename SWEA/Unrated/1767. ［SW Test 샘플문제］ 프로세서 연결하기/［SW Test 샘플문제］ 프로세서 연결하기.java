import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static class Point {
		int row, col;

		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	// 배열 정보 값
	static final int EMPTY = 0;
	static final int CORE = 1;
	static final int WIRE = 2;

	static final int DELTA_CNT = 4;
	static final int[] DELTA_ROW = {-1,1,0,0};
	static final int[] DELTA_COL = {0,0,-1,1};

	static int arraySize;	// 배열 크기
	static int[][] array;	// 배열 정보
	static List<Point> coreList;	// 코어 좌표 (가장자리 코어 제외)
	
	static int maxCnt;		// 최대 연결 코어 개수
	static int minLength;	// 최소 전선 길이

	public static int setStatus(int row, int col, int dir, int value) {
		int nowRow = row;
		int nowCol = col;
		int length = 0;
		
		while (true) {
			nowRow += DELTA_ROW[dir];
			nowCol += DELTA_COL[dir];
			
			// 범위 벗어나면, 연결 가능
			if (nowRow < 0 || nowCol < 0 || nowRow >= arraySize || nowCol >= arraySize) {
				return length;
			}
			
			array[nowRow][nowCol] = value;
			length++;
		}
	}
	
	public static boolean isAvailable(int row, int col, int dir) {
		int nowRow = row;
		int nowCol = col;
		
		while (true) {
			nowRow += DELTA_ROW[dir];
			nowCol += DELTA_COL[dir];
			
			// 범위 벗어나면, 연결 가능
			if (nowRow < 0 || nowCol < 0 || nowRow >= arraySize || nowCol >= arraySize) {
				return true;
			}
			
			// 이동 칸이 빈 칸이 아닌 경우, 연결 불가능
			if (array[nowRow][nowCol] != EMPTY)
				return false;
		}
	}
	
	public static void connectCores(int coreIdx, int cnt, int length) {
		// 종료조건 : 모든 코어를 탐색한 경우
		if (coreIdx == coreList.size()) {
			// 결과 갱신 후 종료
			if (cnt > maxCnt) {
				maxCnt = cnt;
				minLength = length;
			}
			else if (cnt == maxCnt) {
				minLength = Math.min(minLength, length);
			}
			return;
		}
		
		// 가지치기 : 현재 코어부터 남은 코어를 모두 연결해도 최대 연결 개수보다 작으면, 종료
		if (maxCnt > cnt + (coreList.size() - coreIdx))
			return;
		
		// 갈 수 있는 4가지 방향과 연결 안함 처리 탐색
		Point nowCore = coreList.get(coreIdx);
		int currentRow = nowCore.row;
		int currentCol = nowCore.col;
		
		for (int dir = 0; dir < DELTA_CNT; dir++) {
			// 현재 방향으로 갈 수 있는 경우, 전선 연결 처리 -> 다음 코어 호출 -> 전선 연결 처리 취소
			if (isAvailable(currentRow, currentCol, dir)) {
				int plusL = setStatus(currentRow, currentCol, dir, WIRE);
				connectCores(coreIdx+1, cnt+1, length+plusL);
				setStatus(currentRow, currentCol, dir, EMPTY);
			}
		}
		
		// 전선 연결하지 않음
		connectCores(coreIdx+1, cnt, length);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		coreList = new ArrayList<>();
		
		// 1. 테스트 케이스 개수가 입력된다.
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			maxCnt = Integer.MIN_VALUE;
			minLength = Integer.MAX_VALUE;
			coreList.clear();
			
			// 2. 배열의 크기가 입력된다.
			arraySize = Integer.parseInt(br.readLine().trim());
			
			// 3. 배열 초기 상태가 입력된다.
			array = new int[arraySize][arraySize];
			for (int row = 0; row < arraySize; row++) {
				st = new StringTokenizer(br.readLine().trim());
				
				for (int col = 0; col < arraySize; col++) {
					array[row][col] = Integer.parseInt(st.nextToken());
					
					if (array[row][col] == CORE && row > 0 && row < arraySize-1 && col > 0 && col < arraySize-1) {
						coreList.add(new Point(row, col));
					}
				}
			}
			
			// 4. 첫 번째 코어부터 탐색을 시작한다.
			connectCores(0, 0, 0);
			
			sb.append(minLength).append("\n");
		}
		System.out.println(sb);
	}
}