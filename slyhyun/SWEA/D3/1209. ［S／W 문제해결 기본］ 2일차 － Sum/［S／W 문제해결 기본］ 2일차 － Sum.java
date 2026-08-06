import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int tc = 0; tc < 10; tc++) {
			int T = Integer.parseInt(br.readLine());
			
			int[][] arr = new int[100][100];
			
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max = 0;
			int dCount1 = 0;
			int dCount2 = 0;
			
			for (int i = 0; i < 100; i++) {
				int rCount = 0;
				int cCount = 0;
				
				for (int j = 0; j < 100; j++) {
					rCount += arr[i][j];
					cCount += arr[j][i];
				}
				
				dCount1 += arr[i][i];
				dCount2 += arr[i][99 - i];
				
				if (rCount > max) {
					max = rCount;
				}
				
				if (cCount > max) {
					max = cCount;
				}
			}
			
			if (dCount1 > max) {
				max = dCount1;
			}
			
			if (dCount2 > max) {
				max = dCount2;
			}
			
			sb.append("#").append(T).append(" ").append(max).append("\n");
		}
		
		System.out.println(sb);
	}
}
