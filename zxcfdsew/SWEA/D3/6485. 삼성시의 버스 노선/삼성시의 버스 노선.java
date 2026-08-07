import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc < T + 1; tc++) {
            int[] busStop = new int[5001];
            int N = Integer.parseInt(br.readLine().trim());
            int[][] ab = new int[N][2];

            for (int i = 0; i < N; i++) {
                int index = 0;
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                while (st.hasMoreTokens()) {
                    ab[i][index++] = Integer.parseInt(st.nextToken());
                }
            }
            int P = Integer.parseInt(br.readLine().trim());

            int[] C = new int[P];

            for (int i = 0; i < P; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                while (st.hasMoreTokens()) {
                    C[i] = Integer.parseInt(st.nextToken());
                }
            }

            for (int[] range : ab) {
                for (int i = range[0]; i <= range[1]; i++) {
                    busStop[i] += 1;
                }
            }

            StringBuilder sb = new StringBuilder("#");
            sb.append(tc).append(" ");

            for (int i = 0; i < P; i++) {
                sb.append(busStop[C[i]]).append(" ");
            }
            System.out.println(sb);
        }
    }
}
