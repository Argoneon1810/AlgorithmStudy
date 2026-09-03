import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

class MyStack<T> extends ArrayDeque<T> {
	private static final long serialVersionUID = 8960379107114503167L;
	void idfcPush(T data) { this.addFirst(data); }
	T idfcPop() { return this.poll(); }
}

class Solution {
	static final int N = 16;
	static final int ditx[] = { -N, N, -1, 1 };
	static final int START = 2;
	static final int END = 3;

	static boolean isOffboard(int c) {
		return isOffboard(c % N, c / N);
	}

	static boolean isOffboard(int cx, int cy) {
		return cx < 0 || cx >= N || cy < 0 || cy >= N;
	}

	void find(int board[], int start) {
		MyStack<Integer> md = new MyStack<>();
		md.idfcPush(start);
		while (!md.isEmpty()) {
			int c = md.idfcPop();
			if (isOffboard(c))
				continue;
			int curr = board[c];
			if ((curr != 0) && ((curr != START) && (curr != END)))
				continue;
			board[c] = -1;
			if (curr == END)
				break; //dfs early stopping (pathfinding)
			for (int d = 0; d < 4; ++d)
				md.idfcPush(c + ditx[d]);
		}
	}

	String solveInner(BufferedReader br) throws IOException {
		int board[] = new int[N * N];
		int start = 0, end = 0;
		br.readLine(); // 문제번호 줄 버리기
		for (int j = 0; j < N; ++j) {
			char carr[] = br.readLine().toCharArray();
			for (int i = 0; i < N; ++i) {
				int citx = j * N + i;
				board[citx] = carr[i] - '0';
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
		find(board, start);
		return board[end] == -1 ? "1" : "0";
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
