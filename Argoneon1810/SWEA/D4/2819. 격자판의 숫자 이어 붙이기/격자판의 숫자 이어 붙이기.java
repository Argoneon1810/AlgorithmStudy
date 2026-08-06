import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

class Solution
{
	static final int DN;
	static final int deltaX[];
	static final int deltaY[];
	
	final int N;
	final int DEPTH;
	final int MAX_PATHS;
	
	final byte board[];
	final int results[];
	int resultCount;
	
    static {
    	DN = 4;
    	deltaX = new int[] {0, 0, -1, 1};
    	deltaY = new int[] {-1, 1, 0, 0};
    }
    
    {
    	N = 4;
    	DEPTH = 7;
    	MAX_PATHS = (N*N) * ((int) Math.pow(DN, DEPTH-1)); // 4x4 board * 4 deltas ^ 6 sub-depths 
    	
    	board = new byte[N*N];
    	results = new int[MAX_PATHS];
    	resultCount = 0;
    }
    
    byte[] getBoard(BufferedReader br) throws IOException {
        for (int j=0; j<N; ++j) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i=0; i<N; ++i)
                board[j*N+i] = Byte.parseByte(st.nextToken());
        }
        return board;
    }
    
    void solveRecursive(int pos, int depth, int value) {
    	value = value*10+board[pos];
    	if (depth >= DEPTH-1) {
    		results[resultCount++] = value;
    		return;
    	}
    	int prevY = pos / N;
    	int prevX = pos % N;
    	for (int i=0; i<DN; ++i) {
    		int currY = prevY + deltaY[i];
    		int currX = prevX + deltaX[i];
    		if (currY >= N || currX >= N || currY < 0 || currX < 0)
    			continue;
    		solveRecursive(currY*N+currX, depth+1, value);
    	}
    }
    
    String solveInner(BufferedReader br) throws IOException {
    	// 개선시도 v2
    	// 1. 순회순서 문제로 인한 캐시 미스 문제를 겪어 보았으니, board에도 직렬화를 적용해보자
    	// 2. 수열 그 자체를 인덱스로 하는 배열은 지나치게 sparse하다.
    	//	  메모리에서도, 순회길이에서도 전부 열등하다.
    	// 3. 애초에 100_000_000 길이의 배열 그 자체가 유연성이 없다.
    	//	  문제 내용에 딱 맞춘 해킹 그 자체인데, 아무 유익도 얻지 못했다.
    	
    	// 계획 3번까지 + 초기 상태 형성 18분 53초
        getBoard(br);
        
        // 재귀로 해보자.
        // y*N+x번째 칸을 여러번 방문할 것이지만, 방문했을 때의 depth가 무엇인지에 따라 반환값이 결정론적이라는 말은
        // 그냥 재귀해서 deepest node에 도달했을 때 자기 값을 반환시키고 부모 노드에서 자식 노드의 값을 자기 뒤에 append 시키겠다는 말과 같다
        // 즉 그냥 완전탐색 하는 것과 다를게 없다
        
    	resultCount = 0;
    	for(int i=0; i<N*N; ++i)
    		solveRecursive(i, 0, 0);
    	
    	// 중복체크는 어떻게 할까
    	// Set에 배열 전부 넣기 vs 정렬하고 값이 달라질때만 카운터 올리기
    	
//    	// Set에 배열 전부 넣기
//    	// 이거 안되네
//    	Set<Integer> s = new HashSet<Integer>();
//    	Collections.addAll(s, Arrays.copyOf(results, resultCount));
//    	return Integer.toString(s.size());
    	
    	// 정렬하고 값이 달라질때만 카운터 올리기
    	Arrays.sort(results, 0, resultCount);
    	int distinct = 0;
        for (int i = 0; i < resultCount; ++i)
            if (i == 0 || results[i] != results[i - 1])
                ++distinct;
        return Integer.toString(distinct);
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