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
		// 그래도 다익스트라로 구현했으니 꼴을 맞추고자 넣었다.
		return 1;
	}

	void fill(int board[], int start) {
		// 다익스트라 : PQ를 쓰면 어떤 칸을 처음 방문할 때 바닥에 기록한 거리가
		//           곧 그 칸의 가장 이상적인 거리이다.
		//           음수 가중치에 대한 지원을 포기하는 대신 재방문 없는 처리를 구현. 
		//           (대신 힙이 최소값을 찾아 주는 비용을 감수해야 한다.)
		MyHeap<int[]> md = new MyHeap<>(
				(a, b) -> Integer.compare(a[1], b[1])
		);
		md.idfcPush(new int[] { start, 0 });	// { 칸, 그 칸까지의 거리 }
												// Tuple 구현할까 했는데
												// 이게 더 저렴한거 같음
		while (!md.isEmpty()) {
			int c[] = md.idfcPop();
			int c_idx = c[0];
			if (c[1] > board[NSQ+c_idx]) 	// 새로 기록 시도하는 거리가
				continue;					// 보드에 기록된 거리보다 멀면 버림
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
		fill(board, start);
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