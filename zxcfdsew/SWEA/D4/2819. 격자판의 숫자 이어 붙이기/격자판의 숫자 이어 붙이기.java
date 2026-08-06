import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {

    // 위쪽에서부터 시계방향
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        final int SIZE = 4;

        for (int tc = 1; tc < T + 1; tc++) {
            String[][] board = new String[SIZE][SIZE];
            HashSet<String> result = new HashSet<>();  // 결과 담을 배열

            // 배열 입력받아서 board에 저장
            for (int i = 0; i < SIZE; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < SIZE; j++) {
                    board[i][j] = st.nextToken();
                }
            }

            // 모든 위치에서 검사
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    dfs(i, j, "" + board[i][j], result, board);
                }
            }

            // 결과 출력
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ").append(result.size());
            System.out.println(sb);
        }
    }

    static void dfs(int r, int c, String path, HashSet<String> result, String[][] board) {
        // 길이가 7이면 종료
        if (path.length() == 7) {
            result.add(path);
            return;
        }

        // 현재위치에서 4방향을 계산
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 경계선 확인
            if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;

            dfs(nr, nc, path + board[nr][nc], result, board);
        }
    }
}