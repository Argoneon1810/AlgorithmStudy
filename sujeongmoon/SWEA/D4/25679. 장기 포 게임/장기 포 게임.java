import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {

    static int N;
    static int[][] arr; // 장기판 배열
    static Set<String> set; // 잡을 가능성이 있는 알의 좌표를 담은 set ("r c")

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;

        int T = Integer.parseInt(br.readLine()); // tc 수

        for (int t = 1;  t <= T; t++) {

            N = Integer.parseInt(br.readLine()); // 장기판의 크기
            arr = new int[N][N];
            set = new HashSet<>();

            int poR = 0;
            int poC = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 2) {
                        poR = i;
                        poC = j;
                    }
                }
            }

            dfs(poR, poC, 0);

            sb = new StringBuilder();
            sb.append("#").append(t).append(" ").append(set.size());
            System.out.println(sb);
        }

    }


    static void dfs(int nowR, int nowC, int depth) {


        // 현재 이미 3번 이동한 상태라면 리턴
        if (depth == 3) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextR = nowR + dr[i];
            int nextC = nowC + dc[i];
            boolean isCanGo = false;

            while (true) {
                if (nextR < 0 || nextR >= N ||  nextC < 0 || nextC >= N) {
                    break;
                }
                // 알을 만난 경우
                if (arr[nextR][nextC] == 1) {
                    if (!isCanGo) {
                        isCanGo = true;
                        nextR = nextR + dr[i];
                        nextC = nextC + dc[i];
                        continue; // 알을 처음 만난 경우에는 재귀 없이 넘어감
                    } else{
                        // 알을 잡은 경우 움직일 수 있는 범위 끝
                        isCanGo = false;

                        // set에 추가
                        StringBuilder sb = new StringBuilder();
                        sb.append(nextR).append(' ').append(nextC);
                        set.add(sb.toString());

                        // 백트래킹 필요
                        arr[nextR][nextC] = 0;
                        dfs(nextR, nextC, depth + 1);
                        arr[nextR][nextC] = 1;
                        break;
                    }
                }
                // 알이 없는 경우
                if (isCanGo) {
                    dfs(nextR, nextC, depth + 1);
                }

                nextR = nextR + dr[i];
                nextC = nextC + dc[i];

            }
        }

    }

}
