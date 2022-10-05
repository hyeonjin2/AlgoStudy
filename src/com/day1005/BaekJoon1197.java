package com.day1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 달이 차오른다,가자.
public class BaekJoon1197 {

	static class Point {
		int r, c;
		int cnt;
		boolean[] keys;

		public Point() {
		}

		public Point(int r, int c, int cnt, boolean[] keys) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.keys = keys;
		}
	}

	static int R, C, Ans, totalDoor;
	static char[][] maze;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		maze = new char[R][C];
		Point start = new Point(0, 0, 0, new boolean[6]);

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				maze[i][j] = str.charAt(j);
				if (maze[i][j] == '0')
					start = new Point(i, j, 0, new boolean[6]);
				if (maze[i][j] >= 'A' && maze[i][j] <= 'E')
					totalDoor++;
			}
		}
		bfs(start);
		if (Ans == 0)
			Ans -= 1;
		System.out.println(Ans);
//		print(maze);
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static void bfs(Point start) {
		PriorityQueue<Point> queue = new PriorityQueue<>((e1, e2) -> e1.cnt - e2.cnt);
		// 열쇠가 있을 때 방문했는지 아닌지 체크 -> 해당 열쇠가 있으면 따고 들어갈 수 있음.
		queue.offer(start);
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;
			boolean[] keys = cur.keys;
			if (maze[r][c] == '1') {
				Ans = cur.cnt;
				return;
			}
//			System.out.println("r:" + r + " c:" + c + " cnt:" + cnt + " keys:" + Arrays.toString(keys));
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || maze[nr][nc] == '#')
					continue;
				// 열쇠를 발견하면 열쇠개수 배열 초기화
				if (maze[nr][nc] >= 'a' && maze[nr][nc] <= 'f') {
					boolean[] temp = new boolean[6];
					for (int i = 0; i < 6; i++) {
						if (keys[i] == true)
							temp[i] = true;
					}
					temp[maze[nr][nc] - 'a'] = true;
					queue.offer(new Point(nr, nc, cnt + 1, temp));
				}
				// 문을 발견하면 열쇠가 있는지 확인해봄
				else if (maze[nr][nc] >= 'A' && maze[nr][nc] <= 'F') {
					// 해당 문에 열쇠가 없으면 못지나감
					if (!keys[maze[nr][nc] - 'A'])
						continue;
					// 열쇠가 있으면 따고 들어갈 수 있음
					// 따고 이동하기
					queue.offer(new Point(nr, nc, cnt + 1, keys));
				}
				// 그냥 빈 칸이면
				else if (maze[nr][nc] == '.' || maze[nr][nc] == '1' || maze[nr][nc] == '0') {
					// 이동하기
					queue.offer(new Point(nr, nc, cnt + 1, keys));
				}
			}
		}
	}

	private static void print(char[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
