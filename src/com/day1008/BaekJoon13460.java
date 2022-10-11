package com.day1008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 구슬 탈출2
public class BaekJoon13460 {

	static class Point {
		char color;
		int x, y;
		int cnt;
		boolean goal;

		public Point(char color, int x, int y, int cnt) {
			this.color = color;
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + ", cnt=" + cnt + "]";
		}
	}

	static int N, M, Ans;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		Point red = null;
		Point blue = null;

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R') {
					red = new Point(map[i][j], i, j, 0);
				} else if (map[i][j] == 'B') {
					blue = new Point(map[i][j], i, j, 0);
				}
			}
		}
//		print(map);
		bfs(red, blue);
		System.out.println(Ans);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void bfs(Point red, Point blue) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(red);
		queue.offer(blue);

		boolean[][][] visited = new boolean[2][N][M];
		visited[0][red.x][red.y] = true;
		visited[1][blue.x][blue.y] = true;
		int[] totalCnt = new int[2];
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			char color = map[cur.x][cur.y];
			int ind = 0;
			if (color == 'B')
				ind = 1;
			totalCnt[ind] = cur.cnt;
			if (totalCnt[0] > 10) {
				Ans = -1;
				return;
			}
			if (map[red.x][red.y] == 'O' && map[blue.x][blue.y] == 'O') {
				Ans = -1;
				return;
			} else if (map[red.x][red.y] == 'O' && map[blue.x][blue.y] != 'O') {
				Ans = red.cnt;
				break;
			}
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				while (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[ind][nx][ny]) {
					// 벽을 만나면 멈추고 cnt증가
					if (map[nx][ny] == '#') {
						map[cur.x][cur.y] = '.';
						nx -= dx[d];
						ny -= dy[d];
						map[nx][ny] = color;
						visited[ind][nx][ny] = true;
						queue.offer(new Point(color, nx, ny, cur.cnt + 1));
						break;
					}
					// 빨간 구슬이나 파란 구슬을 만났을 경우 일단 멈추기->다른 구슬 먼저 굴려보기
					else if (map[nx][ny] == 'R' || map[nx][ny] == 'B') {
						map[cur.x][cur.y] = '.';
						nx -= dx[d];
						ny -= dy[d];
						map[nx][ny] = color;
						visited[ind][nx][ny] = true;
						queue.offer(new Point(color, nx, ny, cur.cnt));
						break;
					}
					// 구멍을 만나면 일단 멈추기->다른 구슬도 구멍에 들어가는지 확인
					else if (map[nx][ny] == 'O') {
						if (ind == 0) {
							red.x = nx;
							red.y = ny;
						} else {
							blue.x = nx;
							blue.y = ny;
						}
						queue.offer(new Point(color, nx, ny, cur.cnt + 1));
						break;
					}
					nx += dx[d];
					ny += dy[d];
				}
			}
			print(map);
		}
	}

	private static void print(char[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
