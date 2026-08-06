import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;

class Solution
{
	
    String solveInner(BufferedReader br) throws IOException {
    	int N, M;
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	Set<String> s1 = new HashSet<>(), s2 = new HashSet<>();
    	st = new StringTokenizer(br.readLine());
    	for (int i=0; i<N; ++i)
    		s1.add(st.nextToken());
    	st = new StringTokenizer(br.readLine());
    	for (int i=0; i<M; ++i)
    		s2.add(st.nextToken());
    	if (s1.retainAll(s2))
    		return Integer.toString(s1.size());
    	return "0";
    }
    
    void solveOuter() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine().trim());
        for(int test_case = 1; test_case <= T; test_case++)
            System.out.println(String.format("#%d %s", test_case, solveInner(br)));
    }
     
    public static void main(String args[]) throws Exception
    {
    	new Solution().solveOuter();
    }
}