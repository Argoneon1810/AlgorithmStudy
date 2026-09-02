import java.util.*;
import java.io.*;

public class Solution {
    static int N;
    static List<Integer> arr;
    static Map<String, Integer> memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new ArrayList<>();
            memo = new HashMap<>();

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                arr.add(Integer.parseInt(st.nextToken()));
            }

            int result = dfs(arr);

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.print(sb);
    }

    // 접근법: 모든 경우의 수를 전부 파악하기엔 중복 순서 때문에 비효율적인 것 같아 조합을 기록하고 존재하는 조합이면 값 꺼내서 사용
    static int dfs(List<Integer> balloons) {
        // 남은 풍선이 없다면 0점
        if (balloons.isEmpty()) return 0;

        // 풍선이 딱 하나만 남았다면 그 풍선 점수 자체를 반환
        if (balloons.size() == 1) return balloons.get(0);

        // 현재 남은 풍선들의 상태를 문자열 키로 변환
        String state = balloons.toString();

        // 이미 계산해 본 조합이라면 메모장에서 바로 꺼내서 사용
        if (memo.containsKey(state)) return memo.get(state);

        int maxScore = 0;

        for (int i = 0; i < balloons.size(); i++) {
            int score = 0;

            if (i == 0) {
                // 맨 왼쪽 풍선 터뜨릴 때 -> 바로 우측 풍선 값
                score = balloons.get(i + 1);
            } else if (i == balloons.size() - 1) {
                // 맨 오른쪽 풍선 터뜨릴 때 -> 바로 좌측 풍선 값
                score = balloons.get(i - 1);
            } else {
                // 양옆에 풍선이 다 있을 때 -> (좌측 값 * 우측 값)
                score = balloons.get(i - 1) * balloons.get(i + 1);
            }

            // i번째 풍선을 터뜨려 제외한 새로운 남은 풍선 리스트 생성
            List<Integer> next = new ArrayList<>(balloons);
            next.remove(i);

            // 이번 점수 + 다음 상태의 최댓값 재귀 호출
            int total = score + dfs(next);
            maxScore = Math.max(maxScore, total);
        }

        // 현재 조합에서 얻을 수 있는 최종 최댓값을 메모장에 저장
        memo.put(state, maxScore);

        return maxScore;
    }
}
