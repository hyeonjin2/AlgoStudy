package com.day1005;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 치즈
public class BaekJoon2636 {

	static int N, M, totalCnt;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					totalCnt++;
			}
		}
//		print(map);
		visited = new boolean[N][M];
		int Ans = 0;
		int time = 0;
		// 치즈가 다 녹을때까지 치즈 녹이기
		while (totalCnt != 0) {
			Ans = totalCnt;
			bfs();
			time++;
		}
		System.out.println(time);
		System.out.println(Ans);
	}

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	private static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(0, 0));
		boolean[][] visited = new boolean[N][M];
		visited[0][0] = true;
		int[][] temp = new int[N][M];
		copy(temp, map);
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny])
					continue;
				visited[nx][ny] = true;
				if (map[nx][ny] == 1) {
					totalCnt--;
					temp[nx][ny] = 0;
				} else {
					queue.offer(new Point(nx, ny));
				}
			}
		}
		copy(map, temp);
	}

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
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
