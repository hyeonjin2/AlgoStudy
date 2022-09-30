package com.day0930;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 파이프 옮기기1
public class BaekJoon17070_2 {

	static int N, Ans;
	static int[][] map;
	static int[][] route;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		route = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(route, -1);
		}
		route[0][1] = 1;
		dfs(new Point(0, 1), 0);
		System.out.println(route[N - 1][N - 1]);
	}

	// 우, 하, 우하
	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 0, 1 };

	private static void dfs(Point cur, int dir) {
		if (cur.x == N - 1 && cur.y == N - 1) {
			return;
		}
		if (route[cur.x][cur.y] != -1) {

		}
		for (int d = 0; d < 3; d++) {
			// 현재 방향이 가로일 때 세로로 이동 불가능
			if (dir == 0 && d == 1)
				continue;
			// 현재 방향이 세로일 때 가로로 이동 불가능
			if (dir == 1 && d == 0)
				continue;

			// 해당 방향으로 올 수 있는지 체크
			if (check(cur.x, cur.y, d)) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				route[nx][ny] += route[cur.x][cur.y];
				dfs(new Point(nx, ny), d);
			}
		}
		print(route);
	}

	private static boolean check(int x, int y, int d) {
		// 방향이 가로일 때
		if (d == 0) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!isIn(nx, ny) || map[nx][ny] != 0) {
				return false;
			}
		}
		// 방향이 세로일 때
		else if (d == 1) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!isIn(nx, ny) || map[nx][ny] != 0) {
				return false;
			}
		}
		// 방향이 대각선일 때
		else if (d == 2) {
			for (int dd = 0; dd < 3; dd++) {
				int nx = x + dx[dd];
				int ny = y + dy[dd];
				if (!isIn(nx, ny) || map[nx][ny] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && nx < N && ny >= 0 && ny < N;
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
