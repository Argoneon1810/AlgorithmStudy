import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());

            List<Integer> listA = new ArrayList<>();
            List<Integer> listB = new ArrayList<>();

            sb.append("#").append(tc);

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                listA.add(Integer.parseInt(st.nextToken()));
                listB.add(Integer.parseInt(st.nextToken()));
            }

            int P = Integer.parseInt(br.readLine());

            for (int i = 0; i < P; i++) {
                int count = 0;
                int num = Integer.parseInt(br.readLine());

                for (int j = 0; j < N; j++) {
                    if (listA.get(j) <= num && num <= listB.get(j)) {
                        count++;
                    }
                }

                sb.append(" ").append(count);
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }
}
