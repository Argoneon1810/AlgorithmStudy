import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            br.readLine();
            String s = br.readLine();
            int mid = s.length() / 2;

            String sHead = s.substring(0, mid);
            String sTail = s.substring(mid);

            sb.append("#").append(tc).append(" ");

            if (sHead.equals(sTail)) {
                sb.append("Yes");
            } else {
                sb.append("No");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
