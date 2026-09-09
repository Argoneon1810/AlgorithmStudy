import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static final String TANKS = "<^>v";
	static final String WALLS = "*#";
	
	static final char GROUND = '.';
	static final char RIVER = '-';
	
	static final char SHOOT = 'S';
	
	static final char MOVE_LEFT = 'L';
	static final char MOVE_UP = 'U';
	static final char MOVE_RIGHT = 'R';
	static final char MOVE_DOWN = 'D';
	
	static final int MOVE_LEFT_I = 0;
	static final int MOVE_UP_I = 1;
	static final int MOVE_RIGHT_I = 2;
	static final int MOVE_DOWN_I = 3;
	
	static final int[][] DELTAS = {
		    {-1,  0}, // <
		    { 0, -1}, // ^
		    { 1,  0}, // >
		    { 0,  1}  // v
		};
	static final char[][] D2T = {
			{' ', '^', ' '},
			{'<', ' ', '>'},
			{' ', 'v', ' '},
		};
	static char getD2T(int[] deltas) {
		int x= deltas[0];
		int y= deltas[1];
		return D2T[y+1][x+1];
	}
	static final int[] RETURN_BUFFER = {0,0};
	
	String printMap(char[][] map, int H, int W) {
		StringBuilder sb = new StringBuilder();
		for (int y=0; y<H; ++y) {
			for (int x=0; x<W; ++x)
				sb.append(map[y][x]);
			if (y<H-1) sb.append('\n');
		}
		return sb.toString();
	}
	
	boolean tryGetPlayer(char[][] map, int[][] player, int x, int y) {
		char pc = map[y][x];
		int idx = TANKS.indexOf(pc);
		if (idx==-1) return false;
		int[] dt = DELTAS[idx];
		player[0] = new int[] {x, y};
		player[1] = new int[] {dt[0], dt[1]};
		removePlayerFromMap(map, player);
		return true;
	}
	
	void removePlayerFromMap(char[][] map, int[][] player) {
		map[player[0][1]][player[0][0]] = GROUND;
	}
	
	void placePlayerOnMap(char[][] map, int[][] player) {
		map[player[0][1]][player[0][0]] = getD2T(player[1]);
	}
	
	int[] peekNext(int[][] player) {
		RETURN_BUFFER[0] = player[0][0] + player[1][0];
		RETURN_BUFFER[1] = player[0][1] + player[1][1];
		return RETURN_BUFFER;
	}
	
	void faceTowards(int[][] player, int[] dt) {
		player[1][0] = dt[0];
		player[1][1] = dt[1];
	}
	
	void moveNext(int[][] player) {
		player[0][0] += player[1][0];
		player[0][1] += player[1][1];
	}
	
	boolean isOffboard(int H, int W, int x, int y) {
		return y >= H || y < 0 || x >= W || x < 0;
	}
	
	boolean isRiver(char[][] map, int x, int y) {
		return map[y][x] == RIVER;
	}
	
	int tryBreak(char[][] map, int x, int y) {
		char pBreakable = map[y][x];
		switch(WALLS.indexOf(pBreakable)) {
		default:
			return -1;
		case 0:
			map[y][x] = GROUND;
			return 0;
		case 1:
			return 1;
		}
	}
	
	void tryMove(char[][] map, int[][] player, int H, int W, int move_idx) {
		faceTowards(player, DELTAS[move_idx]);
		int[] peek = peekNext(player);
		int px = peek[0];
		int py = peek[1];
		if(isOffboard(H, W, px, py)) return;
		if(isRiver(map, px, py)) return;
		if (map[py][px] == GROUND)
			moveNext(player);
	}
	
	void dfs(char[][] map, int[][] player, int[] buffer, int H, int W, char command) {
		switch(command) {
		case SHOOT:
			int x = buffer[0], y = buffer[1];
			if(isOffboard(H, W, x, y)) return;
			int res = tryBreak(map, x, y);
			if(res>=0) return;
			buffer[0] += player[1][0];
			buffer[1] += player[1][1];
			dfs(map, player, buffer, H, W, command);
			break;
		case MOVE_UP:
			tryMove(map, player, H, W, MOVE_UP_I);
			break;
		case MOVE_DOWN:
			tryMove(map, player, H, W, MOVE_DOWN_I);
			break;
		case MOVE_LEFT:
			tryMove(map, player, H, W, MOVE_LEFT_I);
			break;
		case MOVE_RIGHT:
			tryMove(map, player, H, W, MOVE_RIGHT_I);
			break;
		}
	}
	
	String solveInner(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken()), W = Integer.parseInt(st.nextToken());
		char[][] map = new char[H][W];
		int[][] player = new int[2][2]; // 0 pos 1 dir 0 x 1 y
		for (int y=0; y<H; ++y) {
			map[y] = new StringTokenizer(br.readLine())
							.nextToken().toCharArray();
			for (int x=0; x<W; ++x)
				if (tryGetPlayer(map, player, x, y))
					break;
		}
		int C = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		char[] commands = new StringTokenizer(br.readLine()).nextToken().toCharArray();
		for (char command : commands) {
			dfs(map, player, peekNext(player), H, W, command);
		}
		placePlayerOnMap(map, player);
		return printMap(map, H, W);
	}

	void solve() throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++)
			sb.append('#').append(test_case)
					.append(' ').append(solveInner(br))
					.append('\n');
		System.out.print(sb);
	}

	public static void main(String args[]) throws Exception {
		new Solution().solve();
	}
}
