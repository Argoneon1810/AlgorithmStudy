import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int x_diff = Math.abs(x2 - x1);
			int y_diff = Math.abs(y2 - y1);
			
			int count = 0;
			boolean turnX;
			
			// 만약 x좌표가 더 멀리 떨어져있다면 x방향으로 먼저 이동
			if (x_diff > y_diff) {
				turnX = true;
			}
			else {
				turnX = false;
			}
			
			// x와 y가 둘 다 같아질 때 반복문 탈출
			while (x_diff > 0 || y_diff > 0) {
				// x가 이동할 차례
				if (turnX) {
					// x까지와의 거리가 남아있다면 거리 감소
				    if (x_diff > 0) x_diff--;
				    // x차례인데 동일한 좌표라면 어디로든 이동해서 거리 증가
				    else x_diff++;
				    // y에게 턴 반납
				    turnX = false;
			    // y의 경우에도 동일하게 진행
				} else {
				    if (y_diff > 0) y_diff--;
				    else y_diff++;
				    turnX = true;
				}
				count++;
			}
			
			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		
		System.out.println(sb);
	}
}
