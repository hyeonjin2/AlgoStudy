package com.day0815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1012_Cabbage {
	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // # of testcase
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // row
			m = Integer.parseInt(st.nextToken()); // col
			int k = Integer.parseInt(st.nextToken()); // # of cabbage
			map = new int[n][m];
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				map[row][col] = 1;

			}
			int count = 0;
			visited = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 1 && !visited[i][j])
						count += search(i, j);
				}
			}
			System.out.println(count);
		}
	}

	// 4방 탐색 델타 배열 상,하,좌,우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static int search(int row, int col) {
		visited[row][col] = true;
		// 4방 탐색
		for (int d = 0; d < 4; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];
			// 배열의 범위를 벗어나거나 방문한 적이 있으면 건너뜀
			if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 0) {
				continue;
			}
			if (visited[nr][nc])
				continue;
			search(nr, nc);
		}
		return 1;
	}
}
