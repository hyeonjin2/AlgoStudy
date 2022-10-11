package com.day1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 구슬 탈출2
public class BaekJoon13460_2 {

	static class Ball {
		char no;
		int x, y;
		int cnt;
		boolean flag;

		public Ball(char no, int x, int y, int cnt, boolean flag) {
			this.no = no;
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.flag = flag;
		}
	}

	static int N, M, Ans;
	static char[][] board, copy;
	static boolean success, fail;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		Ball blue = null;
		Ball red = null;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j);
				if (board[i][j] == 'B')
					blue = new Ball('B', i, j, 0, false);
				else if (board[i][j] == 'R')
					red = new Ball('R', i, j, 0, false);
			}
		}
//		print(board);
		bfs(blue, red);
		System.out.println(Ans);
	}

	// 상,하,좌,우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void bfs(Ball red, Ball blue) {
		Queue<Ball> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[2][N][M];
		queue.offer(red);
		queue.offer(blue);
		visited[0][red.x][red.y] = true;
		visited[1][blue.x][blue.y] = true;
		boolean flag = false;
		copy = board;
		while (!queue.isEmpty()) {
			Ball cur = queue.poll();
			char color = cur.no;
			int ind = 0;
			if (color == 'B')
				ind = 1;
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				boolean goal = false;
				while (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[ind][nx][ny]) {
					if (board[nx][ny] == '#') {
						copy[cur.x][cur.y] = '.';
						nx = nx - dx[d];
						ny = ny - dy[d];
						visited[ind][nx][ny] = true;
						queue.offer(new Ball(color, nx, ny, cur.cnt + 1, false));
						copy[nx][ny] = color;
						break;
					}
					// 구멍을 만나면 일단 빠져나오기
					else if (board[nx][ny] == 'O') {
						goal = true;
						queue.offer(new Ball(color, nx, ny, cur.cnt + 1, true));
						if (cur.no == 'R') {
							Ans = cur.cnt;
						} else {
							fail = true;
							Ans = -1;
						}
						return;
					}
					// 파란 구슬이나 빨간 구슬을 만나면 일단 멈추기->다른 구슬 먼저 굴려보고 오기
					else if (copy[nx][ny] == 'R' || copy[nx][ny] == 'B') {
						copy[cur.x][cur.y] = '.';
						nx = nx - dx[d];
						ny = ny - dy[d];
						copy[nx][ny] = color;
						visited[ind][nx][ny] = true;
						queue.offer(new Ball(color, nx, ny, cur.cnt, false));
						break;
					}
					nx += dx[d];
					ny += dy[d];
				}
				print(board);
			}
			board = copy;
		}
	}

	private static void print(char[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
