package com.day1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 낚시왕
public class BaekJoon17143_arr {

	static class Shark {
		int r, c, speed, dir, size;

		public Shark() {
		}

		public Shark(int r, int c, int speed, int dir, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}

	static int N, M, K, Ans;
	static Shark[][] board;

	// 상하좌우 1->2 2->1
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new Shark[N + 1][M + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(board[i], new Shark());
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			board[r][c] = new Shark(r, c, s, d, z);
		}
		// 낚시왕이 이동한다.
		for (int col = 1; col <= M; col++) { // 낚시왕의 위치
		// 낚시왕이 해당 열에 있는 상어 중 가장 가까운 상어를 한마리 잡는다.
		catchShark(col);
		// 상어가 이동한다.
		moveShark();
		}
		System.out.println(Ans);
	}

	// 낚시왕이 상어를 잡는 메소드
	private static void catchShark(int col) {
		// 해당 열에 있는 상어 중 가장 가까운 상어 한마리를 잡는다.
		int row = 0; // 잡은 상어가 위치한 row
		while (row < N && board[row][col].size == 0) {
			row++;
		}
		// 맵에서 상어를 삭제하고
		Ans += board[row][col].size;
		// 보드에서도 지운다.
		board[row][col] = new Shark();
	}

	// 상어가 이동하는 메소드
	private static void moveShark() {
		Shark[][] copy = new Shark[N + 1][M + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(copy[i], new Shark());
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				Shark cur = board[i][j];
				if (cur.size == 0)
					continue;
				int dir = cur.dir;
				int speed = cur.speed;
				int size = cur.size;
				int nr = cur.r + dr[dir] * speed;
				int nc = cur.c + dc[dir] * speed;
				while (nr <= 0 || nr > N || nc <= 0 || nc > M) {
					if (nr <= 0) {
						nr = 1 + 1 - nr;
						dir = changeDir(dir);
					}
					if (nr > N) {
						nr = 2 * N - nr;
						dir = changeDir(dir);
					}
					if (nc <= 0) {
						nc = 1 + 1 - nc;
						dir = changeDir(dir);
					}
					if (nc > M) {
						nc = 2 * M - nc;
						dir = changeDir(dir);
					}
				}
				// 이미 상어가 있는 경우
				if (size > copy[nr][nc].size) {
					copy[nr][nc] = new Shark(nr, nc, speed, dir, size);
				}
//				System.out.println(size);
//				print(copy);
			}
		}
		board = copy;
	}

	// 상어가 방향을 바꾸는 메소드
	private static int changeDir(int dir) {
		if (dir == 1) {
			return 2;
		} else if (dir == 2) {
			return 1;
		} else if (dir == 3) {
			return 4;
		} else if (dir == 4) {
			return 3;
		}
		return 0;
	}

	private static void print(Shark[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sb.append(board[i][j].size + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
