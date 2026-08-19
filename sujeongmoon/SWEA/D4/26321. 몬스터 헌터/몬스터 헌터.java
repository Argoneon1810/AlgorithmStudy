
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			int N = Integer.parseInt(br.readLine()); // 맵의 크기 N
			Map<Integer, int[]> map = new HashMap<>(); // 고객및몬스터 : 좌표를 담을 map

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {

					int now = Integer.parseInt(st.nextToken());
					if (now != 0) { // 고객 or 몬스터
						map.put(now, new int[] {i, j}); // 맵에 고객or몬스터 - 좌표 쌍을 넣어줌
					}

				}
			}

			int answer = findAnswer(map, 0, 0, 0);

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(answer);
			System.out.println(sb.toString());
		}

	}

	static int findAnswer(Map<Integer, int[]> map, int r, int c, int distance) {

		if (map.isEmpty()) {
			return distance; // 맵이 비었다면 값 리턴
		}

		int shortDistance = Integer.MAX_VALUE; // 지금까지 찾은 가장 짧은 거리
		
		for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
			// 만약 아직 몬스터를 잡지 않은 고객의 집을 먼저 방문했다면 바로 패스
			if (entry.getKey() < 0) {
				if (map.containsKey(Math.abs(entry.getKey()))) {
					continue;
				}
			}

			// 현재 좌표값과 고객or몬스터까지의 현재 거리
			int nowDistance = Math.abs(r - entry.getValue()[0]) + Math.abs(c - entry.getValue()[1]);
			Map<Integer, int[]> newMap = new HashMap<>(map); // 현재 방문한 위치를 제외할  새로운 맵 생성
			newMap.remove(entry.getKey());

			shortDistance = Math.min(
				findAnswer(newMap, entry.getValue()[0], entry.getValue()[1], distance + nowDistance), shortDistance);
		}

		return shortDistance;

	}

}
