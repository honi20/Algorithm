/**
 * 1. 테스트 케이스 개수가 입력된다.
 * 2. 규영이가 내는 9개의 카드가 순서대로 입력된다.
 * 3. 인영이가 가지고 있는 카드 조합을 구한다.
 * 	- 1~18번의 카드 중 9개씩 나눔 -> 규영이가 가지고 있는 카드 제외한 카드 = 인영 카드
 * 4. 인영이 카드 조합의 순열을 구한다.
 * 	4-1. 종료 조건. 모든 카드를 둘러본 경우
 * 	4-2. 전처리 로직 : 선택하지 않은 카드 중 하나 선택
 * 	4-3. 재귀 호출
 * 	4-4. 후처리 로직 : 선택 취소
 */
import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int TOTAL_CARD_COUNT = 18;		// 총 카드 개수
	static final int ONE_PLAYER_CARD_COUNT = 9;	// 한 플레이어가 가지는 카드 개수
	
	static List<Integer> basePlayer;	// 규영이 카드 (순서 고정)
	static List<Integer> player;		// 인영이 카드 (9!가지의 순서 존재)
	static int[] selectList;			// 순열 선택 카드 리스트
	static boolean[] isSelected;		// 카드 선택 여부 저장
	static int winCount, loseCount;		// 규영이가 이기는 경우, 지는 경우
	
	public static void calculateWinOrLose() {
		int basePlayerScore = 0, playerScore = 0;
		
		for (int idx = 0; idx < ONE_PLAYER_CARD_COUNT; idx++) {
			// 카드의 수가 더 큰 플레이어가 두 카드의 합 만큼의 점수를 얻는다.
			if (basePlayer.get(idx) > selectList[idx])
				basePlayerScore += (basePlayer.get(idx) + selectList[idx]);
			else if (basePlayer.get(idx) < selectList[idx])
				playerScore += (basePlayer.get(idx) + selectList[idx]);
		}
		
		if (basePlayerScore > playerScore)
			winCount++;
		else if (basePlayerScore < playerScore)
			loseCount++;
	}
	
	public static void permutation(int selectIdx) {
		// 4-1. 종료 조건. 모든 카드를 둘러본 경우
		if (selectIdx == ONE_PLAYER_CARD_COUNT) {
			// 현재 두 플레이어의 카드 순서를 기준으로 규영이의 승패 여부 계산
			calculateWinOrLose();
			return;
		}
		
		// 4-2. 전처리 로직 : 선택하지 않은 카드 중 하나 선택
		for (int idx = 0; idx < ONE_PLAYER_CARD_COUNT; idx++) {
			// 이미 선택한 카드라면 패스
			if (isSelected[idx])
				continue;
			
			isSelected[idx] = true;
			selectList[selectIdx] = player.get(idx);
			
			// 4-3. 재귀 호출
			permutation(selectIdx + 1);
			
			// 4-4. 후처리 로직 : 선택 취소
			isSelected[idx] = false;
			selectList[selectIdx] = 0;
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 테스트 케이스 개수가 입력된다.
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// init
			basePlayer = new ArrayList<>();
			player = new ArrayList<>();
			selectList = new int[ONE_PLAYER_CARD_COUNT];
			isSelected = new boolean[ONE_PLAYER_CARD_COUNT];
			winCount = 0; 
			loseCount = 0;
			sb.append("#").append(tc).append(" ");
			
			// 2. 규영이가 내는 9개의 카드가 순서대로 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 1; idx <= ONE_PLAYER_CARD_COUNT; idx++) {
				basePlayer.add(Integer.parseInt(st.nextToken()));
			}
			
			// 3. 인영이가 가지고 있는 카드 조합을 구한다.
			for (int idx = 1; idx <= TOTAL_CARD_COUNT; idx++) {
				// 1~18번의 카드 중 9개씩 나눔 -> 규영이가 가지고 있는 카드 제외한 카드 = 인영 카드
				if (basePlayer.contains(idx))
					continue;
				
				player.add(idx);
			}
			
			// 4. 인영이 카드 조합의 순열을 구한다.
			permutation(0);

			sb.append(winCount).append(" ").append(loseCount).append("\n");
		}
		
		System.out.println(sb);
	}
}