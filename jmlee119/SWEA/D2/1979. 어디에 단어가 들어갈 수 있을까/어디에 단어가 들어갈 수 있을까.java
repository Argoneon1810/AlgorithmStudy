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
			int K = Integer.parseInt(stringTokenizer.nextToken());
			
			board = new int[N][N];
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j=0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int answer = 0;
			
			// 가로 확인
			for (int i=0; i<N; i++) {
				int row_count =0;
				for(int j=0; j<N; j++) {
					if (board[i][j] == 0) row_count =0;
					if (board[i][j] == 1) row_count +=1;
					if (row_count == K) {
						if (j+1<N &&board[i][j+1] == 0 || j+1 == N) {
							answer+=1;
							row_count =0;
						}
					}
				}
			}
			// 세로 확인
			for (int i=0; i<N; i++) {
				int col_count =0;
				for(int j=0; j<N; j++) {	
					if (board[j][i] == 0) col_count = 0;
					if (board[j][i] == 1) col_count+=1;
					if (col_count == K) {
						if (j+1<N &&board[j+1][i] == 0 || j+1 == N) {
							answer+=1;
							col_count =0;
						}
					}
				}
			}						
			System.out.println("#"+t + " " +answer);
		
		}
			

	}

}
