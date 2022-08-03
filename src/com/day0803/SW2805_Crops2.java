package com.day0803;

import java.util.Scanner;

public class SW2805_Crops2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[][] field = new int[N][N];
			int count = 0;
			// 배열 초기화
			for (int i = 0; i < N; i++) {
				String str = sc.next();
				for (int j = 0; j < N; j++) {
					field[i][j] = str.charAt(j) - 48;
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = Math.abs(i - N / 2); j < N - Math.abs(i - N / 2); j++) {
					count += field[i][j];
				}
			}
			System.out.println("#" + tc + " " + count);
		}
	}
}
