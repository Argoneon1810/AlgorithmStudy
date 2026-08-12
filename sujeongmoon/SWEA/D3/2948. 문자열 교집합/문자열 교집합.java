import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

class Solution
{
	
	public static void main(String args[]) throws Exception
	{
		
		// 알파벳 소문자로 이루어진 문자열
		// 입력으로 2개의 문자열 집합이 주어졌을 때, 두 집합에 모두 속하는 원소의 개수
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int answer = 0;
			
			HashSet<String> set = new HashSet<>();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				set.add(st.nextToken()); // N만큼을 전부 set에
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				String mStr = st.nextToken();
				if (set.contains(mStr)) {
					answer++;
				}
			}
			
			bw.write("#" + t + " " + answer + "\n");

		}
		
		bw.flush();
		bw.close();
		
	}
	
}