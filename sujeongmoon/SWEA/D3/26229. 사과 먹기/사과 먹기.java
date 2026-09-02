import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {

    //오른쪽아래, 왼쪽아래, 왼쪽위, 오른쪽위
    static boolean[] isNextRBiggerArr = new boolean[] {true, true, false, false};
    static boolean[] isNextCBiggerArr = new boolean[] {true, false, false, true};

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;

        int T = Integer.parseInt(br.readLine()); // tc 수

        for (int t = 1; t <= T; t++) {

            int N = Integer.parseInt(br.readLine()); // 게임판의 크기
            // 사과 좌표를 담을 배열
		        int[][] appleArray = new int[11][2]; // 사과 개수가 10개로 제한돼있으니 [11]로 초기화 / {r, c} 좌표값을 담음
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
//                    char now = st.nextToken().charAt(0); -> 10을 파싱해주지 못함... 이것 떄문에 틀림
                    int now = Integer.parseInt(st.nextToken());
                    if (now != 0) { // now의 범위 1~10
                        appleArray[now][0] = i;
                        appleArray[now][1] = j;
                    }
                }
            }

            int direction = 0; // 첫 방향은 무조건 오른쪽 기준, 0오 1아 2왼 3위
            int nowR = 0;
            int nowC = 0;

            int answer = 0; // 몇 번 도는지

            boolean isNextRBigger;
            boolean isNextCBigger;

            // 다음 사과가 어디에있는지 파악하기
            for (int i = 1; i <= 10; i++) {

                int nextR = appleArray[i][0];
                int nextC = appleArray[i][1];

                // 남은 사과가 더이상 없다면 반복문 탈출
                if (nextR == 0 && nextC == 0) {
                    break;
                }

                // 다음 사과의 좌표 대소비교
                if (nextR > nowR) {
                    isNextRBigger = true;
                } else {
                    isNextRBigger = false;
                }
                if (nextC > nowC) {
                    isNextCBigger = true;
                } else {
                    isNextCBigger = false;
                }

                boolean isFind = false; // 회전 횟수를 찾았는지 판별

                for (int j = 0; j < 4; j++) {

                    // 다음 위치 확정
                    if (isNextRBigger == isNextRBiggerArr[direction] && isNextCBigger == isNextCBiggerArr[direction]) {
                        isFind = true;
                    }

                    direction = (direction + 1 ) % 4; // 방향은 무조건 바뀜
                    answer++; // 회전도 무조건 함

                    if (isFind) {
                        if (j == 3) {
                            answer--; // 4번 갈 수 있다고 생각했던 곳 -> 사실 3번만에 갈 수 있는 곳들임
                            direction = (direction + 3) % 4;
                        }
                        break;
                    }

                }

                // now좌표 바꿔주기!!!!!!!
                nowR = nextR;
                nowC = nextC;

            }

            sb = new StringBuilder();
            sb.append("#").append(t).append(" ").append(answer);
            System.out.println(sb);

        }
    }

}
