/**
 * 1. 테스트케이스 개수를 입력받는다.
 * 2. 팩토리얼 배열을 구한다.
 * 	- k! = (k-1)! * k
 * 3. N, R을 입력받는다.
 * 4. 페르마 소정리를 통해 combination 값을 출력한다.
 * 	- nCr (mod p) ≡ n!(r!(n-r)!)^(MOD-2)
 * 		페르마의 소정리 : a^(p-2) ≡ 1/a (mod p)
 * 		-> 1/(r!(n-r)!) (mod p) ≡ ((r!(n-r)!) ^ (MOD-2))
 * 		
 * 	[제곱 연산]
 * 		1) k가 짝수라면, x^k = x^(k/2) * x^(k/2)
 * 		2) k가 홀수라면, x^k = x * x^(k/2) * x^(k/2);
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int MOD = 1234567891;
	static final int MAX = 1000000;
	
	static int testCase;	// 테스트케이스 개수
	static int N, R;		// 조합 nCr
	static long[] factorial;// 팩토리얼 저장
	
	public static long power(long base, long exp) {
		// 지수가 1이라면, 밑의 수 값 반환
		if (exp == 1)
			return base;
		
		// 제곱 연산
		// 1) k가 짝수라면, x^k = x^(k/2) * x^(k/2)
		// 2) k가 홀수라면, x^k = x * x^(k/2) * x^(k/2);
		long result = (exp % 2 == 0) ? 1 : base;
		long midResult = power(base, exp/2);
		
		return (result * midResult % MOD) * midResult % MOD;
	}
	
	public static long combination(int N, int R) {
		long numerator = factorial[N];		// 조합의 분자 값(= N!)
		long denominator = (factorial[N-R] * factorial[R]) % MOD;	// 조합의 분모 값(= (N-R)!R!)
		
		// 페르마 소정리에 의해 조합 nCr = n! * ((r!(n-r)!)^(MOD-2))
		return (long) ((numerator * power(denominator, MOD-2)) % MOD);
	}
	
	public static void init() {
		factorial = new long[MAX + 1];
		factorial[1] = 1;		// 1! = 1
		
		// k! = (k-1)! * k
		for (int idx = 2; idx <= MAX; idx++) {
			factorial[idx] = (factorial[idx-1] * idx) % MOD;
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 테스트케이스 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		// 2. 팩토리얼 배열을 구한다.
		init();
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 3. N, R을 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			// 4. 페르마 소정리를 통해 combination 값을 출력한다.
			sb.append("#").append(tc).append(" ");
			sb.append(combination(N, R)).append("\n");
		}
		System.out.println(sb);
	}
}