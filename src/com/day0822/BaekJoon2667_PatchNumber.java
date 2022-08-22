package com.day0822;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaekJoon2667_PatchNumber {

	static int N, totalCnt, Cnt;
	static List<Integer> counts;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		counts = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					totalCnt++;
					Cnt = 0;
					dfs(new Point(i, j));
					counts.add(Cnt);
				}
			}
		}
		sb.append(totalCnt).append("\n");
		Collections.sort(counts);
		for (int i = 0, size = counts.size(); i < size; i++) {
			sb.append(counts.get(i)).append("\n");
		}
		System.out.println(sb);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void dfs(Point cur) {
		visited[cur.x][cur.y] = true;
		Cnt++;
		for (int d = 0; d < 4; d++) {
			Point next = new Point(cur.x + dx[d], cur.y + dy[d]);
			if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= N)
				continue;
			if (!visited[next.x][next.y] && map[next.x][next.y] == 1)
				dfs(next);
		}
	}
}
