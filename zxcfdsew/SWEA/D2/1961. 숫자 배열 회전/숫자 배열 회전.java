import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            sc.nextLine();

            int[][] board = new int[N][N];

            for (int row = 0; row < N; row++) {
                String[] line = sc.nextLine().split(" ");
                for (int col = 0; col < N; col++) {
                    board[row][col] = Integer.parseInt(line[col]);
                }
            }

            int[][] board90 = new int[N][N];
            int[][] board180 = new int[N][N];
            int[][] board270 = new int[N][N];

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    board90[row][col]  = board[N - 1 - col][row];
                    board180[row][col] = board[N - 1 - row][N - 1 - col];
                    board270[row][col] = board[col][N - 1 - row];
                }
            }

            StringBuilder answer = new StringBuilder();
            answer.append("#" + test_case + "\n");

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    answer.append(board90[row][col]);
                }
                answer.append(" ");
                for (int col = 0; col < N; col++) {
                    answer.append(board180[row][col]);
                }
                answer.append(" ");
                for (int col = 0; col < N; col++) {
                    answer.append(board270[row][col]);
                }
                answer.append("\n");
            }

            System.out.print(answer);
        }
	}
}