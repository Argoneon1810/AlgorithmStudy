import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 첫 번째 줄은 항상 숫자 0
		// 두 번쨰 줄부터 각 숫자들은, 자신의 왼쪽과 오른쪽 위의 숫자의 합으로 구성

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			int N = Integer.parseInt(br.readLine());

			// 삼각형을 담을 배열
			int[][] arr = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				arr[i][0] = 1; // 첫 시작은 언제나 1
				for(int j = 1; j < i; j++) {
					arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
				}
				arr[i][i] = 1;
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append("\n");
			bw.write(sb.toString());

			for (int i = 0; i < N; i++) {
				sb = new StringBuilder();
				for (int j = 0; j <= i; j++) {
					sb.append(arr[i][j]).append(" ");
				}
				sb.append("\n");
				bw.write(sb.toString());
			}
		}

		bw.flush();
		bw.close();

	}
}
