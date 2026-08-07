import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution
{
    String solveInner(BufferedReader br) throws IOException {
    	br.readLine(); // 문자열 길이 받아도 아무 의미 없으니 그냥 flush
    	String input = br.readLine();
    	int len = input.length();
    	int halfLen = len / 2;
    	int remainder = len % 2;
    	boolean result = true;
    	if (remainder > 0)
    		result = false;
    	else {
    		String former = input.substring(0, halfLen);
    		String latter = input.substring(halfLen);
    		if (!former.equals(latter))
    			result = false;
    	}
        return result ? "Yes" : "No";
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