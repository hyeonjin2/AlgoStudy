package com.day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2563_ColorPaper {

	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[100][100];
		int num = Integer.parseInt(br.readLine());
		int row;
		int col;
		// 행, 열 인덱스 배열에 담기
		for (int i = 0; i < num; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			for (int j = row; j < row + 10; j++) {
				for (int k = col; k < col + 10; k++) {
					if (map[j][k] != 1) {
						map[j][k] = 1;
					}
				}
			}
		}
		int result = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				result += map[i][j];
			}
		}
		System.out.println(result);
	}
}
