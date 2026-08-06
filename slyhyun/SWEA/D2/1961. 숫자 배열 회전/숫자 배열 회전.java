import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[][] arr = new int[N][N];
            int[][] rotate1 = new int[N][N];
            int[][] rotate2 = new int[N][N];
            int[][] rotate3 = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    rotate1[i][j] = arr[N - 1 - j][i];
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    rotate2[i][j] = rotate1[N - 1 - j][i];
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    rotate3[i][j] = rotate2[N - 1 - j][i];
                }
            }

            sb.append("#").append(tc).append('\n');
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(rotate1[i][j]);
                }
                sb.append(" ");
                for (int j = 0; j < N; j++) {
                    sb.append(rotate2[i][j]);
                }
                sb.append(" ");
                for (int j = 0; j < N; j++) {
                    sb.append(rotate3[i][j]);
                }
                sb.append(" ").append("\n");
            }
        }
        System.out.println(sb);
    }
}