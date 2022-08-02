package com.day0802;

import java.util.Scanner;

public class BeakJoon1913_Snail {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 배열의 크기
		int num = sc.nextInt(); // 찾고자 하는 수
		int[][] arr = new int[N][N];
		StringBuilder sb = new StringBuilder();
		// 숫자가 저장되는 순서 하,우,상,좌
		int[] dr = { 1, 0, -1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		int curr = 0;
		int next_row;
		int next_col;
		int row = 0;
		int col = 0;
		int idx_r = 0;
		int idx_c = 0;
		for (int i = N * N; i >= 1; i--) {
			arr[row][col] = i;
			if (num == i) {
				idx_r = row;
				idx_c = col;
			}
			// 다음으로 이동
			next_row = row + dr[curr];
			next_col = col + dc[curr];
			// 배열의 범위를 벗어나면
			if (next_row < 0 || next_row >= N || next_col < 0 || next_col >= N) {
				// 방향 전환
				curr = (curr + 1) % 4; // 0,1,2,3 -> 하,우,상,좌 순으로 방향 전환
				// 다음으로 이동
				next_row = row + dr[curr];
				next_col = col + dc[curr];
			}
			// 만약 배열에 값이 있다면
			if (arr[next_row][next_col] != 0) {
				// 방향 전환
				curr = (curr + 1) % 4; // 0,1,2,3 -> 하,우,상,좌 순으로 방향 전환
				// 다음으로 이동
				next_row = row + dr[curr];
				next_col = col + dc[curr];
			}
			row = next_row;
			col = next_col;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
		System.out.println(++idx_r + " " + ++idx_c);
	}
}
