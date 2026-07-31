import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(bf.readLine());

		for (int t = 1; t<=TestCase; t++) {
			
			int N = Integer.parseInt(bf.readLine());
			int[][] arr = new int[N][N];
			
			if (N==1) {
				System.out.println("#"+t);
				System.out.println(1);
				continue;
			}
			
			arr[0][0] = 1;
			arr[1][0] = 1;
			arr[1][1] = 1;
			
			for (int i=2; i<N; i++) {
				for (int j=0; j<=i; j++) {
					if (j==0) arr[i][0]  = 1;
					else if(j==i) arr[i][j] =1;
					else arr[i][j] = arr[i-1][j] + arr[i-1][j-1];
				}
			}
			
			System.out.println("#"+t);
			for(int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (arr[i][j] == 0) break;
					else System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			} 
		}
	}
}

