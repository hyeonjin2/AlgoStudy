package com.day1004;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// 벽돌 깨기
public class SW5656 {

	static int R, N, M, Ans;
	static int[] orders;
	static int[][] map, copy, game;

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
			copy = new int[N][M];
			copy(game, map);
			copy(copy, map);
//			perm(0);
			orders = new int[] { 2, 2, 6 };
			calc();

			sb.append("#").append(tc).append(" ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	// 중복순열로 구슬 발사할 위치 순서 정하기
	private static void perm(int cnt) {
		if (cnt == R) {
			// 순서가 정해지면 구슬 발사해보고 남은 개수 구하기
			System.out.println(Arrays.toString(orders));
			copy(game, map);
			copy(copy, map);
			int result = calc();
			Ans = Math.min(Ans, result);
			return;
		}
		for (int i = 0; i < M; i++) {
			orders[cnt] = i;
			perm(cnt + 1);
		}
	}

	// 정해진 순서에 따라 구슬 발사해보고 남은 개수 구하기
	private static int calc() {
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			// 정해진 순서로 구슬을 1개 발사해본다.
			int col = orders[i];
			shoot(col);
			copy(copy, game);
			print(copy);
		}
		// 깨져야할 벽돌들이 모두 깨지면 벽돌들이 바닥으로 떨어진다.
		// R만큼 위를 반복한다.

		return cnt;
	}

	// 입력으로 들어온 row에 구슬 1개를 발사한다.
	private static void shoot(int col) {
		// 맞은 벽돌에 적인 수 만큼 상하좌우로 벽돌이 깨진다.
		// 벽돌의 범위에 들어간 벽돌들이 깨질때 그 벽돌에 적인 수 만큼 또 다른 벽돌들이 깨진다.
		Queue<Point> queue = new ArrayDeque<>();
		// 맨처음 깨지는 벽돌 찾기
		Point start = new Point(0, col);
		for (int i = 0; i < N; i++) {
			if (copy[i][col] == 0)
				continue;
			start = new Point(i, col);
			break;
		}
		queue.offer(start);
		// 벽돌 깨보기
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int size = copy[cur.x][cur.y] - 1;
			// 벽돌이 깨지는 범위 구하기
			int r1 = (cur.x - size < 0) ? 0 : cur.x - size;
			int c1 = (cur.y - size < 0) ? 0 : cur.y - size;
			int r2 = (cur.x + size >= N) ? N - 1 : cur.x + size;
			int c2 = (cur.y + size >= M) ? M - 1 : cur.y + size;
			System.out.println(r1 + " " + c1 + " " + r2 + " " + c2);
			for (int i = r1; i <= r2; i++) {
				for (int j = c1; j <= c2; j++) {
					// 연쇄적으로 깨지는 벽돌 찾기 -> 세로,가로 검사
					if (copy[i][cur.y] > 1 && game[i][cur.y] != 0)
						queue.offer(new Point(i, cur.y));
					if (copy[cur.x][j] > 1 && game[cur.x][j] != 0)
						queue.offer(new Point(cur.x, j));
					game[i][cur.y] = 0;
					game[cur.x][j] = 0;
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
