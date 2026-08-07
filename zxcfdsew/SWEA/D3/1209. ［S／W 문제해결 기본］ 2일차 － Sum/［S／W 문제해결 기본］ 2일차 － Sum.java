import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int SIZE = 100;

        for (int tc = 0; tc < 10; tc++) {
            int testCaseNum = Integer.parseInt(br.readLine());

            int[][] board = new int[SIZE][SIZE];
            int[] rowSum = new int[SIZE];  // 행에 해당하는 최댓값을 담을 배열
            int[] colSum = new int[SIZE];  // 열에 해당하는 최댓값을 담을 배열
            int[] crossSum = new int[2];  // "/"모양이랑 "\"모양 2개
            int maxValue = Integer.MIN_VALUE;  // 최댓값을 담은 변수

            // 입력값 board에 저장
            for (int i = 0; i < SIZE; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < SIZE; j++) {
                    board[i][j] = Integer.parseInt(line[j]);
                }
            }

            // 가로줄 + 세로줄 구하기
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    rowSum[i] += board[i][j];
                    colSum[i] += board[j][i];
                }
            }

            // 대각선 구하기
            for (int i = 0; i < SIZE; i++) {
                crossSum[0] += board[i][i];
                crossSum[1] += board[SIZE - i - 1][i];
            }

            // 결과값중 최댓값 구하기
            for (int num : rowSum) {
                maxValue = Math.max(num, maxValue);
            }
            for (int num : colSum) {
                maxValue = Math.max(num, maxValue);
            }
            for (int num : crossSum) {
                maxValue = Math.max(num, maxValue);
            }

            System.out.println("#" + testCaseNum + " " + maxValue);
        }
    }
}