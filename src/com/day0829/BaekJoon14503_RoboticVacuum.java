package com.day0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준14503 로봇청소기
public class BaekJoon14503_RoboticVacuum {

	static class Robot {
		int x, y;
		int d;

		public Robot(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Robot [x=" + x + ", y=" + y + ", d=" + d + "]";
		}
	}

	static int N, M, totalCnt;
	static Robot robot;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		work(robot);
		System.out.println(totalCnt);
	}

	// 북,동,남,서
	// 4방 탐색 델타 배열 -> 상,우,하,좌
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	private static void work(Robot cur) {

		// 1. 현재 위치를 청소한다.
		if (map[cur.x][cur.y] == 0) {
			map[cur.x][cur.y] = 2;
			totalCnt++;
		}

		// 2. 왼쪽방향부터 탐색을 진행한다.
		boolean check = false;
		int dir = cur.d;
		for (int d = 0; d < 4; d++) {
			int next_d = (dir + 3) % 4;
			int nx = cur.x + dx[next_d];
			int ny = cur.y + dy[next_d];

			if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
				if (map[nx][ny] == 0) {
					work(new Robot(nx, ny, next_d));
					check = true;
					break;
				}
			}
			dir = (dir + 3) % 4;
		}

		// 네 방향 모두 청소가 되어있거나 벽인 경우
		if (!check) {
			int next_d = (cur.d + 2) % 4;
			int nx = cur.x + dx[next_d];
			int ny = cur.y + dy[next_d];

			if (nx > 0 && nx < N && ny > 0 && ny < M) {
				if (map[nx][ny] != 1) {
					work(new Robot(nx, ny, cur.d));
				}
			}
		}
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
