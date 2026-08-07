import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 각 행의 합, 열의 합, 대각선의 합 중 최댓값을 구하기



		for (int t = 1; t <= 10; t++) {

			Integer T = Integer.parseInt(br.readLine());

			int[][] arr = new int[100][100];
			int max = Integer.MIN_VALUE;

			// 배열 채우기
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 행 / 열 최대값 구하기
			for (int i = 0; i < 100; i++) {
				int rowSum = 0; // 열 합
				int colSum = 0; // 행 합
				for (int j = 0; j < 100; j++) {
					rowSum += arr[i][j];
					colSum += arr[j][i];
				}
				max = Math.max(max, rowSum);
				max = Math.max(max, colSum);
			}

			// 대각선 최대값 구하기
			int crossSum = 0; // 왼쪽에서 출발하는 대각선
			int reverseCrossSum = 0; // 오른쪽에서 출발하는 대각선
			for (int i = 0; i < 100; i++) {
				crossSum += arr[i][i];
				reverseCrossSum += arr[i][100-i-1];
			}
			max = Math.max(max, crossSum);
			max = Math.max(max, reverseCrossSum);

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(T).append(" ").append(max).append("\n");
			bw.write(sb.toString());

		}
		bw.flush();
		bw.close();

	}
}
