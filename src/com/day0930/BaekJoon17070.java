package com.day0930;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파이프 옮기기1
public class BaekJoon17070 {

	static int N, Ans;
	static int[][] map;

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
		dfs(new Point(0, 1), 0);
		System.out.println(Ans);
//		print(route);
	}

	// 우, 하, 우하
	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 0, 1 };

	private static void dfs(Point cur2, int dir) {
		if (cur2.x == N - 1 && cur2.y == N - 1) {
			Ans++;
			return;
		}
		for (int d = 0; d < 3; d++) {
			// 현재 방향이 가로일 때 세로로 이동 불가능
			if (dir == 0 && d == 1)
				continue;
			// 현재 방향이 세로일 때 가로로 이동 불가능
			if (dir == 1 && d == 0)
				continue;

			// 해당 방향으로 갈 수 있는지 체크
			if (check(cur2.x, cur2.y, d)) {
				int nx2 = cur2.x + dx[d];
				int ny2 = cur2.y + dy[d];
				dfs(new Point(nx2, ny2), d);
			}
		}
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
