package com.day0819;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BaekJoon3109_Bakery {

	static int n, m, Ans;
	static char[][] map; // 집 정보를 담을 배열

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
		for (int i = 0; i < n; i++) {
			if (check(new Point(i, 0)))
				Ans++;
		}
		System.out.println(Ans);
	}

	// 이동 델타 배열 우상,우,우하
	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };

	static boolean check(Point start) {
		map[start.x][start.y] = 'x';
		if (start.y == m - 1) {
			return true;
		}
		for (int d = 0; d < 3; d++) {
			Point next = new Point(start.x + dx[d], start.y + dy[d]);
			if (next.x >= 0 && next.x < n && next.y < m && map[next.x][next.y] == '.') {
				if (check(next))
					return true;
			}
		}
		return false;
	}
}
