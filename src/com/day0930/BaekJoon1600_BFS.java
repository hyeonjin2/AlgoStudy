package com.day0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 말이 되고픈 원숭이
public class BaekJoon1600_BFS {

	static class Point {
		int x, y;
		int k, t;

		public Point(int x, int y, int k, int t) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.t = t;
		}

	}

	static int N, M, Ans, totalCnt;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Ans = bfs(new Point(0, 0, k, 0));
		System.out.println(Ans);
//		System.out.println(totalCnt);
//		print(map);
	}

	// 4방 탐색 델타 배열
	static int[] dx1 = { -1, 1, 0, 0 };
	static int[] dy1 = { 0, 0, -1, 1 };
	// 말처럼 움직일 때의 델타 배열
	static int[] dx2 = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dy2 = { -2, -1, 1, 2, -2, -1, 1, 2 };

	private static int bfs(Point start) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(start);
		boolean[][][] visited = new boolean[N][M][start.k + 1];
		visited[0][0][start.k] = true;
		while (!queue.isEmpty()) {
			totalCnt++;
			Point cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			int k = cur.k;
			int t = cur.t;
			if (x == N - 1 && y == M - 1) {
				return t;
			}
			// 말처럼 움직이는 경우
			// 회수가 남아있는지 체크
			if (k > 0) {
				for (int d = 0; d < 8; d++) {
					int nx = x + dx2[d];
					int ny = y + dy2[d];
					if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny][k - 1] || map[nx][ny] == 1)
						continue;
					visited[nx][ny][k - 1] = true;
					queue.offer(new Point(nx, ny, k - 1, t + 1));
				}
			}
			// 그냥 원숭이처럼 움직이는 경우
			for (int d = 0; d < 4; d++) {
				int nx = x + dx1[d];
				int ny = y + dy1[d];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny][k] || map[nx][ny] == 1)
					continue;
				visited[nx][ny][k] = true;
				queue.offer(new Point(nx, ny, k, t + 1));
			}
		}
		return -1;
	}

	private static void print(int[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
