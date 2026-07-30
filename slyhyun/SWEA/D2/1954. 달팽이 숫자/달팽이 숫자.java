import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int d = 0;
            int r = 0;
            int c = 0;

            int[][] arr = new int[N][N];

            for (int i = 1; i <= N * N; i++) {
                arr[r][c] = i;

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N || arr[nr][nc] != 0) {
                    d = (d + 1) % 4;

                    nr = r + dr[d];
                    nc = c + dc[d];
                }

                r = nr;
                c = nc;
            }

            sb.append("#").append(tc).append("\n");

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    sb.append(arr[i][j]).append(" ");
                }

                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
