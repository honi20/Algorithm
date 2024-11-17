import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int h, w;
    static int[] height;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
 
        height = new int[w];
        st = new StringTokenizer(br.readLine().trim());
        for(int idx = 0; idx < w; idx++) {
            height[idx] = Integer.parseInt(st.nextToken());
        }
 
        int result = 0;
        for(int idx = 1; idx < w - 1; idx++) {
            int left = 0;
            int right = 0;
 
            for(int j = 0; j < idx; j++) {
                left = Math.max(height[j], left);
            }
 
            for(int j = idx + 1; j < w; j++) {
                right = Math.max(height[j], right);
            }
 
            if (height[idx] < left && height[idx] < right)
            	result += Math.min(left, right) - height[idx];
        }
        
        System.out.println(result);
	}
}