/**
 * 1. 원본 암호문의 길이가 입력된다.
 * 2. 원본 암호문이 입력된다.
 * 3. 명령어의 개수가 입력된다.
 * 4. 명령어가 입력된다.
 *     - 전체 명령어를 I를 기준으로 한 하나의 명령들로 분리한다.
 * 5. 명령을 하나씩 실행한다.
 *     5-1. 명령을 공백을 기준으로 분리한다.
 *     - [0] : 삽입할 위치, [1] : 삽입할 숫자 개수, [2] ~ : 삽입할 숫자들
 *     5-2. 삽입할 숫자들을 리스트로 저장한다.
 *     5-3. 암호문 리스트에 삽입할 숫자 전체 삽입
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int TESTCASE = 10;
	static final int PRINT_CRYP_COUNT = 10;

	static int cryptogramSize; 		// 원본 암호문 길이
	static String originCryp; 		// 원본 암호문
	static List<String> cryptogram; // 암호문 저장

	static int commandCount; 		// 명령어의 개수
	static String[] commands; 		// 명령어
	static String[] oneCommand; 	// 하나의 명령
	static int insertIdx, insertNumCount; // 삽입할 위치, 삽입할 숫자 개수

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		cryptogram = new ArrayList<>();

		for (int tc = 1; tc <= TESTCASE; tc++) {
			// init
			cryptogram.clear();
			
			// 1. 원본 암호문의 길이가 입력된다.
			cryptogramSize = Integer.parseInt(br.readLine().trim());

			// 2. 원본 암호문이 입력된다.
			originCryp = br.readLine().trim();
			for (String cryp : originCryp.split(" ")) {
				cryptogram.add(cryp);
			}

			// 3. 명령어의 개수가 입력된다.
			commandCount = Integer.parseInt(br.readLine().trim());

			// 4. 명령어가 입력된다.
			// 전체 명령어를 I를 기준으로 한 하나의 명령들로 분리한다.
			commands = br.readLine().trim().split("I");

			// 5. 명령을 하나씩 실행한다.
			for (String command : commands) {
				if (command.equals(""))
					continue;

				// 5-1. 명령을 공백을 기준으로 분리한다.
				// [0] : 삽입할 위치, [1] : 삽입할 숫자 개수, [2] ~ : 삽입할 숫자들
				oneCommand = command.trim().split(" ");
				insertIdx = Integer.parseInt(oneCommand[0]);
				insertNumCount = Integer.parseInt(oneCommand[1]);

				// 5-2. 삽입할 숫자들을 리스트로 저장한다.
				List<String> insertNums = new ArrayList<>();
				for (int idx = 2; idx < oneCommand.length; idx++) {
					insertNums.add(oneCommand[idx]);
				}

				// 5-3. 암호문 리스트에 삽입할 숫자 전체 삽입
				cryptogram.addAll(insertIdx, insertNums);
			}

			// 출력
			sb.append("#").append(tc).append(" ");
			for (int idx = 0; idx < PRINT_CRYP_COUNT; idx++) {
				sb.append(cryptogram.get(idx)).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}