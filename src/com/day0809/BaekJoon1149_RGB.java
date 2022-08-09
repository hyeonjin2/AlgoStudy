package com.day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1149_RGB {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] prices = new int[N + 1][4];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= 3; j++) {
				prices[0][j] = 0;
				prices[i][0] = 0;
			}
		}
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= 3; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (j == 1) {
					prices[i][1] = Math.min(num + prices[i - 1][2], num + prices[i - 1][3]);
				} else if (j == 2) {
					prices[i][2] = Math.min(num + prices[i - 1][1], num + prices[i - 1][3]);
				} else {
					prices[i][3] = Math.min(num + prices[i - 1][1], num + prices[i - 1][2]);
				}
			}
		}
		int result = Math.min(prices[N][1], prices[N][2]);
		result = Math.min(result, prices[N][3]);
		System.out.println(result);
	}
}
