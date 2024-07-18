/**
* 1. 동굴의 가로, 세로 크기가 입력된다.
* 2. 동굴 정보가 입력된다.
*     - '.'은 빈 칸, 'x'는 미네랄
* 3. 막대를 던진 횟수가 입력된다.
* 4. 막대를 던진 높이가 입력된다.
* 5. 현재 생성되는 클러스터를 확인한다.
* 6. 두 사람이 번갈아가며 막대를 모두 던진 후, 미네랄의 모양을 구한다.
*     6-1. 던지는 순서에 따라 던지는 방향이 결정된다.
*         - 첫 번째 사람은 왼->오 , 두 번째 사람은 오 -> 왼
*     6-2. 해당 높이에서 만나는 첫 번째 미네랄을 제거한다.
*     6-3. 새로 만들어지는 클러스터가 있는지 확인한다.
*         1) 공중에 떠 있는 클러스터 (새로 생성된 클러스터)를 찾는다.
*         2) 해당 클러스터가 내려갈 수 있는 바닥의 높이를 구한다.
*         3) 생성된 클러스터를 아래로 내린다.
*/
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int EMPTY = '.';
	static final int MINERAL = 'x';

	static final int[] DELTA_ROW = { -1, 1, 0, 0 };
	static final int[] DELTA_COL = { 0, 0, -1, 1 };

	static int rowSize, colSize;
	static int[][] cave;
	static int throwingCnt;
	static int[] height;
	static int startCol, direction;
	static int[][] cluster;
	static int clusterCnt;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 동굴의 가로, 세로 크기가 입력된다.
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		// 2. 동굴 정보가 입력된다.
		cave = new int[rowSize + 1][colSize + 1];
		cluster = new int[rowSize + 1][colSize + 1];
		for (int row = 1; row <= rowSize; row++) {
			String str = br.readLine().trim();

			for (int col = 1; col <= colSize; col++) {
				cave[row][col] = str.charAt(col - 1);
			}
		}

		// 3. 막대를 던진 횟수가 입력된다.
		throwingCnt = Integer.parseInt(br.readLine().trim());

		// 4. 막대를 던진 높이가 입력된다.
		height = new int[throwingCnt];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < throwingCnt; idx++) {
			int val = Integer.parseInt(st.nextToken());
			height[idx] = rowSize - val + 1;
		}

		// 6. 두 사람이 번갈아가며 막대를 모두 던진 후, 미네랄의 모양을 구한다.
		for (int idx = 0; idx < throwingCnt; idx++) {

			// 현재 생성되는 클러스터를 확인한다.
			clusterCnt = getCluster();

			// 6-1. 던지는 순서에 따라 던지는 방향이 결정된다.
			// 첫 번째 사람은 왼->오 , 두 번째 사람은 오 -> 왼
			if (idx % 2 == 0) {
				startCol = 1;
				direction = 1;
			} else {
				startCol = colSize;
				direction = -1;
			}

			// 6-2. 해당 높이에서 만나는 첫 번째 미네랄을 제거한다.
			int curRow = height[idx];
			int curCol = startCol;
			while (true) {
				// 제거할 미네랄 없음
				if (curCol <= 0 || curCol > colSize)
					break;

				// 만나는 첫 번째 미네랄
				if (cave[curRow][curCol] == MINERAL)
					break;

				curCol += direction;
			}

			// 제거할 미네랄이 없으면, 바로 다음 막대로 이동
			if (curCol <= 0 || curCol > colSize)
				continue;

			// 미네랄 제거
			cave[curRow][curCol] = EMPTY;
			int newClusterCnt = getCluster();

				clusterCnt = newClusterCnt;

				// 1) 공중에 떠 있는 클러스터 (새로 생성된 클러스터)를 찾는다.
				int newClusterIdx = findNewCluster();

				// 만약 공중에 떠있는 클러스터가 없는 경우, 패스
				if (newClusterIdx == 0)
					continue;

				// 2) 해당 클러스터가 내려갈 수 있는 바닥의 높이를 구한다.
				int dropHeight = getDropHeight(newClusterIdx);

				// 3) 생성된 클러스터를 아래로 내린다.
				dropDownCluster(newClusterIdx, dropHeight);
//			
//			System.out.println("=====" + idx + "=======");
//			for (int row = 1; row <= rowSize; row++) {
//				for (int col = 1; col <= colSize; col++) {
//					System.out.print((char) cave[row][col]);
//				}
//				System.out.println();
//			}
//			System.out.println();
		}

		printCave();
		System.out.println(sb);
	}

	public static int getCluster() {

		// init
		int clusterIdx = 0;
		for (int row = 0; row <= rowSize; row++) {
			Arrays.fill(cluster[row], 0);
		}

		for (int row = 1; row <= rowSize; row++) {
			for (int col = 1; col <= colSize; col++) {
				if (cave[row][col] == MINERAL && cluster[row][col] == 0) {
					cluster[row][col] = ++clusterIdx;
					dfs(row, col, clusterIdx);
				}
			}
		}
		return clusterIdx;
	}

	public static void dfs(int row, int col, int groupIdx) {

		for (int dir = 0; dir < 4; dir++) {
			int nextRow = row + DELTA_ROW[dir];
			int nextCol = col + DELTA_COL[dir];

			if (nextRow <= 0 || nextCol <= 0 || nextRow > rowSize || nextCol > colSize)
				continue;

			if (cave[nextRow][nextCol] == EMPTY)
				continue;

			if (cluster[nextRow][nextCol] != 0)
				continue;

			cluster[nextRow][nextCol] = groupIdx;
			dfs(nextRow, nextCol, groupIdx);
		}
	}

	public static int findNewCluster() {

		boolean[] isNotFly = new boolean[clusterCnt + 1];

		for (int row = 1; row <= rowSize; row++) {
			for (int col = 1; col <= colSize; col++) {
				int idx = cluster[row][col];

				// 미네랄이 아닌 경우
				if (idx == 0)
					continue;

				// 이미 바닥과 연결되어 있는 클러스터인 경우
				if (isNotFly[idx])
					continue;

				// 맨 바닥에 존재 => true
				if (row == rowSize) {
					isNotFly[idx] = true;
				}

				// 바로 아래에 다른 클러스터의 미네랄 존재 => true
				else if (cluster[row + 1][col] != 0 && cluster[row + 1][col] != idx) {
					isNotFly[idx] = true;
				}
			}
		}

		for (int idx = 1; idx <= clusterCnt; idx++) {
			if (!isNotFly[idx]) {
				return idx;
			}
		}

		return 0;
	}

	public static int getDropHeight(int clusterIdx) {

		int dropHeight = Integer.MAX_VALUE;

		for (int row = 1; row <= rowSize; row++) {
			for (int col = 1; col <= colSize; col++) {

				// 현재 미네랄이 공중에 떠있는 미네랄이 아닌 경우, 패스
				if (cluster[row][col] != clusterIdx)
					continue;

				// 바로 아래에 같은 클러스터가 존재하는 경우, 패스
				if (cluster[row + 1][col] == clusterIdx)
					continue;

				// 최대로 내려갈 수 있는 빈칸 높이를 구한다.
				int val = 0;

				while (true) {
					int newRow = row + val;

					// 바닥에 존재 => 해당 높이로
					if (newRow == rowSize) {
						break;
					}

					// 자신이 속한 클러스터의 미네랄 만남
					if (val > 0 && cluster[newRow][col] == clusterIdx) {
						val = Integer.MAX_VALUE;
						break;
					}

					// 다른 클러스터의 미네랄 만남
					if (cluster[newRow+1][col] != 0 && cluster[newRow+1][col] != clusterIdx) {
						break;
					}
					
					++val;
				}

				dropHeight = Math.min(dropHeight, val);
			}
		}

		return (dropHeight == Integer.MAX_VALUE ? 0 : dropHeight);
	}

	public static void dropDownCluster(int newClusterIdx, int dropHeight) {
		for (int row = rowSize; row > 0; row--) {
			for (int col = 1; col <= colSize; col++) {
				if (cluster[row][col] == newClusterIdx) {
					cluster[row + dropHeight][col] = newClusterIdx + 1;
					cave[row + dropHeight][col] = MINERAL;
					cluster[row][col] = 0;
					cave[row][col] = EMPTY;
				}
			}
		}
	}

	public static void printCave() {
		for (int row = 1; row <= rowSize; row++) {
			for (int col = 1; col <= colSize; col++) {
				sb.append((char) cave[row][col]);
			}
			sb.append("\n");
		}
	}
}