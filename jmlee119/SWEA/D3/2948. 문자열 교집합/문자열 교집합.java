import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(bf.readLine());
		StringTokenizer st;
		
		for (int t=1; t<=TestCase; t++) {
			st= new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// 작은배열이 큰 배열에 접근하면서 존재하는지 여부를 체크
			
			// 중복 제거를 위한 hashset 
			Set<String> set1 = new HashSet<>();
			Set<String> set2 = new HashSet<>();
			
			st = new StringTokenizer(bf.readLine());
			for (int i =0; i<N; i++) {
				set1.add(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine());
			for (int i =0; i<M; i++) {
				set2.add(st.nextToken());
			}
			
			int answer = 0;
			for (String str : set2) {
				if (set1.contains(str)) {
					answer +=1;
				}
			}
			
			System.out.println("#" + t + " " + answer);
		}
	}
}
