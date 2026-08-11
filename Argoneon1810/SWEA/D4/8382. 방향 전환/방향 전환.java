import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static final int deltasXY[][] = {
			{0, -1},
			{0, 1},
			{-1, 0},
			{1, 0}
	};
	static int getDelta(int vx, int vy, boolean mustGoVertically) {
		if (mustGoVertically) {
			if (vy < 0)
				return 0;
			return 1;
		}
		else {
			if (vx < 0)
				return 2;
			return 3;
		}
	}
	int move(int cx, int cy, int dx, int dy, boolean mustGoVertically) {
		int toReturn = 0;
		while(cx != dx || cy != dy) {
			toReturn++;
			int vx = dx - cx;
			int vy = dy - cy;
			int d = getDelta(vx, vy, mustGoVertically);
			cx = cx + deltasXY[d][0];
			cy = cy + deltasXY[d][1];
			mustGoVertically = !mustGoVertically;
		}
		return toReturn;
	}
	String solveInner(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int currX = Integer.parseInt(st.nextToken());
		int currY = Integer.parseInt(st.nextToken());
		int destX = Integer.parseInt(st.nextToken());
		int destY = Integer.parseInt(st.nextToken());
		int try1 = move(currX, currY, destX, destY, true);
		int try2 = move(currX, currY, destX, destY, false);
		return Integer.toString(try1<try2 ? try1 : try2);
	}
	
	void solve() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; test_case++)
			System.out.println(String.format("#%d %s", test_case, solveInner(br)));
	}
	
	public static void main(String args[]) throws Exception
	{	
		new Solution().solve();
	}
}