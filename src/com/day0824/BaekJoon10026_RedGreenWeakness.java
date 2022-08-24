package com.day0824;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon10026_RedGreenWeakness {

	static int N;
	static boolean[][] visited1, visited2;
	static char[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 배열 입력 받기
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int sum1 = 0;
		int sum2 = 0;
		visited1 = new boolean[N][N];
		visited2 = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 색약이 아닌 사람이 보는 경우
				if (!visited1[i][j]) {
					sum1 += dfs1(new Point(i, j));
				}
				// 색약인 사람이 보는 경우
				if (!visited2[i][j]) {
					sum2 += dfs2(new Point(i, j));
				}
			}
		}
		System.out.println(sum1 + " " + sum2);
	}

	// 4방 탐색 델타 배열 상,하,좌,우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	// 색약이 아닌 사람이 보는 경우
	private static int dfs1(Point cur) {
		visited1[cur.x][cur.y] = true;
		char curC = map[cur.x][cur.y];
		for (int d = 0; d < 4; d++) {
			Point next = new Point(cur.x + dx[d], cur.y + dy[d]);
			if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= N)
				continue;
			if (!visited1[next.x][next.y] && map[next.x][next.y] == curC)
				dfs1(next);
		}
		return 1;
	}

	static String rg = "RG";

	// 색약인 사람이 보는 경우
	private static int dfs2(Point cur) {
		visited2[cur.x][cur.y] = true;
		char curC = map[cur.x][cur.y];
		for (int d = 0; d < 4; d++) {
			Point next = new Point(cur.x + dx[d], cur.y + dy[d]);
			if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= N)
				continue;
			if (rg.contains(curC + "")) {
				if (!visited2[next.x][next.y] && rg.contains(map[next.x][next.y] + ""))
					dfs2(next);
			}else {
				if (!visited2[next.x][next.y] && map[next.x][next.y] == curC)
					dfs2(next);
			}
		}
		return 1;
	}

}
