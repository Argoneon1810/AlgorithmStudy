import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;

class MyHashQueue<T> extends ArrayDeque<T> {
	private static final long serialVersionUID = 7301991324580155611L;
	private final HashSet<T> members = new HashSet<>();
	
	boolean has(Object data) { return members.contains(data); }
	
	void idfcPush(T data) {
		this.offer(data);
		members.add(data);
	}

	T idfcPop() {
		T data = this.poll();
		members.remove(data);
		return data;
	}
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

	void fill(int board[], int start) {
		// 벨만 포드 : 어떤 칸이 업데이트 되어야 하는지 모름.
		//          그냥 전부 재방문해서 업데이트를 할 칸만 하게 함
		// SPFA : 주목을 받아야 할 칸 후보를 이전 스텝에서 미리 알 수 있으므로
		//        방문이 필요없는 칸은 애초에 방문을 안하고 해결
		//        조기탈출 조건도 필요 없음 
		MyHashQueue<Integer> md = new MyHashQueue<>();
		md.idfcPush(start);
		while (!md.isEmpty()) {
			int c = md.idfcPop();
			for (int d = 0; d < 4; ++d) {	// 4방탐색
				int n = c + ditx[d];
				if (isOffboard(n))			// 경계조건
					continue;
				if (isBlocked(board, n))	// 4방이 방문 가능한 칸인지
					continue;
				if (board[NSQ+c] + 1 < board[NSQ+n]) {	// 더 나은
					board[NSQ+n] = board[NSQ+c] + 1;	// 거리 찾기
					if (!md.has(n))			// '재방문 필요'를 마킹하는
						md.idfcPush(n);		// .. 것이므로 같은게 두번 이상
											// .. 들어갈 이유가 없다.
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