package com.day0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SW2805_Crops {
	static int[][] field;
	static boolean[][] visited;
	static int count = 0;
	// 4방 탐색 델타 배열 상,하,좌,우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static void harvest(int curr_row, int curr_col) {
		// 1. 종료조건 검색하려는 위치가 배열의 끝이라면
		if (curr_row == 0 || curr_row == field.length || curr_col == 0 || curr_col == field[0].length) {
			count += field[curr_row][curr_col];
			field[curr_row][curr_col] = 0;
			return;
		}
		// 2. 반복조건
		// 4방의 농작물들의 수익을 더한다.
		count += field[curr_row][curr_col];
		field[curr_row][curr_col] = 0;
		for (int k = 0; k < 4; k++) {
			int next_row = curr_row + dr[k];
			int next_col = curr_col + dc[k];
			// 배열의 범위를 벗어나면 다음 인덱스 탐색
			if (next_row < 0 || next_row >= field.length || next_col < 0 || next_col >= field[0].length
					|| visited[next_row][next_col] == true) {
				continue;
			}
			// 3. 재귀 전 바꿔야 할 것 배열의 범위 안이라면 농작물의 수익은 더하고 해당 걊을 0으로 만든다.
			count += field[next_row][next_col];
			field[next_row][next_col] = 0;
			visited[next_row][next_col] = true;

			// 4. 4방으로 재귀를 보낸다.
			harvest(next_row, next_col);
		}
		System.out.println(count + " " + curr_row + " " + curr_col);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			count = 0;
			int N = Integer.parseInt(br.readLine()); // 땅의 크기
			field = new int[N][N]; // 농작물의 개수 배열
			visited = new boolean[N][N];
			// 배열 초기화
			for (int i = 0; i < N; i++) {
				String temp = br.readLine();
				for (int j = 0; j < N; j++) {
					field[i][j] = temp.charAt(j) - 48;
				}
			}
			harvest(N / 2, N / 2);

			System.out.println("#" + tc + " " + count);
		}
	}

}
