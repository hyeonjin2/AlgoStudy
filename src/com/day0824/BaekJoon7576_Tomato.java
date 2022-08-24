package com.day0824;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon7576_Tomato {

	static int N, M, Ans;
	static Queue<Point> queue;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		queue = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if (num == 1) {
					queue.offer(new Point(i, j));
				}
			}
		}
		bfs();
		System.out.println(Ans);
	}

	// 4방 탐색 델타 배열 상,하,좌,우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void bfs() {
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int d = 0; d < 4; d++) {
				Point next = new Point(cur.x + dx[d], cur.y + dy[d]);
				// 배열의 범위 밖이면 건너뜀
				if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= M)
					continue;
				// 익지 않은 토마토가 있으면 큐에 넣기
				if (map[next.x][next.y] == 0) {
					map[next.x][next.y] = map[cur.x][cur.y] + 1;
					queue.add(next);
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					Ans = -1;
					return;
				}
				Ans = Math.max(Ans, map[i][j]);
			}
		}
		Ans--;
	}
}
