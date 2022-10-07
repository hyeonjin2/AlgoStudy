package com.day1007;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 구슬 탈출2
public class BaekJoon13460 {

	static int N, M, Ans;
	static char[][] board, copy;
	static int[] order;
	static boolean success, fail;
	static Point blue, red;

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
					blue = new Point(i, j);
				else if (board[i][j] == 'R')
					red = new Point(i, j);
			}
		}
//		print(board);
		// 구슬판을 기울일 순서 정하기 -> 중복순열
		System.out.println(red + " " + blue);
		order = new int[10];
		rollBall(board, 3);
//		perm(0);
	}

	private static void perm(int cnt) {
		if (cnt == 10) {
			System.out.println(Arrays.toString(order));
			Ans = -1;
			return;
		}
		for (int i = 0; i < 4; i++) {
			order[cnt] = i;
			// 구슬 굴려보기
			copy = new char[N][M];
			copy = board;
			rollBall(copy, i);
			// 파란 구슬이 구멍 위치에 있는지 확인 -> 가지치기
			if (fail) {
				perm(cnt);
			}
			if (success) {
				Ans = cnt + 1;
				return;
			}
			perm(cnt + 1);
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
		while (rx >= 1 && rx <= N - 1 && ry >= 1 && ry <= M - 1) {
			// 벽을 만나면 더 이상 못감.
			// 몇 칸을 굴러서 갔는지 체크 -> 파란 구슬과 같은 곳에 위치할 경우 우선순위를 결정하기 위해
			if (copy[rx][ry] == '#') {
				System.out.println("in");
				copy[red.x][red.y] = '.';
				red.x = rx - dx[dir];
				red.y = ry - dy[dir];
				copy[red.x][red.y] = 'R';
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
		while (bx >= 1 && bx < N - 1 && by >= 1 && by < M - 1) {
			// 벽을 만나면 더 이상 못감.
			if (copy[bx][by] == '#') {
				copy[blue.x][blue.y] = '.';
				blue.x = bx - dx[dir];
				blue.y = by - dy[dir];
				copy[blue.x][blue.y] = 'B';
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
		// 둘 다 탈출 가능한 경우
		if (rEsc && bEsc) {
			// 누가 먼저 탈출했는지 따지기
			// 빨간 구슬이 먼저 탈출한 경우
			if (rnt < bnt) {
				success = true;
			}
			// 파란 구슬이 먼저 탈출한 경우
			else {
				fail = true;
			}
		}
		// 파란 구슬만 탈출 가능한 경우
		else if (bEsc) {
			fail = true;
		}
		// 빨간 구슬만 탈출 가능한 경우
		else if (rEsc) {
			success = true;
		}
		print(copy);
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
