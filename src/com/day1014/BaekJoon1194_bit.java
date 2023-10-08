package com.day1014;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 달이 떠오른다, 가자.
public class BaekJoon1194_bit {

	static class Point {
		int x, y;
		int key;
		int cnt;

		public Point(int x, int y, int key, int cnt) {
			this.x = x;
			this.y = y;
			this.key = key;
			this.cnt = cnt;
		}
	}

	static int N, M, ans;
	static char[][] map;
	static Point start;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '0')
					start = new Point(i, j, 0, 0);
			}
		}
//		print(map);
		final int INF = Integer.MAX_VALUE;
		ans = INF;
		bfs(start);
		if (ans == INF) {
			ans = -1;
		}
		System.out.println(ans);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void bfs(Point start) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(start);
		boolean[][][] visited = new boolean[64][N][M];
		visited[start.key][start.x][start.y] = true;
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			int z = cur.key;
			int cnt = cur.cnt;
			if (map[x][y] == '1') {
				ans = Math.min(ans, cnt);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				int nk = cur.key;
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[z][nx][ny] || map[nx][ny] == '#')
					continue;
				// 열쇠를 주으면
				if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
					nk = nk | 1 << (map[nx][ny] - 'a');
				}
				// 문을 만나면
				else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
					// 열쇠가 있으면 따고 들어가기
					if ((nk & 1 << (map[nx][ny] - 'A')) == 0) {
						continue;
					}
				}
				visited[nk][nx][ny] = true;
				queue.offer(new Point(nx, ny, nk, cnt + 1));
			}
		}
	}
}
