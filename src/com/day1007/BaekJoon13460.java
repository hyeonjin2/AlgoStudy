package com.day1007;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 구슬 탈출2
public class BaekJoon13460 {

	static class Ball {
		int x, y;
		int cnt;

		public Ball(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static int N, M, Ans;
	static char[][] board, copy;
	static boolean success, fail;
	static Ball blue, red;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j);
				if (board[i][j] == 'B')
					blue = new Ball(i, j, 0);
				else if (board[i][j] == 'R')
					red = new Ball(i, j, 0);
			}
		}
//		print(board);
		// 구슬판을 기울일 순서 정하기 -> 중복순열
		rollBall(board, 3);
		perm(0, -1);
		System.out.println(Ans);
	}

	private static void perm(int cnt, int temp) {
		if (cnt == 10) {
			Ans = -1;
			System.out.println(Ans);
			System.exit(0);
		}
		for (int i = 0; i < 4; i++) {
			if (temp == i)
				continue;
			// 구슬 굴려보기
			copy = new char[N][M];
			copy = board;
			success = false;
			fail = false;
			rollBall(copy, i);
			System.out.println(cnt);
			print(copy);
			// 파란 구슬이 구멍 위치에 있는지 확인 -> 가지치기
			if (fail) {
				perm(cnt + 1, i);
			}
			// 빨간색 구슬이 들어간다면 -> 끝내기
			else if (success) {
				Ans = cnt + 1;
				return;
			}
			// 위의 상황이 아니라면 다음 순서 뽑으러 가기
			else {
				board = copy;
				perm(cnt + 1, i);
			}
		}
	}

	// 상,하,좌,우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	// 입력으로 들어온 방향으로 구슬 굴리기
	private static void rollBall(char[][] copy, int dir) {
		int rx = red.x + dx[dir];
		int ry = red.y + dy[dir];
		int rnt = 1;
		boolean rEsc = false;
		boolean bEsc = false;
		// 빨간 구슬 굴리기
		while (rx >= 0 && rx < N && ry >= 0 && ry < M) {
			// 벽을 만나면 더 이상 못감.
			// 몇 칸을 굴러서 갔는지 체크 -> 파란 구슬과 같은 곳에 위치할 경우 우선순위를 결정하기 위해
			if (copy[rx][ry] == '#') {
				copy[red.x][red.y] = '.';
				red.x = rx - dx[dir];
				red.y = ry - dy[dir];
				break;
			}
			// 구멍을 만나면 일단 빠져나오기
			else if (copy[rx][ry] == 'O') {
				rEsc = true;
				break;
			}
			rx += dx[dir];
			ry += dy[dir];
			rnt++;
		}
		// 파란 구슬 굴리기
		int bx = blue.x + dx[dir];
		int by = blue.y + dy[dir];
		int bnt = 1;
		while (bx >= 0 && bx < N && by >= 0 && by < M) {
			// 벽을 만나면 더 이상 못감.
			if (copy[bx][by] == '#') {
				copy[blue.x][blue.y] = '.';
				blue.x = bx - dx[dir];
				blue.y = by - dy[dir];
				break;
			}
			// 구멍을 만나면 일단 빠져나오기
			else if (copy[bx][by] == 'O') {
				bEsc = true;
				break;
			}
			bx += dx[dir];
			by += dy[dir];
			bnt++;
		}
		// 둘의 좌표가 같은 경우
		if (red.x == blue.x && red.y == blue.y) {
			if (rnt < bnt) {
				blue.x -= dx[dir];
				blue.y -= dy[dir];
			} else {
				red.x -= dx[dir];
				red.y -= dy[dir];
			}
		}
		copy[red.x][red.y] = 'R';
		copy[blue.x][blue.y] = 'B';
		// 둘 다 탈출 가능한 경우
		if (rEsc && bEsc) {
			fail = true;
		}
		// 파란 구슬만 탈출 가능한 경우
		else if (bEsc) {
			fail = true;
		}
		// 빨간 구슬만 탈출 가능한 경우
		else if (rEsc) {
			success = true;
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
