import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int MOD = 1000;
	
	static int N;
    static int[][] arr;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
        long tmp = Long.parseLong(st.nextToken());

        int[][] matrix = new int[N][N];
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine().trim());
        	for (int j = 0; j < N; j++) {
        		matrix[i][j] = Integer.parseInt(st.nextToken()) % MOD;
        	}
        }

        for (int i = 0; i < N; i++) {
        	arr[i][i] = 1;
        }

        matrix = pow(tmp, matrix);

        for (int[] m : matrix) {
            for (int i : m)
                sb.append(i + " ");
            sb.append("\n");
        }
        
        System.out.println(sb);
	}
    
    private static int[][] pow(long n, int[][] matrix) {
        if (n == 0)
            return arr;
        if (n == 1)
            return matrix;

        int[][] nMatrix = pow(n / 2, matrix);
        nMatrix = multiple(nMatrix, nMatrix);

        return n % 2 == 0 ? nMatrix : multiple(nMatrix, matrix);
    }

    private static int[][] multiple(int[][] m1, int[][] m2) {
        int[][] matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++)
                    matrix[i][j] += (m1[i][k] * m2[k][j]) % MOD;
                matrix[i][j] %= MOD;
            }
        }

        return matrix;
    }
}