import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	static final int WORK = 0;
	static final int HOME = 1;
	static final int X = 0;
	static final int Y = 1;
	static final int UNKNOWN = -1;

	int getMemoL1Distance(
			int[][] memoDistances,
			int[][] posArray,
			int fromIdx,
			int toIdx
	) {
		if(memoDistances[fromIdx][toIdx] == UNKNOWN) {
			int[] from = posArray[fromIdx];
			int[] to = posArray[toIdx];
			int mDist = Math.abs(to[X] - from[X]) + Math.abs(to[Y] - from[Y]);
			memoDistances[fromIdx][toIdx] = mDist;
			memoDistances[toIdx][fromIdx] = mDist;
		}
		return memoDistances[fromIdx][toIdx];
	}

	int dfsVisit(
			int[][] dist,
			int[][] arr,
			boolean[] visited,
			int N,
			int current,
			int depth
	) { 
		int minDist = Integer.MAX_VALUE;
		for(int i=0; i<N+2; i++) {
			if (visited[i])
				continue;
			if (i==1 && depth!=N)
				continue;
			if (depth==N) {
				minDist = getMemoL1Distance(dist, arr, current, HOME);
				break;
			}
			else {
				visited[i]=true;
				minDist = Integer.min(
						getMemoL1Distance(dist, arr, current, i)
						+ dfsVisit(dist, arr, visited, N, i, depth+1),
						minDist
				);
				visited[i]=false;
			}
		}
		return minDist;
	}
	
	String solveInner(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numCustomers; // 2≤N≤10
		numCustomers = Integer.parseInt(st.nextToken());
		int[][] posArray = new int[numCustomers+2][2];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<numCustomers+2; ++i) {
			posArray[i][X] = Integer.parseInt(st.nextToken());
			posArray[i][Y] = Integer.parseInt(st.nextToken());
		}
		int[][] dist = new int[numCustomers+2][numCustomers+2];
		for (int i=0; i<numCustomers+2; ++i)
			Arrays.fill(dist[i], UNKNOWN);
		boolean[] visited = new boolean[numCustomers+2];
		visited[WORK] = true;
		int minDistance = dfsVisit(dist, posArray, visited, numCustomers, 0, 0);
		return Integer.toString(minDistance);
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
