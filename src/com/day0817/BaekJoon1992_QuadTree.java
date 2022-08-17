package com.day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon1992_QuadTree {

	static int[][] map;
	static String result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 배열 입력 받기
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		result = "";
		quadTree(N, 0, 0);
		System.out.println(result);
	}

	private static void quadTree(int size, int row, int col) {
		if (size == 1) {
			result += map[row][col] + "";
			return;
		}
		int start = map[row][col];
		boolean isDiff = false;
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (map[i][j] != start) {
					isDiff = true;
					break;
				}
			}
		}
		// 첫번째 숫자와 달라서 반복문을 빠져나온 경우
		if (isDiff) {
			result += "(";
			quadTree(size / 2, row, col);
			quadTree(size / 2, row, col + size / 2);
			quadTree(size / 2, row + size / 2, col);
			quadTree(size / 2, row + size / 2, col + size / 2);
			result += ")";
		} else { // 반복문을 다 마친 경우
			result += start + "";
		}
	}
}
