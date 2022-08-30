package com.day0829;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// [SW Test 샘플문제] 프로세스 연결하기
public class SW1767_ProcessorConnect {

	static int N, Ans, min, size;
	static List<Point> cores;
	static boolean[] selected;
	static int[][] map, copy;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < N - 1; j++) {
					if (map[i][j] == 1) {
						cores.add(new Point(i, j));
					}
				}
			}
			size = cores.size();
			selected = new boolean[size];
			min = Integer.MAX_VALUE;
			for (int i = size; i >= 0; i--) {
				combi(0, 0, i);
				if (min < Integer.MAX_VALUE)
					break;
			}

			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

	private static void combi(int cnt, int start, int R) {
		if (cnt == R) {
			dfs(0, 0);
			return;
		}
		for (int i = start; i < size; i++) {
			selected[i] = true;
			combi(cnt + 1, i + 1, R);
			selected[i] = false;
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void dfs(int idx, int cnt) {
		if (idx == size) {
			min = Math.min(min, cnt);
			return;
		}
		if (!selected[idx]) {
			dfs(idx + 1, cnt);
			return;
		}
		for (int d = 0; d < 4; d++) {
			Point cur = cores.get(idx);
			int x = cur.x;
			int y = cur.y;
			int count = 0;
			boolean success = false;
			while (true) {
				x += dx[d];
				y += dy[d];
				if (x < 0 || x >= N || y < 0 || y >= N) {
					success = true;
					break;
				}
				if (map[x][y] != 0)
					break;
				map[x][y] = 2;
				count++;
			}
			if (success)
				dfs(idx + 1, cnt + count);
			while (true) {
				x -= dx[d];
				y -= dy[d];
				if (x == cur.x && y == cur.y)
					break;
				map[x][y] = 0;
			}
		}
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