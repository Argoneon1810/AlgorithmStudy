import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solution {
	static final long FULL = (1L << 18) - 1;
	void swap(int arr[], int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	void reverse(int arr[], int fromInclusive, int toExclusive) {
		int N = toExclusive-fromInclusive;
		for (int i=0; i<N/2; ++i)
			swap(arr, fromInclusive + i, fromInclusive + N-1-i);
	}
	boolean next(int arr[]) {
		// 오른쪽에 가장 가까운, 순방향 오름차순인 구간의 왼쪽 인덱스 찾기
		int N = arr.length;
		int idx = Integer.MAX_VALUE;
		for (int i=N-1; i>0; --i) {
			if (arr[i] > arr[i-1]) {
				idx = i;
				break;
			}
		}
		// 못찾았으면 순열 끝
		if (idx == Integer.MAX_VALUE)
			return false;
		// 오른쪽에 가장 가까운, idx-1보다 큰 값의 인덱스 찾기
		int idx2 = Integer.MAX_VALUE;
		for (int i=N-1; i>=idx; --i) {
			if (arr[i] > arr[idx-1]) {
				idx2 = i;
				break;
			}
		}
		// 두 값 스왑
		swap(arr, idx2, idx-1);
		// idx 뒤쪽의 내림차순인 구간 역순으로
		reverse(arr, idx, N);
		return true;
	}
	int winCheck(int s[], int o[]) {
		int ss, so; ss=so=0;
		for(int i=0; i<9; ++i) {
			int res = Integer.compare(s[i], o[i]);
			if(res<0)
				so += s[i] + o[i];
			else
				ss += s[i] + o[i];
		}
		return Integer.compare(ss, so);
	}
	String solveInner(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		long mine = 0;
		int mineArr[] = new int[9];
		for(int i=0; i<9; ++i) {
			mineArr[i] = Integer.parseInt(st.nextToken());
			mine += (1L << (mineArr[i]-1));
		}
		long notMine = ~mine & FULL;
		int notMineArr[] = new int[9];
		int cnt = 0;
		for(int i=0; i<18; ++i) {
			if ((notMine & (1L << i))!=0)
				notMineArr[cnt++] = i+1;
		}
		Arrays.sort(notMineArr);
		int win, lose; win=lose=0;
		do {
			switch(winCheck(mineArr, notMineArr)) {
			case -1:
				++lose;
				break;
			case 1:
				++win;
				break;
			default:
				break;
			}
		} while(next(notMineArr));
		StringBuilder sb = new StringBuilder();
		return sb.append(win).append(' ').append(lose).toString();
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
	
	void permTest() {
		int arr[] = new int[] {1, 2, 3, 4};
		do {
			System.out.println(print(arr));
		} while(next(arr));
	}
	String print(int arr[]) {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (int i=0; i<arr.length; ++i) {
			if (i!=0)
				sb.append(", ");
			sb.append(arr[i]);
		}
		return sb.append(" ]").toString();
	}

	public static void main(String args[]) throws Exception {
		new Solution().solve();
//		new Solution().permTest();
	}
}
