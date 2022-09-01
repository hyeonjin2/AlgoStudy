package com.day0831;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

// 뱀
public class BaekJoon3190 {

	static int N, M, K, curD, time;
	static Queue<Point> snake;
	static int[][] map;
	static HashMap<Integer, Character> dir;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = 1;
		}
		// 뱀이 방향을 바꿀 때
		dir = new HashMap<>();
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			dir.put(n, st.nextToken().charAt(0));
		}
		// 뱀은 게임이 시작할 때 0,0에 위치하고 뱀의 길이는 1이다.
		// 뱀은 처음에 오른쪽을 향한다.
		snake = new ArrayDeque<>();
		curD = 1;
		snake.add(new Point(0, 0));
		map[0][0] = 2;
		game(0, 1);
//		print(map);
		System.out.println(time);
	}

	// 4방 탐색 델타 배열 상,우,하,좌
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	private static void game(int x, int y) {
		time++;
		// 사과가 있는 경우
		if (map[x][y] == 1) {
			// 머리 이동하기
			map[x][y] = 2;
			snake.add(new Point(x, y));
			// 사과가 없는 경우
		} else if (map[x][y] == 0) {
			// 머리 이동하기
			snake.add(new Point(x, y));
			map[x][y] = 2;
			// 꼬리 이동하기
			Point tail = snake.poll();
			map[tail.x][tail.y] = 0;
		}
		// 방향을 오른쪽으로 90도 회전할 때
		if (dir.containsKey(time) && dir.get(time) == 'D') {
			curD = (curD + 1) % 4;
			// 방향을 왼쪽으로 90도 회전할 때
		} else if (dir.containsKey(time) && dir.get(time) == 'L') {
			curD = (curD + 3) % 4;
		}
		int nx = x + dx[curD];
		int ny = y + dy[curD];

		// 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
		if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 2) {
			time++;
			return;
		}
		game(nx, ny);
	}

	private static void print(int[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
