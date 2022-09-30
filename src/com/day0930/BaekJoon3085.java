package com.day0930;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// 사탕 게임
public class BaekJoon3085 {

	static int N, cnt, Ans;
	static char[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		bfs();
		print(map);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		queue.offer(new Point(0, 0));
		visited[0][0] = true;
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
					continue;
				// map[nx][ny]가 map[x][y]와 다르면 바꿔본다
				char temp = map[nx][ny];
				if (map[x][y] != map[nx][ny]) {
					map[nx][ny] = map[x][y];
					// 먹을 수 있는 사탕의 최대 개수 구하기
					cnt = Math.max(cnt, check());
					// 다시 값 되돌리기
					map[nx][ny] = temp;
				}
				visited[nx][ny] = true;
				queue.offer(new Point(nx, ny));
			}
		}
	}

	private static int check() {
		// C,P,Z,Y
		int[] sum1 = new int[4];
		int[] sum2 = new int[4];
		// 가로, 세로로 연속하는 색의 사탕 수 세기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 가로 검사
				
				// 세로 검사
				
			}
		}
		// 세로 검사
		for (int i = 0; i < N; i++) {

		}
		return 0;
	}

	private static void print(char[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
