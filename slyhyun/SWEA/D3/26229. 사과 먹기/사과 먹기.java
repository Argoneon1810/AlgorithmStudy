import java.util.*;
import java.io.*;
 
public class Solution {
    // 좌표 정보를 받기 위한 클래스 설정
    static class Point {
        int r, c, num;
 
        public Point(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
 
            List<Point> targets = new ArrayList<>();
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
 
                for (int j = 0; j < N; j++) {
                    int val = Integer.parseInt(st.nextToken());
 
                    // 사과 정보만 리스트에 추가
                    if (val != 0) {
                        targets.add(new Point(i, j, val));
                    }
                }
            }
 
            // 사과 번호 순서대로 정렬
            targets.sort((a, b) -> a.num - b.num);
 
            // 초기값 설정
            int curR = 0, curC = 0, dir = 0;
            int total = 0;
 
            for (Point p : targets) {
                int[] result = move(curR, curC, p.r, p.c, dir);
 
                total += result[0];
                dir = result[1];
 
                curR = p.r;
                curC = p.c;
            }
 
            sb.append("#").append(tc).append(" ").append(total).append("\n");
        }
 
        System.out.print(sb);
    }
 
    // 접근법: 이전 사과에서 다음 사과로 갈 때 변해야 하는 방향은 최대 2개로 정해져 있다. 그렇기 때문에 회전하면서 해당 방향 2개가 전부 나올 때까지 횟수 누적
    static int[] move(int prevR, int prevC, int nextR, int nextC, int dir) {
        int dr = nextR - prevR;
        int dc = nextC - prevC;
 
        // 다음 사과로 가는데 필요한 방향을 리스트에 저장
        List<Integer> required = new ArrayList<>();
 
        if (dr > 0) required.add(1); // 아래
        if (dr < 0) required.add(3); // 위
        if (dc > 0) required.add(0); // 오른쪽
        if (dc < 0) required.add(2); // 왼쪽
 
        int turns = 0;
 
        // 필요한 방향이 전부 나올 때까지 회전하며 횟수 누적
        while (!required.isEmpty()) {
            if (required.contains(dir)) {
                required.remove((Integer) dir);
            } else {
                dir = (dir + 1) % 4;
 
                turns++;
            }
        }
 
        return new int[]{turns, dir};
    }
}
