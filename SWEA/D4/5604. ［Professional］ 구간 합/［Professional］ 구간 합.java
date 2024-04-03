/**
 * 1. 1~9까지의 누적합을 구해놓는다.
 * 2. 테스트케이스 개수가 입력된다.
 * 3. 구간이 입력된다.
 * 4. 각 입력값까지의 누적합을 구한다.
 * 	4-1. 10보다 작은 수라면, 누적합 바로 반환
 * 	4-2. 숫자의 각 자릿수를 배열에 저장하면서 숫자의 길이를 구한다.
 * 	4-3. 현재 숫자를 기준으로 가장 가까운 99...9 형태의 숫자를 구하고, 해당 숫자까지의 누적합을 결과에 더한다.
 * 		- 99...9까지의 누적합 = k * (10^(k-1)) * sum[9] 	(k = 숫자 길이)
 * 		4-3-1. 현재 숫자 == (10^길이-1)이라면, 현재 숫자의 누적합을 결과에 더한다.
 * 		4-3-2. 아니라면, (현재 길이 - 1)의 길이만큼의 99..9의 누적합을 결과에 더한다.
 * 			- 예) 현재 숫자 = 1234 => 999까지의 누적합 저장
 * 	
 * 	4-4. 각 자릿수를 탐색하며 "남은 숫자들의 합"을 구한다.
 * 		- 예) 8732 = 999까지의 누적합 + (1000~8732)의 합
 * 		4-4-1. 1의 자리인 경우, 해당 숫자의 누적합 더함
 * 		4-4-2. 1의 자리가 아닌 경우
 * 			1) 현재 자리의 숫자가 0보다 큰 경우, 현재 자릿수에 나오는 수들의 합을 더한다.
 * 			2) 현재 자리보다 작은 자리에 나오는 수들의 합을 더한다.
 * 				(이때, 최대 자리에서는 10..0부터 시작하기 때문에 (숫자-1)이 곱해진다.)
 * 			3) 현재 자리보다 작은 자리를 탐색하며, 나머지 수의 횟수만큼 현재 자릿수를 더한다.
 * 
 * [진행 예시]
 * 예) 8732 -> 999까지의 누적합 + 1000~8732의 합
 * 		1000~8732 -> 1000~7999 + 8000~8732 -> 1000~7999 + (8000~8699 + 8700~8732)
 * 				  -> 1000~7999 + (8000~8699 + (8700~8729 + 8730~8732))
 * 		
 * 	- 1의 자리 2 : 1+2 => 2까지의 누적합
 * 	- 10의 자리 3 : (8700~8729에서 10의 자리의 합) + (8700~8729에서 1의 자리의 합) + (8730~8732에서 10의 자리 합)
 * 					= sum[2]*10 + sum[9]*1*(10^0)*2 + 3*(2+1)
 * 	- 100의 자리 7 : (8000~8699에서 100의 자리 합) + (8000~8699에서 10, 1의 자리 합) + (8700~8729에서 100의 자리 합) + (8730~8732에서 100의 자리 합)
 * 					= sum[6]*100 + sum[9]*2*(10^1)*7 + 7*3*10 + 7*(2+1)
 * 	- 1000의 자리 8 : (1000~7999에서 1000의 자리 합) + (1000~7999에서 100,10,1의 자리 합) + (8000~8699에서 1000의 자리 합) + (8700~8729에서 1000의 자리 합) + (8730~8732에서 1000의 자리 합)
 * 					= sum[7]*1000 + sum[9]*3*(10^2)*(8-1) + 8*7*100 + 8*3*10 + 8*(2+1)
 * 					(0000이 아닌 1000부터 시작하기 때문에 8이 아닌 (8-1)을 곱한다.)
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int testCase;
	static long startNum, endNum;
	static long[] sum;

	// 1~9까지의 누적합 저장
	public static void init() {
		sum = new long[10];
		for (int idx = 1; idx < 10; idx++) {
			sum[idx] = sum[idx - 1] + idx;
		}
	}

	public static long getSum(long number) {
		// 4-1. 10보다 작은 수라면, 누적합 바로 반환
		if (number < 10)
			return sum[(int) number];

		// 4-2. 숫자의 각 자릿수를 배열에 저장하면서 숫자의 길이를 구한다.
		int[] num = new int[17];	// 숫자 자릿수 별 저장
		int len = 0;	// 숫자 길이
		long result;

		while (number > 0) {
			num[len++] = (int) (number % 10);
			number /= 10;
		}

		// 4-3. 현재 숫자를 기준으로 가장 가까운 99...9 형태의 숫자를 구하고, 해당 숫자까지의 누적합을 결과에 더한다.
		// 99...9까지의 누적합 = k * (10^(k-1)) * sum[9] 	(k = 숫자 길이)
		
		// 4-3-1. 현재 숫자 == (10^길이-1)이라면, 현재 숫자의 누적합을 결과에 더한다.
		if ((long) Math.pow(10, len) - 1 == number)
			return len * (long) Math.pow(10, len - 1) * sum[9];
		
		// 4-3-2. 아니라면, (현재 길이 - 1)의 길이만큼의 99..9의 누적합을 결과에 더한다.
		else {
			result = (len - 1) * (long) Math.pow(10, len - 2) * sum[9];
		}

		// 4-4. 각 자릿수를 탐색하며 "남은 숫자들의 합"을 구한다.
		for (int i = 0; i < len; i++) {
			int nowNum = num[i];

			// 4-4-1. 1의 자리인 경우, 해당 숫자의 누적합 더함
			if (i == 0) {
				result += sum[nowNum];
			}
			// 4-4-2. 1의 자리가 아닌 경우
			else {
				// 1) 현재 자리의 숫자가 0보다 큰 경우, 현재 자릿수에 나오는 수들의 합을 더한다.
				if (nowNum > 0)
					result += sum[(nowNum - 1)] * (long)Math.pow(10, i);

				// 2) 현재 자리보다 작은 자리에 나오는 수들의 합을 더한다.
				// (이때, 최대 자리에서는 10..0부터 시작하기 때문에 (숫자-1)이 곱해진다.)
				if (i == len - 1 && nowNum > 0) {
						result += sum[9] * i * (long)Math.pow(10, i - 1) * (nowNum - 1);
				}
				else
					result += sum[9] * i * (long)Math.pow(10, i - 1) * nowNum;

				// 3) 현재 자리보다 작은 자리를 탐색하며, 나머지 수의 횟수만큼 현재 자릿수를 더한다.
				for (int j = 0; j < i; j++) {
					if (j == 0)
						result += nowNum * (num[j] + 1);
					else
						result += nowNum * num[j] * (long)Math.pow(10, j);
				}
			}
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 1~9까지의 누적합을 구해놓는다.
		init();

		// 2. 테스트케이스 개수가 입력된다.
		testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= testCase; tc++) {
			// 3. 구간이 입력된다.
			st = new StringTokenizer(br.readLine().trim());
			startNum = Long.parseLong(st.nextToken());
			endNum = Long.parseLong(st.nextToken());

			// 4. 각 입력값까지의 누적합을 구한다.
			long totalSum = getSum(endNum);
			long minusSum = startNum == 0 ? 0 : getSum(startNum - 1);
			
			sb.append("#").append(tc).append(" ").append(totalSum - minusSum).append("\n");
		}
		System.out.println(sb);
	}
}