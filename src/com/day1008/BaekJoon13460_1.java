package com.day1008;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 구슬 탈출2
public class BaekJoon13460_1 {

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
					red = new Point(i, j);
				} else if (map[i][j] == 'B') {
					blue = new Point(i, j);
				}
			}
		}
		final int INF = 100;
		Ans = INF;
		bfs(red, blue, 0);
		Ans = Ans == INF ? -1 : Ans;
		System.out.println(Ans);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void bfs(Point red, Point blue, int cnt) {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][][][] visited = new boolean[N][M][N][M];
		queue.offer(new int[] { red.x, red.y, blue.x, blue.y, cnt });
		visited[red.x][red.y][blue.x][blue.y] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int count = cur[4];
			if (count >= 10) {
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nRx = cur[0];
				int nRy = cur[1];
				int nBx = cur[2];
				int nBy = cur[3];

				// 빨간 구슬 이동->벽을 만나기 전까지, 구멍을 만나면 break
				while (map[nRx + dx[d]][nRy + dy[d]] != '#') {
					nRx += dx[d];
					nRy += dy[d];
					if (map[nRx][nRy] == 'O')
						break;
				}
				// 파란 구슬 이동
				while (map[nBx + dx[d]][nBy + dy[d]] != '#') {
					nBx += dx[d];
					nBy += dy[d];
					if (map[nBx][nBy] == 'O')
						break;
				}
				// 파란 구슬이 구멍에 들어간 경우->건너뛰고 다음 경우 탐색
				if (map[nBx][nBy] == 'O')
					continue;
				// 빨간 구슬이 구멍에 들어간 경우->이동횟수의 최솟값 구한 후 return
				if (map[nRx][nRy] == 'O') {
					Ans = Math.min(Ans, count + 1);
					return;
				}
				// 빨간 구슬과 파란 구슬이 만났을 때
				if (nRx == nBx && nRy == nBy && map[nRx][nRy] != 'O') {
					// 먼저 도착한 구슬 자리로 주고, 그 뒤에 도착한 구슬 한칸 뒤로 위치시키기
					int moveR = Math.abs(nRx - cur[0]) + Math.abs(nRy - cur[1]);
					int moveB = Math.abs(nBx - cur[2]) + Math.abs(nBy - cur[3]);
					// 빨간 구슬이 먼저 도착한 경우
					if (moveR < moveB) {
						nBx -= dx[d];
						nBy -= dy[d];
					} else {
						nRx -= dx[d];
						nRy -= dy[d];
					}
				}
				if (!visited[nRx][nRy][nBx][nBy]) {
					visited[nRx][nRy][nBx][nBy] = true;
					queue.offer(new int[] { nRx, nRy, nBx, nBy, count + 1 });
				}
			}
		}
	}
}
