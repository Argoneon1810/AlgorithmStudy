import java.util.*;
import java.io.*;

public class Solution {
	static int minTime;
	
	// 좌표 정보를 받기 위한 클래스 설정
	static class Point{
		int r;
		int c;
		int num;
		
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
			        
			        // 몬스터 또는 고객 정보만 리스트에 추가
			        if (val != 0) {
			            targets.add(new Point(i, j, val));
			        }
			    }
			}
			
			minTime = Integer.MAX_VALUE;
			// 이미 방문한 몬스터 또는 고객 방문 금지
			boolean[] visited = new boolean[targets.size()];
			// 고객에 도달했을 때 해당 고객이 의뢰한 몬스터를 사냥했는지 확인
			boolean[] hunted = new boolean[5];
			
			dfs(0, 0, 0, 0, targets, visited, hunted);
			
			sb.append("#").append(tc).append(" ").append(minTime).append("\n");
		}
		
		System.out.println(sb);
	}
	
	// 몬스터와 고객 방문 순서의 모든 조합을 찾기 위한 dfs
	public static void dfs(int r, int c, int depth, int sum, List<Point> t, boolean[] v, boolean[] h) {
		// 탈출 조건: 모든 몬스터와 고객을 방문했으면 최소 시간 계산
		if (depth == t.size()) {
			minTime = Math.min(minTime, sum);
			return;
		}
		
		for (int i = 0; i < t.size(); i++) {
			// 방문한 곳이면 통과
			if (v[i]) {
				continue;
			}
			
			int dist;
			Point next = t.get(i);
			
			// 몬스터를 방문한 경우
			if (next.num > 0) {
				v[i] = true;
				h[next.num] = true;
				
				// 맨해튼 거리 계산
				dist = Math.abs(r - next.r) + Math.abs(c - next.c);
				
				// 그 위치에서 재귀 시작
				dfs(next.r, next.c, depth + 1, sum + dist, t, v,  h);
				
				// 재귀 후 방문 처리 취소
				h[next.num] = false;
				v[i] = false;
			}
			// 고객을 방문한 경우
			else {
				// 고객이 의뢰한 몬스터를 잡은 경우
				if (h[Math.abs(next.num)]) {
					v[i] = true;
					
					// 맨해튼 거리 계산
					dist = Math.abs(r - next.r) + Math.abs(c - next.c);
					
					// 그 위치에서 재귀 시작
					dfs(next.r, next.c, depth + 1, sum + dist, t, v,  h);
					
					// 재귀 후 방문 처리 취소
					v[i] = false;
				}
			}
		}
	}
}
