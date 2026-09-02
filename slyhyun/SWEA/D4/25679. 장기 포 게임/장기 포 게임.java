import java.util.*;
import java.io.*;

public class Solution {
    static int N;
    static int[][] arr;
    static boolean[][] catched;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            catched = new boolean[N][N];

            int startR = 0;
            int startC = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());

                    // 포를 찾으면 그 위치부터 dfs 시작
                    if (arr[i][j] == 2) {
                        startR = i;
                        startC = j;
                    }
                }
            }

            dfs(startR, startC, 0);

            int count = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (catched[i][j]) count++;
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int r, int c, int depth) {
        if (depth == 3) return;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            boolean jump = false;

            while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                // 1 만나기 전
                if (!jump) {
                    // 1 만나면 다음부턴 이동 가능, 0 만나면 1 만날 때까지 탐색
                    if (arr[nr][nc] == 1) {
                        jump = true;
                    }
                // 1 만난 후
                } else {
                    // 1을 만나면 잡았다고 표시, 그 위치에서 dfs
                    if (arr[nr][nc] == 1) {
                        catched[nr][nc] = true;

                        // dfs 하기 전 0, 한 후에 1로 변경
                        arr[nr][nc] = 0;
                        dfs(nr, nc, depth + 1);
                        arr[nr][nc] = 1;

                        // 알을 잡은 후에는 해당 방향으로 더 진행 불가하므로 while문 탈출
                        break;
                    // 0을 만나면 그 위치에서 dfs
                    } else {
                        dfs(nr, nc, depth + 1);
                    }
                }

                nr += dr[i];
                nc += dc[i];
            }
        }
    }
}
