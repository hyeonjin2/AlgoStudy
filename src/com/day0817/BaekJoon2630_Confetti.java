package com.day0817;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BaekJoon2630_Confetti {

	static int[][] map;
	static int N;
	static int whiteCnt, blueCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("공간만들기_input.txt"));
		int N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		searchMap(N, 0, 0);
		System.out.println(whiteCnt);
		System.out.println(blueCnt);
	}

	// size : 배열의 크기, row,col : 탐색 시작 인덱스
	private static void searchMap(int size, int row, int col) {
		if (size == 1) {
			if (map[row][col] == 0) {
				whiteCnt++;
			} else {
				blueCnt++;
			}
			return;
		}
		int startColor = map[row][col];
		boolean isDiff = false;
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				// 시작인덱스의 색과 다르면 반복문 빠져나옴
				if (map[i][j] != startColor) {
					isDiff = true;
					break;
				}
			}
		}
		// 색이 달라서 반복문을 나온 경우 size/2 해서 재귀
		if (isDiff) {
			searchMap(size / 2, row, col);
			searchMap(size / 2, row + size / 2, col);
			searchMap(size / 2, row, col + size / 2);
			searchMap(size / 2, row + size / 2, col + size / 2);
		}
		// if문에 걸리지 않고 반복문이 끝난 경우
		else if (startColor == 0) {
//			System.out.println("===white===");
//			System.out.println(row + " " + col + " " + size);
			whiteCnt++;
		} else if (startColor == 1) {
//			System.out.println("===blue===");
//			System.out.println(row + " " + col + " " + size);
			blueCnt++;
		}
		return;
	}

}
