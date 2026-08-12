import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {

	public static void main(String args[]) throws Exception {

		// A가 B의 부모노드이면, A의 키값과 B의 키값 사이에는 항상 일정한 대소관계가 성립한다.
		// 부모노드의 키값이 자식노드의 키값보다 항상 크거나 같은 힙을 최대 힙, 부모노드의 키값이 자식노드의 키값보다 항상 작거나 같은 힙을 최소 힙

		// 최대 힙이 비어있을 떄 다음의 두 가지 연산을 수행할 수 있는지
		// 자연수 x를 삽입하고, 최대 힙의 루트 노드의 키값을 출력하고, 해당 노드를 삭제

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

			int N = Integer.parseInt(br.readLine()); // 수행해야하는 연산의 수
			boolean isEmpty = false; // pq가 비었음을 알려줄 boolean
			ArrayList<Integer> list = new ArrayList<>(); // 2의 연산 내용들을 담을 리스트

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");

			// 연산 수행
			for (int n = 0; n < N; n++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				int num = Integer.parseInt(st.nextToken());

				if (num == 1) { // 1번이면 pq에 추가
					pq.add(Integer.parseInt(st.nextToken()));

				} else { // 2번이면 list에 최대값 추가 / pq 비었을 시 -1로 처리되도록 boolean 값 변경
					if (!pq.isEmpty()) {
						sb.append(pq.poll());
					} else {
						sb.append("-1");

					}
					sb.append(" ");
				}
			}

			sb.append("\n");
			bw.write(sb.toString());

		}

		bw.flush();
		bw.close();

	}

}
