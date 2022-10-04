package com.day1004;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 연구소
public class BaekJoon14502 {

	static int N, M, Ans;
	static int[][] map, copy;
	static List<Point> list;
	static Point[] walls;
	static List<Point> viruses;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		list = new ArrayList<>();
		viruses = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					list.add(new Point(i, j));
				if (map[i][j] == 2) {
					viruses.add(new Point(i, j));
				}
			}
		}
		// 벽 세우기
		walls = new Point[3];
		copy = new int[N][M];
		comb(0, 0);
		System.out.println(Ans);
	}

	// 벽을 세울수 있는 곳에 벽 3개 세우기
	private static void comb(int cnt, int start) {
		if (cnt == 3) {
			// 벽을 세우기
			copy(copy, map);
			setWalls();
			// 바이러스 퍼져나가기
			bfs();
			// 안전지역 개수 세기
			int result = calc();
			Ans = Math.max(Ans, result);
			return;
		}
		for (int i = start, size = list.size(); i < size; i++) {
			walls[cnt] = list.get(i);
			comb(cnt + 1, i + 1);
		}
	}

	private static void setWalls() {
		for (int i = 0; i < 3; i++) {
			Point wall = walls[i];
			copy[wall.x][wall.y] = 1;
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	// 바이러스가 퍼져나가는 메소드
	private static void bfs() {
		boolean[][] visited = new boolean[N][M];
		Queue<Point> queue = new ArrayDeque<>();
		for (int i = 0, size = viruses.size(); i < size; i++) {
			Point virus = viruses.get(i);
			visited[virus.x][virus.y] = true;
			queue.offer(virus);
		}
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || copy[nx][ny] == 1)
					continue;
				visited[nx][ny] = true;
				copy[nx][ny] = 2;
				queue.offer(new Point(nx, ny));
			}
		}
	}

	private static int calc() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}

	private static void copy(int[][] copyed, int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyed[i][j] = map[i][j];
			}
		}
	}
}
