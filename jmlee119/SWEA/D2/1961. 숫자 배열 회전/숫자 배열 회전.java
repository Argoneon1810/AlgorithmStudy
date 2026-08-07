import java.util.*;
import java.io.*;

public class Solution {
	static int num1;
	int num2;
	public static void main(String args[]) throws Exception
	{
        
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(bf.readLine());
		for(int t=1; t<=TestCase; t++) {
			StringTokenizer st1 = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st1.nextToken());
			String[][] boards = new String[N][N];
			for (int in = 0; in<N; in++) {	
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int in2 = 0; in2<N; in2++) {	
					boards[in][in2] = st.nextToken();
				}
			}
			String[][] answer = new String[N][3];
			
			// 1번쨰 줄 출력
			for (int j=0; j<N; j++) {
				StringBuilder sb = new StringBuilder();
				for(int k=N-1; k>=0; k--) {
					sb.append(boards[k][j]);
				}
				answer[j][0] = sb.toString();
			}
			
			// 2번째 출 출력
			int temp2 =0;
			for (int j=N-1; j>=0; j--) {
				StringBuilder sb = new StringBuilder();
				for(int k=N-1; k>=0; k--) {
					sb.append(boards[j][k]);
				}
				answer[temp2][1] =sb.toString();
				temp2++;
			}
			// 3번째 출 출력
			int temp3 =0;
			for (int j=N-1; j>=0; j--) {
				StringBuilder sb = new StringBuilder();
				for(int k=0; k<N; k++) {
					sb.append(boards[k][j]);
				}
				answer[temp3][2] = sb.toString();
				temp3++;
				
			}	
			System.out.println("#" + t);
			for (int i= 0; i< N; i++) {
				StringBuilder sb = new StringBuilder();
				for (int j=0; j<3; j++) {
					sb.append(answer[i][j]);
					if (j!= N-1) {
						sb.append(" ");
					}
				}
				System.out.println(sb);
				
			}
		}
	}
}