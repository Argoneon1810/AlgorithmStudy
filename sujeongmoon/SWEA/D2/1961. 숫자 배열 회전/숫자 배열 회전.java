import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {

			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 접근해야하는 row, col의 인덱스를 배열로 저장
			int[] idx90 = new int[] {N-1, 0};
			int[] idx180 = new int[] {N-1, N-1};
			int[] idx270 = new int[] {0, N-1};

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append("\n");
			bw.write(sb.toString());

			for (int i = 0; i < N; i++) {
				sb = new StringBuilder();

				// 90 출력
				for (int j = 0; j < N; j++) {
					sb.append(arr[idx90[0]--][idx90[1]]);
				}
				// 인덱스 이동
				idx90[0] = N - 1;
				idx90[1]++;
				sb.append(' ');

				// 180 출력
				for (int j = 0; j < N; j++) {
					sb.append(arr[idx180[0]][idx180[1]--]);
				}
				// 인덱스 이동
				idx180[0]--;
				idx180[1] = N - 1;
				sb.append(' ');

				// 270 출력
				for (int j = 0; j < N; j++) {
					sb.append(arr[idx270[0]++][idx270[1]]);
				}
				// 인덱스 이동
				idx270[0] = 0;
				idx270[1]--;
				sb.append("\n");
				bw.write(sb.toString());
			}

		}
		bw.flush();
		bw.close();
	}
}
