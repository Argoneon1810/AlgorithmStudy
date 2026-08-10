import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(bf.readLine());
		
		for (int t=1; t<=TestCase; t++) {
			int N = Integer.parseInt(bf.readLine());
			
			String answer = "Yes";
			String str = bf.readLine();
			Character[] arr = new Character[N];
			
			// tocharArray를 모르겟어요
			for (int i=0; i<N; i++) {
				arr[i] =str.charAt(i);
			}
			
			int Mid = N/2;
			
			if (N%2==1) {
				answer = "No";
			}
			else {
				for (int i=0; i<Mid; i++) {
					if (arr[i] != arr[Mid+i]) {
						answer= "No";
						break;
					}
				}	
			}
			System.out.println("#" + t + " " + answer);
			
		}
	}
}
