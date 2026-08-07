import java.util.Scanner;

class Solution
{
	static int[] dr = new int[] {0, 1, 0, -1};
	static int[] dc = new int[] {1, 0, -1, 0};
	
	public static void main(String args[]) throws Exception
	{
		// 테스트케이스 T
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			
			int[][] arr = new int[N][N]; // 배열
			int index = 0; // dr, dc의 인덱스
			int r = 0;
			int c = 0;
			
			// 배열 채우기
			for (int i = 1; i <= N*N; i++) {

				arr[r][c] = i;
				
				r += dr[index];
				c += dc[index];
				
				// 다음 인덱스 고려
				
				// 만약 범위를 넘었거나, 채워져있다면 index를 넘김
				if ((r + dr[index]) < 0 ||(r + dr[index]) >= N || (c + dc[index]) < 0 ||(c + dc[index]) >= N
						|| (arr[r + dr[index]][c + dc[index]] != 0)) {
					index = (index + 1) % 4;
				}
				
			}
			
			// 배열 출력하기
			System.out.println("#" + test_case);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
			
		}
	}
	
}