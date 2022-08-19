package com.day0819;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon3109_Bakery {

	static int n, m, cnt;
	static char[][] map; // 집 정보를 담을 배열
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// 지도 배열 입력 받기
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().trim().toCharArray();
		}
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			dfs(new Point(i, 0));// c=0에서 시작
		}
		System.out.println(cnt);
	}

	// 이동 델타 배열 우상,우,우하
	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };

	private static void dfs(Point start) {
		visited[start.x][start.y] = true;
		if (start.y == m - 1) {
			cnt++;
			return;
		}
		for (int d = 0; d < 3; d++) {
			Point next = new Point(start.x + dx[d], start.y + dy[d]);
			if (next.x < 0 || next.x >= n || next.y >= m || map[next.x][next.y] == 'x')
				continue;
			if (!visited[next.x][next.y]) {
				dfs(next);
			}
		}
	}
}
