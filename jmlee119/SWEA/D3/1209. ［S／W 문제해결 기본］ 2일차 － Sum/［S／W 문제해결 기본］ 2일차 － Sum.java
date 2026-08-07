import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		for(int t=1; t<=10; t++) {
			int Testcase = Integer.parseInt(bf.readLine());
			int[][] boards = new int[100][100];
			for(int i=0; i<100; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j=0; j<100; j++) {
					boards[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0;
			// 가로 계산
			for(int i=0; i<100; i++) {
				int temp = 0;
				for(int j=0; j<100; j++) {
					temp += boards[i][j]; 
				}
				answer = Math.max(answer, temp);
			}
			// 세로 계산
			for(int i=0; i<100; i++) {
				int temp = 0;
				for(int j=0; j<100; j++) {
					temp += boards[j][i]; 
				}
				answer = Math.max(answer, temp);
			}
			// 오른쪽 대각선
			int right = 0;
			for(int i=0; i<100; i++) {
				right += boards[i][i];
			}
			answer = Math.max(answer, right);
			// 왼쪽 대각선
			int left = 0;
			for(int i=99; i>=0; i--) {
				left += boards[i][99-i];
			}
			answer = Math.max(answer, left);
			
			System.out.println("#"+Testcase+" " + answer);
			
		}
		
	}
}
