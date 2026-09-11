import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int top;
	static int all;
	static int[][] groups;
	static boolean groupIsStale = false;
	
	int getInitialBitmask(int N) {
		int maskHalf = 0b0;
		int maskAll = 0b0;
		int bit = 0b1;
		for (int i=0; i<N; ++i) {
			maskAll += bit;
			if (i < N/2)
				maskHalf += bit;
			bit <<= 1;
		}
		all = maskAll;
		top = bit;
		return maskHalf;
	}
	
	int nextBitMask(int N, int prev) {
		int a = prev & (-prev);
		int b = prev + a;
		int c = ((prev ^ b) >> 2) / a;
		return b | c;
	}
	
	int getComplement(int mask) {
		return all ^ mask;
	}
	
	void fillGroups(int N, int mask) {
		if (groups == null || groupIsStale) {
			groupIsStale = false;
			groups = new int[2][];
			groups[0] = new int[N/2];
			groups[1] = new int[N/2];
		}
		for (int i=0, a=0, b=0; i<N; ++i) {
			int minBit = mask & (-mask);
			if (minBit==0b1)
				groups[0][a++] = i;
			else
				groups[1][b++] = i;
			mask >>= 1;
		}
	}
	
	int getScore(int[][] S, int N, int groupID, int accumScore, int depth) {
		if (depth >= N/2)
			return accumScore;
		int mScore = 0;
		int[] mGroup = groups[groupID];
		int left = mGroup[depth];
		for (int i=depth+1; i<N/2; ++i) {
			mScore += S[left][mGroup[i]] + S[mGroup[i]][left];
		}
		return getScore(S, N, groupID, accumScore + mScore, depth+1);
	}
	
	int evaluate(int a, int b) {
		return Math.abs(a-b);
	}
	
	String solveInner(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// (4 ≤ N ≤ 16)
		int[][] S = new int[N][N];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; ++j) {
				S[i][j] = Integer.parseInt(st.nextToken());	// (1 ≤ Sij ≤ 20,000, i ≠ j)
			}
		}
		int mask = getInitialBitmask(N);
		int best = Integer.MAX_VALUE;
		int half = top >> 1;
		do {
			fillGroups(N, mask);
			int value = evaluate(getScore(S, N, 0, 0, 0), getScore(S, N, 1, 0, 0));
			best = Integer.min(best, value);
		} while((mask = nextBitMask(N, mask)) < half);
		groupIsStale = true;
		return Integer.toString(best);
	}

	void solve() throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine().trim());
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
