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
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            sb.append("#").append(tc);

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());

                if (cmd == 1) {
                    pq.add(Integer.parseInt(st.nextToken()));
                } else {
                    if (pq.isEmpty()) {
                        sb.append(" ").append(-1);
                    } else {
                        sb.append(" ").append(pq.poll());
                    }
                }
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
