package com.day1004;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 벽돌 깨기
public class SW5652_2 {

	static class Point {
		int r, c;
		int cnt;

		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int R, N, M, Ans;
	static int[] orders;
	static int[][] map, game;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			// 입력받기
			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
//			print(map);
			// 중복순열로 구슬 발사할 위치 순서 정하기
			Ans = Integer.MAX_VALUE;
			orders = new int[R];
			game = new int[N][M];
			perm(map, 0);

			sb.append("#").append(tc).append(" ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	// 중복순열로 구슬 발사할 위치 순서 정하기
	private static void perm(int[][] map, int cnt) {
		if (cnt == R) {
			// 남은 개수 구하기
			int result = calc(map);
			Ans = Math.min(Ans, result);
			return;
		}

		int[][] copy = new int[N][M];
		for (int col = 0; col < M; col++) {
			int row = 0;
			while (row < N && map[row][col] == 0)
				++row;
			if (row == N) {
				perm(map, cnt + 1);
			} else {
				copy(copy, map);
				shoot(copy, row, col);
				down(copy);
				perm(copy, cnt + 1);
			}
		}
	}

	// 남은 개수 구하기
	private static int calc(int[][] copy) {
		int cnt = 0;
		// 남은 벽돌의 수 계산하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] != 0)
					cnt++;
			}
		}
		return cnt;
	}

	// 벽돌들이 바닥으로 떨어진다.
	private static void down(int[][] copy) {
		// 한 열에 있는 벽돌들을 stack에 담고 아래부터 채워주기
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < M; i++) {
			// 한 열에 있는 벽돌 stack에 넣기
			for (int j = 0; j < N; j++) {
				if (copy[j][i] != 0) {
					stack.push(copy[j][i]);
					copy[j][i] = 0;
				}
			}
			// 아래서부터 stack에서 빼서 넣기
			for (int j = N - 1; j >= 0; j--) {
				if (!stack.isEmpty())
					copy[j][i] = stack.pop();
			}
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// 입력으로 들어온 row에 구슬 1개를 발사한다.
	private static void shoot(int[][] map, int row, int col) {
		// 맞은 벽돌에 적인 수 만큼 상하좌우로 벽돌이 깨진다.
		// 벽돌의 범위에 들어간 벽돌들이 깨질때 그 벽돌에 적인 수 만큼 또 다른 벽돌들이 깨진다.
		Queue<Point> queue = new ArrayDeque<>();
		// 맨처음 깨지는 벽돌 찾기
		if (map[row][col] > 1) {
			queue.offer(new Point(row, col, map[row][col]));
		}
		map[row][col] = 0;
		// 벽돌 깨보기
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			// 벽돌이 깨지는 범위 구하기
			for (int d = 0; d < 4; d++) {
				int nr = cur.r;
				int nc = cur.c;
				for (int k = 1; k < cur.cnt; k++) {
					nr += dr[d];
					nc += dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] > 0) {
						if (map[nr][nc] > 1)
							queue.offer(new Point(nr, nc, map[nr][nc]));
						// 벽돌 0으로 부시기
						map[nr][nc] = 0;
					}
				}
			}
		}
	}

	// map의 내용을 copyed에 복사하는 메소드
	private static void copy(int[][] copyed, int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyed[i][j] = map[i][j];
			}
		}
	}

	private static void print(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
