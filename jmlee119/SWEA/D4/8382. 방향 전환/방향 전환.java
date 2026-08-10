import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= testCase; t++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int answer =0 ;
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 가로 값 사이 거리
            int dx = Math.abs(x1- x2);
            // 세로 값 사이 거리
            int dy = Math.abs(y1- y2);

            /*
            만약에 x 가 1 y가 4 라면
            x 값을 먼저 0으로 맞춰 둔 후 y 이동
            1. x 를 0으로 만듬
            2. y 도 3으로 만듬
            3. y를 0으로 만들기 위해서는 한방향으로 직진하는 대신 옆의 칸을 방문하는 방식으로 채택
             */

            // max 의 경우 4 min 의 경우 1
            int max = Math.max(dx, dy);
            int min = Math.min(dx, dy);

            // 두개의 차
            int diff = max - min;

            // 2를 곱하는 이유 지그재그라서
            if (diff %2 ==0) {
                answer = max * 2;
            }
            else {
                answer = max * 2 -1;
            }
            System.out.println("#"+t+" " +answer);
        }
    }
}
