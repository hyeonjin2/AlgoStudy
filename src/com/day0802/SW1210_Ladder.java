package com.day0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1210_Ladder {
	static int[][] ladder;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			int T = Integer.parseInt(br.readLine());
			ladder = new int[100][100];
			StringTokenizer st;
			// 사다리 배열 초기화
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int idx_col = 0;

			// 도착점 찾기
			for (int i = 0; i < 100; i++) {
				if (ladder[99][i] == 2) {
					idx_col = i;
				}
			}
			int curr_row = 99;
			int curr_col = idx_col;
			boolean move_left = false;
			boolean move_right = false;
			while (curr_row >= 0) {
				if (curr_col - 1 >= 0 && ladder[curr_row][curr_col - 1] == 1 && !move_right) {
					move_left = true;
					curr_col--;
					continue;
				}
				if (curr_col + 1 < 100 && ladder[curr_row][curr_col + 1] == 1 && !move_left) {
					move_right = true;
					curr_col++;
					continue;
				} else {
					move_left = false;
					move_right = false;
					curr_row--;
				}
			}
			System.out.println("#" + tc + " " + curr_col);
		}

	}
}
