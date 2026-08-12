import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(bf.readLine());
		
		for (int t=1; t<=TestCase; t++) {
			int N = Integer.parseInt(bf.readLine());
			Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			StringBuilder sb = new StringBuilder();
			for (int i =0; i<N; i++) {
				String st = bf.readLine();
				
				
				// 삽입인 경우				
				if (st.charAt(0) == '1') {
					String[] arr = st.split(" ");
					pq.add(Integer.parseInt(arr[1]));
				}
				// 삭제인 경우
				else {
					
					/* Integer 쓴 이유 pq.poll 해도 null 로 진행하기 위해 
					   그냥 int 로 바로 입력하게 된다면 오류가 남
					   poll의 반환값은 Integer 이기 때문에 
					*/
					Integer str = pq.poll();
					if (str == null) {
						sb.append(-1);
					}else {
						sb.append(str);
					}
					sb.append(" ");
				}
			}
			System.out.println("#" + t + " "+sb);
			
		}
	}
}
