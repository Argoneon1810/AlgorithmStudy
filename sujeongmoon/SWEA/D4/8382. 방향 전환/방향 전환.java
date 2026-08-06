import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution
{
	
	public static void main(String args[]) throws Exception
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		//무조건 가로이동 다음에는 세로이동이 되어야 함
		
		for (int tc = 1; tc <= T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] start = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			int[] dest = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			int xDiff = Math.abs(start[0] - dest[0]);
			int yDiff = Math.abs(start[1] - dest[1]);
			
			int diff = Math.min(xDiff, yDiff); // 둘의 공통 차이
			int diff2 = Math.max(xDiff,  yDiff) - diff; // 나머지 차이
			
			int answer = diff*2; // 우선 공통이동 구해주기
			
			if (diff2 > 0 ) {
				if (diff2 % 2 == 1) {
					answer += (diff2-1)*2 + 1;
				} else {
					answer += diff2 * 2;
				}
			}
			
			StringBuilder sb = new StringBuilder(); 
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();
		
		
	}
	
}