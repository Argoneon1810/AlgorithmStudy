import java.util.Scanner;

class Solution
{
	static int arr[][];
	static {
		arr = new int[10][10];
		arr[0][0] = 1;
		int N = 10;
		for (int y=1; y<N; ++y)
			for (int x=0; x<=y; x++)
				arr[y][x] = (x>0 ? arr[y-1][x-1] : 0) + arr[y-1][x];
	}
	
	static String printPyramid(int arr[][], int N) {
		StringBuilder sb = new StringBuilder();
		int t;
		for (int y=0; y<N;++y) {
			for(int x=0; x<N; ++x) {
				t = arr[y][x];
				if (t == 0)
					break;
				sb.append(t);
				if (x<N-1)
					sb.append(' ');
			}
			if(y<N-1)
				sb.append('\n');
		}
		return sb.toString();
	}
	static String solve(Scanner sc) {
		return printPyramid(arr, sc.nextInt());
	}

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
			System.out.println(String.format("#%d\n%s", test_case, solve(sc)));
	}
}