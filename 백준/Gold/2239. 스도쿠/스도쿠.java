/**
 * 1. 스도쿠 퍼즐의 정보가 입력된다.
 * 	- 아직 숫자가 채워지지 않은 칸 = 0
 * 2. 행/열/3X3 각각에 대해 존재하는 수 비트와 존재하지 않는 수의 개수를 저장한다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int MAX_NUM = 9;
	static final int EXIST_NUMS_BITS = 0;
	static final int NOT_EXIST_NUM_CNT = 1;
	
	static int[][] sudoku;
	static boolean[][] vertical;
	static boolean[][] horizontal;
	static boolean[][] box3By3;
	static boolean flag = false;
	
	public static int getBoxIdx(int row, int col) {
		return (row / 3) * 3 + (col / 3);
	}
	
	public static void fillSudoku(int currentRow, int currentCol) {
		if (currentRow == MAX_NUM) {
			flag = true;
			for (int row = 0; row < MAX_NUM; row++) {
				for (int col = 0; col < MAX_NUM; col++) {
					System.out.print(sudoku[row][col]);
				}
				System.out.println();
			}
			return;
		}
		
		if (flag)
			return;
		
		int nextRow = currentRow + (currentCol + 1)/MAX_NUM;
		int nextCol = (currentCol + 1)%MAX_NUM;

		// 현재 칸에 숫자가 있는 경우, 패스 (다음 칸으로)
		if (sudoku[currentRow][currentCol] > 0) {
			fillSudoku(nextRow, nextCol);
		}
		// 현재 칸에 숫자가 없는 경우, 가능한 숫자들을 넣어보면서 백트레킹 진행
		else {
			for (int num = 1; num <= MAX_NUM; num++) {
				if (!vertical[currentRow][num] && !horizontal[currentCol][num] && !box3By3[getBoxIdx(currentRow, currentCol)][num]) {
					vertical[currentRow][num] = horizontal[currentCol][num] = box3By3[getBoxIdx(currentRow, currentCol)][num] = true;
					sudoku[currentRow][currentCol] = num;
					fillSudoku(nextRow, nextCol);
					sudoku[currentRow][currentCol] = 0;
					vertical[currentRow][num] = horizontal[currentCol][num] = box3By3[getBoxIdx(currentRow, currentCol)][num] = false;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 스도쿠 퍼즐의 정보가 입력된다.
		sudoku = new int[MAX_NUM][MAX_NUM];
		vertical = new boolean[MAX_NUM][MAX_NUM + 1];
		horizontal = new boolean[MAX_NUM][MAX_NUM + 1];
		box3By3 = new boolean[MAX_NUM][MAX_NUM + 1];
		
		for (int row = 0; row < MAX_NUM; row++) {
			int nums = Integer.parseInt(br.readLine().trim());
			
			for (int col = MAX_NUM-1; col >= 0; col--) {
				sudoku[row][col] = nums % 10;
				nums /= 10; 
				
				if (sudoku[row][col] > 0) {
					vertical[row][sudoku[row][col]] = true;
					horizontal[col][sudoku[row][col]] = true;
					box3By3[getBoxIdx(row, col)][sudoku[row][col]] = true;
				}
			}
		}

		// 2. 스도쿠의 값을 추가해서 해당 값이 가능하면 계속 가고 안되면 다시 돌아간다.
		fillSudoku(0, 0);
	}

}
