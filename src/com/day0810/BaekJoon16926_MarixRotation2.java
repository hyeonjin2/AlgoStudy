package com.day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon16926_MarixRotation2 {

	// 회전 델타 배열 우,하,좌,상
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static Queue<Integer> list;
	static int[][] matrix;
	static int[][] result;
	static int N, M, R, size;
	static int dir;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // row 크기
		M = Integer.parseInt(st.nextToken()); // col 크기
		R = Integer.parseInt(st.nextToken()); // 회전 횟수
		// matrix 배열 초기화
		matrix = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result = new int[N][M];
		int cycle = Math.min(N, M) / 2;
		for (int i = 0; i < cycle; i++) {
			dir = 0;
			size = 2 * (N - i * 2 + M - i * 2) - 4;
			list = new ArrayDeque<>();
			setList(i, i, 0);
			rotation();
			getResult(i, i);
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(result[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	// matrix 배열을 회전한 숫자들로 채우는 함수
	private static void getResult(int row, int col) {
		if (list.isEmpty()) {
			return;
		}
		result[row][col] = list.poll();
		int next_row = row + dr[dir];
		int next_col = col + dc[dir];
		if (next_row < 0 || next_row >= N || next_col < 0 || next_col >= M||result[next_row][next_col] != 0) {
			dir = (dir + 1) % 4;
			next_row = row + dr[dir];
			next_col = col + dc[dir];
		}
		getResult(next_row, next_col);
	}

	// list를 회전시키는 함수
	private static void rotation() {
		for (int i = 0; i < R; i++) {
			int tmp = list.peek();
			list.poll();
			list.offer(tmp);
		}
	}

	// cycle의 숫자들을 담는 배열 list를 만드는 함수
	private static void setList(int row, int col, int cnt) {
		if (cnt == size) {
			return;
		}
		list.add(matrix[row][col]);
		matrix[row][col] = 0;
		int next_row = row + dr[dir];
		int next_col = col + dc[dir];
		if (next_row < 0 || next_row >= N || next_col < 0 || next_col >= M || matrix[next_row][next_col] == 0) {
			dir = (dir + 1) % 4;
			next_row = row + dr[dir];
			next_col = col + dc[dir];
		}
		setList(next_row, next_col, cnt + 1);
	}
}
