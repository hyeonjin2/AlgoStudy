package com.day0813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2468_SafeArea {

	static int N, cnt;
	static boolean[][] visited;
	static int[][] map;
	// 4방 탐색 델타배열 상,하,좌,우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		// 2차원 배열 초기화
		int maxHeight = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				maxHeight = maxHeight < num ? num : maxHeight;
			}
		}
		// 비의 양 : 0 ~ maxHeight
		// 값이 0이 아닌 영역 찾기 -> 4방 탐색
		int result = 0;
		for (int h = 0; h <= maxHeight; h++) {
			visited = new boolean[N][N];
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > h && !visited[i][j]) {
						cnt += search(i, j, h);
					}
				}
			}
			result = result < cnt ? cnt : result;
		}
		System.out.println(result);
	}

	// 4방 탐색해서 cnt를 구하는 함수
	private static int search(int row, int col, int h) {// h:비의 양
		visited[row][col] = true;
		for (int d = 0; d < 4; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];
			// 배열의 범위를 벗어나거나 높이가 낮으면 continue
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] <= h) {
				continue;
			}
			if (visited[nr][nc])
				continue;
			search(nr, nc, h);
		}
		return 1;
	}

}
