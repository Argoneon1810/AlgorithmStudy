import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {

    static int N; // 풍선 개수
    static List<Integer> list; // 풍선 리스트

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N =  Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int answer = backtracking();

            //sb = new StringBuilder();
            sb.append("#").append(t).append(" ").append(answer).append("\n");
            //System.out.println(sb.toString()); -> 이 피드백 지금 이해했어요.. ^ ^
        }
        System.out.println(sb.toString());

    }

    static int backtracking() {
        if (list.size() == 1) {
            return list.get(0);
        }

        int answer = 0;

        for (int i = 0; i < list.size(); i++) {
            int nowAnswer = 0;
            // i번쨰 풍선을 터뜨릴 예정
            int now = list.get(i);
            if (i == 0) {
                nowAnswer = list.get(i+1);
            } else if (i == list.size() - 1) {
                nowAnswer = list.get(i-1);
            } else {
                nowAnswer = list.get(i-1) * list.get(i+1);
            }

            list.remove(i);
            nowAnswer += backtracking();
            list.add(i, now);
            answer = Math.max(answer, nowAnswer);
        }

        return answer;

    }

}
