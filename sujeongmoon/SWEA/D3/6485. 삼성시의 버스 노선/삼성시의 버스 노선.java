import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 버스 정류장 1-5000, 버스 노선은 번호가 Ai 이상이고 Bi이하인 모든 정류장을 다니는 노선이다
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine());
			int[] busStops = new int[5001]; // 버스정류장 1~5000

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				for (int j = a; j <= b; j++) {
					busStops[j]++;
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#" + tc + " ");

			int P = Integer.parseInt(br.readLine());
			for (int i = 0; i < P; i++) {
				int nowStop = Integer.parseInt(br.readLine());
				sb.append(busStops[nowStop]).append(" ");
			}
			bw.write(sb.toString());
			bw.write('\n');
		}
		bw.flush();
		bw.close();
	}
}
