import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(bf.readLine());

		for (int t = 1; t<=TestCase; t++) { 
			int N = Integer.parseInt(bf.readLine());
			int[] board = new int[5001];
			for (int n=0; n<N; n++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				for (int i=a; i<=b; i++) {
					board[i] +=1;
				}
			}
			
			int P = Integer.parseInt(bf.readLine());
			System.out.print("#"+t+" ");
			for (int i=0; i<P; i++) {
				int idx = Integer.parseInt(bf.readLine());
				System.out.print(board[idx]+" ");
			}
            System.out.println();
		}
	}

}
