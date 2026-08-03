import java.util.Scanner;

class Solution
{
	static String solve(Scanner sc) {
		int N = sc.nextInt();
		int M = sc.nextInt();
		int board[][] = new int[N][N];
		for (int y=0; y<N; ++y)
			for (int x=0; x<N; ++x)
				board[y][x] = sc.nextInt();
		int sum[][] = new int[N-M+1][N-M+1];
		int max = Integer.MIN_VALUE;
		for (int y=0; y<=N-M; ++y) {
			for (int x=0; x<=N-M; ++x) {
				for (int j=0; j<M; ++j)
					for (int i=0; i<M; ++i)
						sum[y][x] += board[y+j][x+i];
				max = Integer.max(max, sum[y][x]);
			}
		}
		return Integer.toString(max);
	}

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			System.out.println(String.format("#%d %s", test_case, solve(sc)));
		}
	}
}