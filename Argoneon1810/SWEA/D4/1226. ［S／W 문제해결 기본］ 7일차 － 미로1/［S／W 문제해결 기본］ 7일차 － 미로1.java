import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class MyHeap<T> extends PriorityQueue<T> {
	private static final long serialVersionUID = 7301991324580155611L;
	MyHeap(Comparator<? super T> cmp) { super(cmp); }
	void idfcPush(T data) { this.offer(data); }
	T idfcPop() { return this.poll(); }
}

class Solution {
	static final int N = 16;
	static final int NSQ = N*N;
	static final int ditx[] = { -N, N, -1, 1 };
	static final int START = 2;
	static final int END = 3;
	static final int INF = Integer.MAX_VALUE;

	static boolean isOffboard(int c) {
		return isOffboard(c % N, c / N);
	}

	static boolean isOffboard(int cx, int cy) {
		return cx < 0 || cx >= N || cy < 0 || cy >= N;
	}

	static boolean isBlocked(int board[], int c) {
		return isBlocked(board[c]);
	}

	static boolean isBlocked(int curr) {
		return (curr != 0) && ((curr != START) && (curr != END));
	}
	
	static int cost(int board[], int c) {
		// 이 문제는 바닥에 가중치가 없어 항상 1이다.
		// A*도 다익스트라도, 필요하면 여기서 가중치값을 읽어가면 된다.
		return 1;
	}
	
	static int heuristic(int c, int end) {
		// 현위치에서 목적지까지의 남은 거리 어림값
		// 목적지로 곧장 달려가는 경로를 먼저 방문하도록 바이어스를 주는 역할
		// 현재 L1거리가 적용되어 있는데, L2 Squared를 써도 문제 없는 것으로 안다.
		// 그 경로가 악의적으로 막혀 있는 문제에서는 오히려 탐색을 느리게 만드는 부작용이 있다.
		int cx = c % N, cy = c / N;
		int ex = end % N, ey = end / N;
		return Math.abs(cx - ex) + Math.abs(cy - ey);
	}

	void fill(int board[], int start, int end) {
		// 다익스트라 : PQ를 쓰면 어떤 칸을 처음 방문할 때 바닥에 기록한 거리가
		//           곧 그 칸의 가장 이상적인 거리이다.
		//           음수 가중치에 대한 지원을 포기하는 대신 재방문 없는 처리를 구현. 
		//           (대신 힙이 최소값을 찾아 주는 비용을 감수해야 한다.)
		// A*      : 다익스트라는 목적지가 어느 쪽인지 모르므로 사방으로 고르게 퍼진다.
		//           A*는 거기에 '목적지까지의 남은 거리 어림값'을 추가적으로 고려한다.
		//           지금까지 온 거리에 남은 거리의 어림값을 더한 것으로 힙을 정렬하면
		//           목적지 방향 칸이 먼저 꺼내져 멀리 돌아가는 경로보다 선호하게 된다.
		//           어림값을 항상 0을 뱉게 하면 그대로 다익스트라가 된다.
		MyHeap<int[]> md = new MyHeap<>(
				(a, b) -> Integer.compare(a[1], b[1])
		);
		// { 칸, 그 칸까지의 거리 }
		// Tuple 구현할까 했는데 이게 더 저렴한거 같음
		// 원래 {start, 0}이었다. 0+heuristic(start, end)로 바뀌었다 보면 된다.
		md.idfcPush(new int[] { start, heuristic(start, end) });
		while (!md.isEmpty()) {
			int c[] = md.idfcPop();
			int c_idx = c[0];
			if (c_idx == end)	// A*는 '이 경로가 정말로 최적 경로인가'는
				return;			// .. 신경쓰지 않는다. 그냥 제일 빨리 구해지는 
								// .. 경로를 뱉는 알고리즘이기 때문에
								// .. 다익스트라와 달리 경로 하나가 정해지면
								// .. 더 저렴한 비용 경로를 찾지 않고  종료한다.
			if (c[1] - heuristic(c_idx, end) > board[NSQ+c_idx])
				continue; // 새로 기록 시도하는 거리가 보드에 기록된 거리보다 멀면 버림
			for (int d = 0; d < 4; ++d) {	// 4방탐색
				int n = c_idx + ditx[d];
				if (isOffboard(n))			// 경계조건
					continue;
				if (isBlocked(board, n))	// 4방이 방문 가능한 칸인지
					continue;
				if (board[NSQ+c_idx]+cost(board, n) < board[NSQ+n]) {
					// 더 나은 거리 찾기
					board[NSQ+n] = board[NSQ+c_idx] + cost(board, n);
					md.idfcPush(new int[] { n, board[NSQ+n] });
				}
			}
		}
	}

	String solveInner(BufferedReader br) throws IOException {
		int board[] = new int[2 * NSQ];
		int start = 0, end = 0;
		br.readLine(); // 문제번호 줄 버리기
		for (int j = 0; j < N; ++j) {
			char carr[] = br.readLine().toCharArray();
			for (int i = 0; i < N; ++i) {
				int citx = j * N + i;
				board[citx] = carr[i] - '0';
				board[NSQ+citx] = INF;
				switch (board[citx]) {
				case 2:
					start = citx;
					break;
				case 3:
					end = citx;
					break;
				}
			}
		}
		board[NSQ+start] = 0;
		fill(board, start, end);
		return board[NSQ+end] != INF ? "1" : "0";
	}

	void solve() throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		int T;
		T = 10;
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++)
			sb.append('#').append(test_case)
					.append(' ').append(solveInner(br))
					.append('\n');
		System.out.print(sb);
	}

	public static void main(String args[]) throws Exception {
		new Solution().solve();
	}
}