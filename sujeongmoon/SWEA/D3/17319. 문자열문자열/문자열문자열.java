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
		
		// 문자열 하나를 받아 그대로 두 번 연달아 쓴다.
		// 절반을 나눴을 때, 일치하면 Yes, 아니면 No
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			
			int N = Integer.parseInt(br.readLine()); // 문자열의 길이;
			boolean isAnswer = true;
			
			// 문자열의 길이가 홀수인 경우는 그냥 아예 X
			if (N % 2 == 1) {
				br.readLine(); // 안 읽을 줄 스킵
				isAnswer = false;
				bw.write(answerToString(t, isAnswer));
				continue;
			}
			
			String line = br.readLine(); // 받은 줄
			for (int i = 0; i < line.length()/2; i++) {
				if (line.charAt(i) != line.charAt(i+(line.length()/2))) {
				
					isAnswer = false;
					break;
				}
			}
			bw.write(answerToString(t, isAnswer));
		}
		bw.flush();
		bw.close();
		
		
	}
	
	static String answerToString(int t, boolean isAnswer) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("#").append(t).append(" ");
		
		if (isAnswer) {
			sb.append("Yes");
		} else {
			sb.append("No");
		}
		sb.append("\n");
		return sb.toString();
	}
	
}