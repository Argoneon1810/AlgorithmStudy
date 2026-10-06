import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static boolean isDirty = false;
	public static void main(String...args) throws IOException {
		BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T; ++tc) {
			sb
				.append('#')
				.append(tc)
				.append(' ')
				.append(solve(br))
				.append('\n');
			isDirty = true;
		}
		System.out.print(sb.toString());
	}

	static String solve(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] X = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; ++i)
			X[i] = Integer.parseInt(st.nextToken());
		int[] Y = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; ++i)
			Y[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		double E = Double.parseDouble(st.nextToken());
		boolean[] treeCovers = new boolean[N];
		long[] minDistances = new long[N];
		for(int i=0; i<N; ++i)
			minDistances[i] = Long.MAX_VALUE;
		int current = 0;
		treeCovers[0] = true;
		minDistances[0] = 0;
		while(!allcover(treeCovers)) {
			int nextMinimumIdx = -1;
			for(int i=0; i<N; ++i) {
				if (treeCovers[i])
					continue;
				long lastMin = minDistances[i];
				long newDist = SQL2(X, Y, current, i);
				if(lastMin > newDist)
					minDistances[i] = newDist;
				if (nextMinimumIdx == -1 || minDistances[nextMinimumIdx] > minDistances[i])
					nextMinimumIdx = i;
			}
			if (nextMinimumIdx != -1) {
				current = nextMinimumIdx;
				treeCovers[nextMinimumIdx] = true;
			}
		}
		return String.format("%d", Math.round(E*sum(minDistances)));
	}

	static boolean allcover(boolean[] arr) {
		for(int i=0; i<arr.length; ++i)
			if(!arr[i])
				return false;
		return true;
	}

	static long SQL2(int[] X, int[] Y, int r, int c) {
		long dx = X[c] - X[r];
		long dy = Y[c] - Y[r];
		return (dx*dx) + (dy*dy);
	}

	static long sum(long[] distances) {
		long sum = 0;
		for(int i=0; i<distances.length; ++i)
			sum += distances[i];
		return sum;
	}
}
