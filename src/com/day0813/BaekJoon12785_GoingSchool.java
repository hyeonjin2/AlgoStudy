package com.day0813;

import java.util.Scanner;

public class BaekJoon12785_GoingSchool {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 배열의 크기
		int w = sc.nextInt();
		int h = sc.nextInt();
		// 토스트 집 좌표
		int x = sc.nextInt();
		int y = sc.nextInt();
		long[][] map = new long[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (i == 0 || j == 0) {
					map[i][j] = 1;
					continue;
				}
				map[i][j] = (map[i - 1][j] + map[i][j - 1]) % 1_000_007;
			}
		}
		System.out.println((map[y - 1][x - 1] * map[h - y][w - x]) % 1_000_007);
	}
}
