package com.day0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SW2805_Crops {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 땅의 크기
			int[][] field = new int[N][N]; // 농작물의 개수 배열
			// 배열 초기화
			for (int i = 0; i < N; i++) {
				String temp = br.readLine();
				for (int j = 0; j < N; j++) {
					field[i][j] = temp.charAt(j) - 48;
				}
			}
			int result = 0;
			for (int i = 0; i < N; i++) {
				if (i <= N / 2) {
					for (int j = N / 2 - i; j <= N / 2; j++) {
						result += field[i][j];
						System.out.println(i + " " + j);
					}
					for (int j = N / 2 + 1; j <= N / 2 + i; j++) {
						result += field[i][j];
						System.out.println(i + " " + j);
					}
				}
				if (i > N / 2) {
					for (int j = i - N / 2; j <= N / 2; j++) {
						result += field[i][j];
						System.out.println(i + " " + j);
					}
					for (int j = N / 2 + 1; j < N +N/2-i; j++) {
						result += field[i][j];
						System.out.println(i + " " + j);
					}
				}
			}

			System.out.println("#" + tc + " " + result);
		}
	}

}
