package com.day0822;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SW2819_GratingNumber {

	static int N, Ans;
	static String selected;
	static int[][] map;
	static HashSet<Integer> set;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = 4;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			set = new HashSet<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(new Point(i, j), "");
				}
			}
			Ans = set.size();
			sb.append("#").append(tc).append(" ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	// 4방 탐색 델타 배열
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void dfs(Point cur, String str) {
		// 현재위치의 숫자 문자열에 넣기
		str += map[cur.x][cur.y];
		// 길이가 7이면 return
		if (str.length() == 7) {
			selected = str;
			set.add(Integer.parseInt(selected));
			return;
		}
		for (int d = 0; d < 4; d++) {
			Point next = new Point(cur.x + dx[d], cur.y + dy[d]);
			// 배열의 범위 밖이면 건너뜀
			if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= N)
				continue;
			// 배열의 범위 안이라면
			dfs(next, str);
		}
	}
}
