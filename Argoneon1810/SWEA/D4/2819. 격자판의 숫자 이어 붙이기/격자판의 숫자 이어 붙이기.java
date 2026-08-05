import java.io.BufferedReader;
// import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

class Solution
{
	static final int deltasXY[][] = {
			{0, -1},
			{0, 1},
			{-1, 0},
			{1, 0}
	};
	static String solve(BufferedReader br) throws IOException {
		// 임의의 위치에서 시작해서 6번의 이동을 통해 구성 가능한 모든 7자리 숫자 조합 찾기 
		// 4x4라서 보드가 크진 않다
		// 단 한번 방문한 칸을 재방문 해도 된다는 조건이 있어 재귀로 풀면 재귀 깊이 문제가 발생
		// 어떤 칸은 자신이 N번째로 방문되었을 때 뱉을 수 있는 문자열이 동일하다. = 메모리제이션 가능
		// 자기 자신을 기준으로 한 칸 이동으로 갈 수 있는 블럭 = 4방향 블럭
		// 이동하지 않아도 되는가? X
		// T-1스텝의 4방향으로부터 숫자 조합을 받고 그 다음 내 숫자 붙이기.
		// - "0으로 시작하는 0102001과 같은 수를 만들 수도 있다"라고 했으니 내 숫자는 다른 숫자 앞에 붙는다
		// - "0으로 시작하는 값"이 나와야 한다는 거니까 문자열로 관리 = 4x4(격자)x7(깊이)x4(방향) 정도면 그냥 String Concat 하자.
		// T=7일 때의 모든 셀의 값을 출력
		// 중복값이 있으면?
		// - 모든 셀이 Set을 가지고 있게 하자. 로컬에서 중복이 있으면 미리 걸러질거고, 최종 4x4도 Set에 넣고 빼면 된다.
		// 계획 10분
		final int N = 4;
		byte board[][] = new byte[N][N];
		for (int j=0; j<N; ++j) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; ++i)
				board[j][i] = Byte.parseByte(st.nextToken());
		}
		final int D = 7; // depth
		List<ArrayList<HashSet<String>>> prev = null, curr = null;
		// 한번 연산하고 지나갔다면 그 전 단계는 불필요하므로 prev와 curr만 유지.
		// 마지막에 curr만 읽으면 됨
		for (int o=0; o < D; ++o) {
			curr = new ArrayList<>();
			for (int j=0; j<N; ++j) {
				ArrayList<HashSet<String>> outer = new ArrayList<>();
				for (int i=0; i<N; ++i) {
					HashSet<String> inner = new HashSet<>();
					// body가 여기에. 여기까지 37분
					if (o==0)
						inner.add(Integer.toString(board[j][i]));
					else {
						for(int d=0; d<4; ++d) {
							int deltaXY[] = deltasXY[d];
							int tx, ty;
							tx = i+deltaXY[0];
							ty = j+deltaXY[1];
							if (tx < 0 || ty < 0 || tx >= N || ty >= N)
								continue;
							HashSet<String> prevs = prev.get(ty).get(tx);
							for(String perm : prevs)
								inner.add(Integer.toString(board[j][i]) + perm);
						}
					}
					// body 작성 끝. 여기까지 45분
					outer.add(inner);
				}
				curr.add(outer);
			}
			prev = curr;
		}
		Set<String> lastCheck = new HashSet<>();
		for (int j=0; j<N; ++j)
			for (int i=0; i<N; ++i)
				lastCheck.addAll(curr.get(j).get(i));
		return Integer.toString(lastCheck.size());
		// 예시 input 기준 23 나오는 것 확인. 여기까지 48분. 
	}
	
	public static void main(String args[]) throws Exception
	{
		// System.setIn(new FileInputStream("./src/com/swea/q2819/res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; test_case++)
			System.out.println(String.format("#%d %s", test_case, solve(br)));
	}
}
