package com.day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1149_RGB2 {
	static int N;
	static int sum;
	static int result;
	static int[] selected;
	static int[][] prices;
	static int totalCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		prices = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				prices[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		sum = 0;
		result = Integer.MAX_VALUE;
		selected = new int[N];
		totalCnt = 0;
		comb(0, -1);
		System.out.println(totalCnt);
		System.out.println(result);
	}

	private static void comb(int cnt, int pre_idx) {
		sum = 0;
		if (cnt == N) {
			for (int i = 0; i < N; i++) {
				sum += selected[i];
			}
			result = Math.min(result, sum);
			System.out.println(Arrays.toString(selected));
			totalCnt++;
			return;
		}
		for (int i = cnt; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == pre_idx) {
					continue;
				}
				selected[cnt] = prices[cnt][j];
				comb(cnt + 1, j);
			}
		}
	}
}
