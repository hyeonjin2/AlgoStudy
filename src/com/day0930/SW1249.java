package com.day0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

// 보급로
public class SW1249 {

	static class Point implements Comparable<Point> {
		int x, y;
		int cost;

		public Point(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return this.cost - o.cost;
		}
	}

	static int N, Ans;
	static final int INF = Integer.MAX_VALUE;
	static int[][] map, costs;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			costs = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}
			Ans = INF;
			for (int i = 0; i < N; i++) {
				Arrays.fill(costs[i], INF);
			}
			bfs();
			sb.append("#").append(tc).append(" ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void bfs() {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		queue.offer(new Point(0, 0, 0));
		boolean[][] visited = new boolean[N][N];
		visited[0][0] = true;
		costs[0][0] = 0;
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			int cost = cur.cost;
			if (x == N - 1 && y == N - 1) {
				Ans = Math.min(Ans, cost);
				return;
			}
			if (cost >= Ans)
				continue;
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				int ncost = cost + map[nx][ny];
				if (!visited[nx][ny] || ncost < costs[nx][ny]) {
					visited[nx][ny] = true;
					costs[nx][ny] = ncost;
					queue.offer(new Point(nx, ny, ncost));
				}
			}
		}
	}
}
