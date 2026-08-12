import java.util.*;
import java.io.*;

public class Solution {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int[][] arr = new int[4][4];
			Set<String> result = new HashSet<>();
			
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
						
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					dfs(i, j, String.valueOf(arr[i][j]), arr, result);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result.size()).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void dfs(int r, int c, String s, int[][] arr, Set<String> set) {
		if (s.length() == 7) {
	        set.add(s);
	        return;
	    }
	    
	    for (int i = 0; i < 4; i++) {
	        int nr = r + dr[i];
	        int nc = c + dc[i];
	        
	        if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4) {
	            dfs(nr, nc, s + String.valueOf(arr[nr][nc]), arr, set);
	        }
	    }
	}
}
