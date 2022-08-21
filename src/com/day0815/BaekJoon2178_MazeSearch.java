package com.day0815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon2178_MazeSearch {

	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static StringBuilder sb;

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// 배열 초기화
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		Point start = new Point(0, 0);
		dfs(start);
		System.out.println(map[n - 1][m - 1]);
	}

	// 4방 탐색 델타 배열 상,하,좌,우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static void dfs(Point start) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(start);
		visited = new boolean[n][m];
		visited[start.r][start.c] = true;

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
					continue;
				}
				if (visited[nr][nc] || map[nr][nc] == 0)
					continue;
				visited[nr][nc] = true;
				map[nr][nc] = map[p.r][p.c] + 1;
				queue.offer(new Point(nr, nc));
			}
		}
	}
}
