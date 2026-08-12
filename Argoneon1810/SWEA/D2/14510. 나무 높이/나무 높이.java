import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	String solveInner(BufferedReader br) throws IOException {
		// 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int mArr[] = new int[n];
		int maxVal = Integer.MIN_VALUE;
		for (int i=0;i<n;++i) {
			mArr[i] = Integer.parseInt(st.nextToken());
			maxVal = Integer.max(maxVal, mArr[i]);
		}
		
		// 이게 왜 d2야
		int sum=0;
		int odds=0;
		for (int i=0;i<n;++i) {
			int diff = maxVal - mArr[i];
			if (diff%2!=0)
				++odds;
			sum+=diff;
		}
		if (sum==0)
			return Integer.toString(0);
		
		int minDays = Integer.MAX_VALUE;
		for (int cnt_one=odds; cnt_one<=sum; cnt_one+=2) {
			int cnt_two = (sum-cnt_one) / 2;
			int daysReqOne = 2*cnt_one-1;
			int daysReqTwo = 2*cnt_two;
			int maxDaysCurr = Integer.max(daysReqOne, daysReqTwo);
			if (maxDaysCurr > minDays)
				break;
			minDays = Integer.min(minDays, maxDaysCurr);
		}
		
		return Integer.toString(minDays);
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
