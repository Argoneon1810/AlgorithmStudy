import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int answer = 0;
            int[][] arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                int rowCount = 0;
                int colCount = 0;

                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == 1) {
                        rowCount++;
                    } else {
                        if (rowCount == K) {
                            answer++;
                        }
                        rowCount = 0;
                    }

                    if (arr[j][i] == 1) {
                        colCount++;
                    } else {
                        if (colCount == K) {
                            answer++;
                        }
                        colCount = 0;
                    }
                }

                if (rowCount == K) {
                    answer++;
                }
                if (colCount == K) {
                    answer++;
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
