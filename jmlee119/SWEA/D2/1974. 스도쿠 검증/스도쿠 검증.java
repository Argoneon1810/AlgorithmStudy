import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] board;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(bf.readLine());

		for (int t = 1; t<=TestCase; t++) {
			board = new int[9][9];
			for (int i=0; i<9; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j=0; j<9; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean rows = true;
			// 가로 확인 
			for (int i=0; i<9; i++) {
				if (!Row(i)) {
					rows = false;
					break;
				}
			}
			boolean cols = true;
			// 세로 확인 
			for (int i=0; i<9; i++) {
				if (!Col(i)) {
					cols = false;
					break;
				}
			}
			
			// 3*3 확인
			boolean squares = true;
			for (int i=0; i<9; i+=3) {
				for (int j=0; j<9; j+=3) {
//					System.out.println(i + " " + j);
					if (!Square(i,j)) {
						squares = false;
						break;
					}
				}
			}
			int answer = 0;
			if (rows && cols && squares) answer =1;
			System.out.println("#"+t+" " +answer);
		}
	}
	
	
	// 가로 확인
	private static boolean Row(int x) {
		int sum=0;
		for (int i=0; i<9; i++) {
			sum += board[x][i];
		}
		if (sum == 45) return true;
		else return false;
	}
	
	// 세로 확인
	private static boolean Col(int y) {
		int sum=0;
		for (int i=0; i<9; i++) {
			sum += board[i][y];
		}
		if (sum == 45) return true;
		else return false;
	}
	// 3*3 확인
	private static boolean Square(int x, int y) {
		int sum = 0;
		for (int i=x; i<x+3; i++) {
			for (int j =y; j<y+3; j++) {
				sum += board[i][j];
			}
		}
		if (sum == 45) return true;
		else return false;
	}	
}
