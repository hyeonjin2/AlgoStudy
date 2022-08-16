package com.day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BaekJoon1018_ChessBoard {

	static int N, M;
	static int totalCnt;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 배열 크기 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M]; // 입력받을 배열
		// 배열 초기화
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		totalCnt = Integer.MAX_VALUE;
		for (int i = 0; i <= N - 8; i++) {// 10 13 -> row:0 1 2, col:0 1 2 3 4 5 만 검사
			for (int j = 0; j <= M - 8; j++) {
				int cnt = calc(i, j);
				totalCnt = totalCnt > cnt ? cnt : totalCnt;
			}
		}
		System.out.println(totalCnt);

	}

	private static int calc(int start_row, int start_col) {
		String[] board = { "WBWBWBWB", "BWBWBWBW" }; // 둘 중에 하나여야 함
		int whiteCnt = 0;
		for (int i = 0; i < 8; i++) {
			int row = start_row + i;
			for (int j = 0; j < 8; j++) {
				int col = start_col + j;
				if (map[row][col] != board[row % 2].charAt(j)) {
					whiteCnt++;
				}
			}
		}
		return Math.min(whiteCnt, 64 - whiteCnt);
	}
}
