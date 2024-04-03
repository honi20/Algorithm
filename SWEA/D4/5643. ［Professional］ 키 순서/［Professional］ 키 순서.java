/**
 * 1. 테스트케이스 개수가 입력된다.
 * 2. 학생들의 수가 입력된다.
 * 3. 두 학생의 키 비교 횟수가 입력된다.
 * 4. 두 학생의 키를 비교한 결과가 입력된다. input1 학생 키 < input2 학생 키
 * 	- students 배열에 존재한다는 의미로 1을 저장한다.
 * 5. 전체 학생에 대하여 학생 A에서 학생 B로 갈 수 있는지 여부를 저장한다.
 * 	5-1. 학생1과 경유 학생이 같다면 패스
 * 	5-2. 학생1과 학생2의 관계가 학생1 < 학생2로 이미 정의되어 있다면, 패스
 * 	5-3. 학생1과 경유 학생, 경유 학생과 학생2의 관계가 정의되어 있다면, 학생1과 학생2의 관계를 업데이트
 * 		- (학생1의 키 < 경유 학생의 키) && (경유 학생의 키 < 학생2의 키) => (학생1의 키 < 학생2의 키)
 * 6. 각 학생에 대해 (자신 1명 + 자신보다 작은 학생 수 + 자신보다 큰 학생 수 == 전체 학생 수)일 때, 해당 학생은 정확한 순서를 알 수 있다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int testCase;	// 테스트케이스 개수
	static int studentCnt;	// 학생 수
	static int compareCnt;	// 비교 횟수
	static int[][] students;	// [i][j] : 학생 i에서 학생 j로의 경로 존재 = (학생 i의 키 < 학생 j의 키)
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트케이스 개수가 입력된다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			
			// 2. 학생들의 수가 입력된다.
			studentCnt = Integer.parseInt(br.readLine().trim());
			
			// 3. 두 학생의 키 비교 횟수가 입력된다.
			compareCnt = Integer.parseInt(br.readLine().trim());
			
			// 4. 두 학생의 키를 비교한 결과가 입력된다. input1 학생 키 < input2 학생 키
			students = new int[studentCnt+1][studentCnt+1];
			for (int cnt = 0; cnt < compareCnt; cnt++) {
				st = new StringTokenizer(br.readLine().trim());
				int smaller = Integer.parseInt(st.nextToken());
				int taller = Integer.parseInt(st.nextToken());
				
				students[smaller][taller] = 1;
			}
			
			// 5. 전체 학생에 대하여 학생 A에서 학생 B로 갈 수 있는지 여부를 저장한다.
			for (int mid = 1; mid <= studentCnt; mid++) {
				for (int student1 = 1; student1 <= studentCnt; student1++) {
					// 5-1. 학생1과 경유 학생이 같다면 패스
					if (mid == student1)
						continue;
					
					for (int student2 = 1; student2 <= studentCnt; student2++) {
						// 5-2. 학생1과 학생2의 관계가 학생1 < 학생2로 이미 정의되어 있다면, 패스
						if (students[student1][student2] == 1)
							continue;
						
						// 5-3. 학생1과 경유 학생, 경유 학생과 학생2의 관계가 정의되어 있다면, 학생1과 학생2의 관계를 업데이트
						// (학생1의 키 < 경유 학생의 키) && (경유 학생의 키 < 학생2의 키) => (학생1의 키 < 학생2의 키)
						else if (students[student1][mid] == 1 && students[mid][student2] == 1)
							students[student1][student2] = 1;
					}
				}
			}
			
			// 6. 각 학생에 대해 (자신 1명 + 자신보다 작은 학생 수 + 자신보다 큰 학생 수 == 전체 학생 수)일 때, 해당 학생은 정확한 순서를 알 수 있다.
			int result = 0;
			for (int idx = 1; idx <= studentCnt; idx++) {
				int tallerCnt = 0, smallerCnt = 0;	// 자신보다 큰/작은 학생의 수
				
				for (int other = 1; other <= studentCnt; other++) {
					// 비교 학생 번호가 현재 학생 번호인 경우
					if (idx == other)
						continue;
					
					// 현재 학생의 키가 비교 학생의 키보다 작은 경우
					if (students[idx][other] == 1)
						tallerCnt++;
					
					// 비교 학생의 키가 현재 학생의 키보다 작은 경우
					if (students[other][idx] == 1)
						smallerCnt++;
				}
				
				if (tallerCnt + smallerCnt == studentCnt - 1)
					result++;
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}
