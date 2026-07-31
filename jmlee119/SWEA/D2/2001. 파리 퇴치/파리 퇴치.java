import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] board;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(bf.readLine());

		for (int t = 1; t<=TestCase; t++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(stringTokenizer.nextToken());
			int M = Integer.parseInt(stringTokenizer.nextToken());
			
			board = new int[N][N];
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j=0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int answer =0 ;
			for (int i=0; i<=N-M; i++) {
				for (int j=0; j<=N-M; j++) {
					int sum = 0;
					for (int m=i; m<i+M; m++) {
						for (int n=j; n<j+M; n++) {
							sum += board[m][n];
						}
					}
					answer = Math.max(answer, sum);
				}
			}
			
			System.out.println("#" + t + " " + answer );
			
			
		}
	}
}
