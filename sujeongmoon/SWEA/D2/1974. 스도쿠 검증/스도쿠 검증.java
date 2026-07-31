import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution
{
	
	public static void main(String args[]) throws Exception
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 스도쿠
		// 겹치는 숫자가 없을 경우 1을 정답으로 출력, 아닐 경우 0을 출력
		
		int test_case = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= test_case; tc++) {
			
			// 9*9의 배열 입력받기
			int[][] arr = new int[9][9];
			
			for (int i = 0; i < 9; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// isRight면 완벽한 스도쿠
			boolean isRight = true;
			
			// 하나라도 완벽하지 않으면 false처리
			if (!isRowRight(arr) || !isColRight(arr) || !isSectionRight(arr)) {
				isRight = false;
			} 
			
			// 결과 출력
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(isRight ? 1 : 0).append("\n");
			bw.write(sb.toString());
			
		}
		
		bw.flush();
		bw.close();
	}
	
	// 각 열이 겹치는 숫자가 없는지 확인
	static boolean isRowRight(int[][] arr) {
		for (int i = 0; i < 9; i++) {
			int[] noDuplicate = new int[10]; // 인덱스 1부터 9까지 사용
			for (int j = 0; j < 9; j++) {
				noDuplicate[arr[i][j]]++; 
				// 만약 각 인덱스가 1번 이상 들어갔다면, 겹치는 숫자가 2개 이상 있었다는 뜻
				if (noDuplicate[arr[i][j]] > 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	// 각 행이 겹치는 숫자가 없는지 확인
	static boolean isColRight(int[][] arr) {
		for (int i = 0; i < 9; i++) {
			int[] noDuplicate = new int[10]; // 인덱스 1부터 9까지 사용
			for (int j = 0; j < 9; j++) {
				noDuplicate[arr[j][i]]++; // col이니까 j, i로 조회
				// 만약 각 인덱스가 1번 이상 들어갔다면, 겹치는 숫자가 2개 이상 있었다는 뜻
				if (noDuplicate[arr[i][j]] > 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	// 각 섹션별 겹치는 숫자가 없는지 확인
	static boolean isSectionRight(int[][] arr) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) { // 각각의 섹션 첫 번째 칸을 기준점으로
				int[] noDuplicate = new int[10]; // 인덱스 1부터 9까지 사용
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) { //칸 안을 돈다
						noDuplicate[arr[i*3+k][j*3+l]]++; // 각 칸의 인덱스를 계산
						// 만약 각 인덱스가 1번 이상 들어갔다면, 겹치는 숫자가 2개 이상 있었다는 뜻
						
						if (noDuplicate[arr[i*3+k][j*3+l]] > 1) {
							return false;
						}
					}
				}
				
			}
		}
		return true;
	}
	
}