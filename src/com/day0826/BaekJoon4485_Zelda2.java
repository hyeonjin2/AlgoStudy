package com.day0826;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon4485_Zelda2 {

	static int N, Ans;
	static boolean[][] visited;
	static int[][] map, D;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			cnt++;
			StringTokenizer st;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited = new boolean[N][N];
			D = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(D[i], Integer.MAX_VALUE);
			}
			Ans = Integer.MAX_VALUE;
			bfs(new Point(0, 0));
			Ans = D[N - 1][N - 1];
			sb.append("Problem ").append(cnt).append(": ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void bfs(Point start) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(start);
		D[start.x][start.y] = map[start.x][start.y];
		visited[start.x][start.y] = true;
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
					continue;
				if (D[nx][ny] > D[cur.x][cur.y] + map[nx][ny]) {
					D[nx][ny] = D[cur.x][cur.y] + map[nx][ny];
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
				}
				visited[nx][ny] = false;
			}
//			print(D);
		}
	}

	static void print(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
/*








*/