import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Solution
{
	
	static int[] dr = new int[] {1, 0, -1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	public static void main(String args[]) throws Exception
	{
		
		// 4*4 격자칸, 0~9 사이의 숫자
		// 시작 포함해서 동서남북으로 총 여섯 번 이동 (7자리 수)
		// 다시 갔던 길을 돌아가도 된다
		// 만들 수 있는 서로 다른 일곱 자리 수들의 개수
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			char[][] arr = new char[4][4];
			Set<String> set = new HashSet<>();
			
			// 배열 채우기
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					arr[i][j] = st.nextToken().charAt(0);
				}
			}
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					dfs(i, j, arr, set, new StringBuilder());
				}
			}

			// set의 사이즈 출력
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(set.size()).append("\n");
			bw.write(sb.toString());
			
		}
		
		bw.flush();
		bw.close();
	}
	
	
	static void dfs(int r, int c, char[][] arr, Set<String> set, StringBuilder sb) {
		// depth(StringBuilder.length())가 7이 되었을 때 -> 무조건 종료
		
		if (sb.length() == 7) {
			set.add(sb.toString());
			return;
		}
		
		sb.append(arr[r][c]);
		
		for (int i = 0; i < 4; i++) {

			int nextR = r+dr[i];
			int nextC = c+dc[i];
			
			if (nextR < 0 || nextR >= 4 || nextC <0 || nextC >= 4) {
				continue;
			}
			
			dfs(nextR, nextC, arr, set, sb);
		}
		sb.setLength(sb.length()-1); // sb는 객체이므로 setLength로 길이 조정 필요!!
	}
	
}