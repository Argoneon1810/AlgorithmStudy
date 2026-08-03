import java.util.Scanner;

class Solution
{
	static String solve(Scanner sc) {
		int N = 100;
		int horiMax = Integer.MIN_VALUE;
		int c, t;
		int verti[] = new int[N];
		int diag[] = new int[2];
		for(int y = 0; y < N; ++y) {
			t = 0;
			for (int x = 0; x < N; ++x) {
				c = sc.nextInt();
				t+=c;
				verti[x] += c;
				if (y == x) // confirmed
					diag[0] += c;
				if (y == ((N - 1) - x)) // confirmed
					diag[1] += c;
			}
			horiMax = Integer.max(horiMax, t);
		}
		int realMax = horiMax; 
		for (int i = 0; i < N; ++i)
			realMax = Integer.max(realMax, verti[i]);
		for (int i = 0; i < 2; ++i)
			realMax = Integer.max(realMax, diag[i]);
		return Integer.toString(realMax);
	}
	
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++)
			System.out.println(String.format("#%d %s", sc.nextInt(), solve(sc)));
	}
}