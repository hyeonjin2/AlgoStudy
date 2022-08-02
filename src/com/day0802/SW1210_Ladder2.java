package com.day0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1210_Ladder2 {
	static int[][] ladder;
	static boolean move_left = false;
	static boolean move_right = false;
	static int result = 0;

	static void move(int curr_row, int curr_col) {
		// 1. 종료 조건
		if (curr_row == 0) {
			result = curr_col;
			return;
		}
		// 2. 반복 조건
		// 왼쪽으로 가는 경우
		if (curr_col - 1 >= 0 && ladder[curr_row][curr_col - 1] == 1 && !move_right) {
			move_left = true;
			move(curr_row, curr_col - 1);
		// 오른쪽으로 가는 경우
		} else if (curr_col + 1 <= 99 && ladder[curr_row][curr_col + 1] == 1 && !move_left) {
			move_right = true;
			move(curr_row, curr_col + 1);
		} else { // 왼쪽, 오른쪽 둘다 갈 수 없는 경우 -> 위로
			move_left = false;
			move_right = false;
			move(curr_row - 1, curr_col);
		}
	}

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
			move(99, idx_col);
			System.out.println("#" + tc + " " + result);
		}

	}
}
