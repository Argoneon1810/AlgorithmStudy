import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int count = 0;

            Set<String> sSet1 = new HashSet<>();
            Set<String> sSet2 = new HashSet<>();

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                sSet1.add(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < M; i++) {
                sSet2.add(st.nextToken());
            }

            for (String s : sSet1) {
                if (sSet2.contains(s)) {
                    count++;
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");

        }

        System.out.println(sb);
    }
}
