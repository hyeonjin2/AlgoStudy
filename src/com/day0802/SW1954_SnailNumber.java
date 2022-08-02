package com.day0802;

import java.util.Scanner;

public class SW1954_SnailNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 배열의 크기
			if (N == 1) {
				System.out.println("#" + tc);
				System.out.println(1);
				continue;
			}
			int[][] arr = new int[N][N];
			// 숫자가 들어가는 방향 우,하,좌,상
			int[] dr = { 0, 1, 0, -1 };
			int[] dc = { 1, 0, -1, 0 };
			// 처음 방향 우 : dr[0],dc[0]
			int curr = 0;
			int next_row = 0;
			int next_col = 0;
			int row = 0;
			int col = 0;
			for (int i = 0; i < N * N; i++) {
				arr[row][col] = i + 1;
				// 현재 진행방향으로 이동
				next_row = row + dr[curr];
				next_col = col + dc[curr];

				// 다음 이동이 배열을 벗어나면 방향 전환
				if (next_row < 0 || next_row >= N || next_col < 0 || next_col >= N) {
					// 방향 전환
					curr = (curr + 1) % 4; // 0,1,2,3(우,하,좌,상)순으로 방향 전환
					// 이동
					next_row = row + dr[curr];
					next_col = col + dc[curr];
				}
				// 다음 이동에 배열값이 있을 경우 방향 전환
				if (arr[next_row][next_col] != 0) {
					// 방향 전환
					curr = (curr + 1) % 4; // 0,1,2,3(우,하,좌,상)순으로 방향 전환
					// 이동
					next_row = row + dr[curr];
					next_col = col + dc[curr];
				}
				row = next_row;
				col = next_col;
			}
			System.out.println("#" + tc);
			// 달팽이 숫자 출력
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
