package com.day0824;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon7569_Tomato {

	static class Point {
		int x, y, z;

		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static int N, M, H, Ans;
	static Queue<Point> queue;
	static int[][][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		// 배열 입력 받기
		map = new int[N][M][H];
		queue = new ArrayDeque<>();
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if (map[i][j][k] == 1) {
						queue.add(new Point(i, j, k));
					}
				}
			}
		}
		bfs();
		System.out.println(Ans);
	}

	// 탐색 델타 배열 위,아래,왼쪽,오른쪽,앞,뒤
	static int[] dx = { 0, 0, 0, 0, -1, 1 };
	static int[] dy = { 0, 0, -1, 1, 0, 0 };
	static int[] dz = { -1, 1, 0, 0, 0, 0 };

	private static void bfs() {
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int d = 0; d < 6; d++) {
				Point next = new Point(cur.x + dx[d], cur.y + dy[d], cur.z + dz[d]);
				if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= M || next.z < 0 || next.z >= H)
					continue;
				if (map[next.x][next.y][next.z] == 0) {
					map[next.x][next.y][next.z] = map[cur.x][cur.y][cur.z] + 1;
					queue.add(next);
				}
			}
		}
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j][k] == 0) {
						Ans = -1;
						return;
					}
					Ans = Math.max(Ans, map[i][j][k]);
				}
			}
		}
		Ans--;
	}
}
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
