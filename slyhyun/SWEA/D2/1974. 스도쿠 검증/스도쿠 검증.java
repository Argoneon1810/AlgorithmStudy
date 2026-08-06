import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int[][] arr = new int[9][9];
            int answer = 1;

            for (int i = 0; i < 9; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 9; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (!row(arr) || !col(arr) || !square(arr)) {
                answer = 0;
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static boolean row(int[][] arr) {
        List<Integer> temp = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (temp.contains(arr[i][j])) {
                    return false;
                }
                else{
                    temp.add(arr[i][j]);
                }
            }
            temp.clear();
        }
        return true;
    }

    public static boolean col(int[][] arr) {
        List<Integer> temp = new ArrayList<>();

        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (temp.contains(arr[i][j])) {
                    return false;
                }
                else{
                    temp.add(arr[i][j]);
                }
            }
            temp.clear();
        }
        return true;
    }

    public static boolean square(int[][] arr) {
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (temp.contains(arr[i * 3 + k][j * 3 + l])) {
                            return false;
                        }
                        else {
                            temp.add(arr[i * 3 + k][j * 3 + l]);
                        }
                    }
                }
                temp.clear();
            }
        }
        return true;
    }
}
