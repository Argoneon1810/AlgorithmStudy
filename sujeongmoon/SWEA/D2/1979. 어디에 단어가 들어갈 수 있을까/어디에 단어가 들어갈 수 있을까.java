import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			// N*N의 단어 퍼즐
			// 특정 길이 K를 갖는 단어가 들어갈 수 있도록 -> 직선으로 길이가 k개만 비어있는 부분을 찾아야 한다

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 정사각형 배열의 길이
			int K = Integer.parseInt(st.nextToken()); // 찾고자하는 칸의 수

			int[][] arr = new int[N][N];

			// 배열 채우기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					// 1이 채워질 수 있는 수
				}
			}

			int answer = 0;

			// 각 열/행을 돌면서 1을 발견하면 수를 count, 1이 끝났을 때 K와 일치한다면 answer++
			for (int i = 0; i < N; i++) {

				// 열
				int countRow = 0;
				boolean isBlankRow = false;
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 1) {
						if (!isBlankRow) { // 처음 빈 칸을 발견헀다면 빈 칸이라고 상태 변경해주기
							isBlankRow = true;
						}
						countRow++;
						// 만약 마지막 칸이라면 count값 비교해주기
						if (j == N - 1) {
							if (countRow == K) {
								answer++;
							}
						}
					}
					if (arr[i][j] == 0) {
						if (isBlankRow) { // 빈칸이 끝났다면 끝났다고 상태 변경해주고, count값 비교
							isBlankRow = false;
							if (countRow == K) {
								answer++;
							}
							countRow = 0;
						}
					}
				}

				// 행
				int countCol = 0;
				boolean isBlankCol = false;
				for (int j = 0; j < N; j++) {
					if (arr[j][i] == 1) {
						if (!isBlankCol) { // 처음 빈 칸을 발견헀다면 빈 칸이라고 상태 변경해주기
							isBlankCol = true;
						}
						countCol++;
						// 만약 마지막 칸이라면 count값 비교해주기
						if (j == N - 1) {
							if (countCol == K) {
								answer++;
							}
						}
					}
					if (arr[j][i] == 0) {
						if (isBlankCol) { // 빈칸이 끝났다면 끝났다고 상태 변경해주고, count값 비교
							isBlankCol = false;
							if (countCol == K) {
								answer++;
							}
							countCol = 0;
						}
					}
				}
				
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();
	}
}
