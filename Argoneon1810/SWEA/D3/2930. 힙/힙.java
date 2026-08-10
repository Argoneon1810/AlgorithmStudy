import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static final int N = 100_000;
	static int argmax(int arr[], int a, int b) {
		return arr[a] > arr[b] ? a : b;
    }
	
	int arr[];
	int nextIndex;
	{
		arr = new int[N];
		nextIndex = 0;
	}
	void swap(int indexA, int indexB) {
		int temp = arr[indexA];
		arr[indexA] = arr[indexB];
		arr[indexB] = temp;
	}
	void insertMaxHeapInner(int currentIndex) {
		int parentIndex = currentIndex % 2 == 0 ? currentIndex/2-1 : currentIndex/2;
		if (parentIndex != -1 && arr[parentIndex] < arr[currentIndex]) {
			swap(currentIndex, parentIndex);
			insertMaxHeapInner(parentIndex);
		}
	}
	void insertMaxHeap(int newval) {
		arr[nextIndex] = newval;
		insertMaxHeapInner(nextIndex++);
	}
	void popMaxHeapInner(int currentIndex) {
		int childIndexA = currentIndex*2+1;
		int childIndexB = (currentIndex+1)*2;
		if (childIndexA >= nextIndex) {
			arr[currentIndex] = 0;
			return;
		}
		else if (childIndexB >= nextIndex) {
			if (arr[currentIndex] < arr[childIndexA])
				swap(currentIndex, childIndexA);
			return;
		}
		int maxIndex = Solution.argmax(arr, childIndexA, childIndexB);
		if (arr[currentIndex] < arr[maxIndex])
			swap(currentIndex, maxIndex);
		popMaxHeapInner(maxIndex);
    }
	int popMaxHeap() {
		if (nextIndex == 0)
			return -1;
		int toReturn = arr[0];
		arr[0] = arr[nextIndex-1];
		popMaxHeapInner(0);
		nextIndex--;
		return toReturn;
	}
	String solveInner(BufferedReader br) throws IOException {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		for (int i=0; i<n; ++i) {
			st = new StringTokenizer(br.readLine());
			switch (Integer.parseInt(st.nextToken())) {
			default:
			case 1:
				insertMaxHeap(Integer.parseInt(st.nextToken()));
				break;
			case 2:
				sb.append(popMaxHeap()).append(' ');
				break;
			}
		}
		if (sb.length()==0)
			sb.append(' ');
		return sb.substring(0, sb.length()-1);
	}
	
	void solve() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; test_case++) {
			this.nextIndex = 0;
			System.out.println(String.format("#%d %s", test_case, solveInner(br)));
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		new Solution().solve();
	}
}
