import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solution {
	String solveInner(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, M;
		N = Integer.parseInt(st.nextToken());		// [2, 1000]
		M = Integer.parseInt(st.nextToken());		// [2, 2000000]
		int[] weight = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; ++i)
			weight[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(weight); 
		int i = 0, j = N-1;
		int twBest = Integer.MIN_VALUE;
		while (i != j) {
			int tw = weight[i] + weight[j];
			if (tw <= M) {
				++i;
				twBest = Integer.max(tw, twBest);
				continue;
			}
			else if (tw > M)
				--j;
		}
		return Integer.toString((twBest>=0) ? twBest : -1);
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
