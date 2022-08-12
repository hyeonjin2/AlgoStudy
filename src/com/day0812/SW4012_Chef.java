package com.day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW4012_Chef {
	static int N, result, R;
	static boolean[] isSelected;
	static int[] selected1; // 음식1을 만드는 재료를 담는 배열
	static int[] selected2; // 음식2를 만드는 재료를 담는 배열
	static int[][] table;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			R = N / 2;
			// 식재료 table 입력 받기
			table = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					table[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 입력 확인
//			print(table);
			isSelected = new boolean[N];
			selected1 = new int[N / 2];
			selected2 = new int[N / 2];
			result = Integer.MAX_VALUE;
			comb(0, 0);
			System.out.println("#" + tc + " " + result);
		}
	}

//	private static void subSet(int index) {
//		if (index == N) {
//			int count = 0;
//			// N/2개 뽑혔는지 확인하기
//			for (int i = 0; i < N; i++) {
//				if (isSelected[i])
//					count++;
//			}
//			if (count != N / 2) {
//				return;
//			}
//			// N/2개 뽑힌 경우 음식 재료 배열 만들기
//			int count1 = 0;
//			int count2 = 0;
//			for (int i = 0; i < N; i++) {
//				if (!isSelected[i]) {
//					selected2[count2++] = i;
//				} else {
//					selected1[count1++] = i;
//				}
//			}
//			result = result > calc() ? calc() : result;
//			return;
//		}
//		isSelected[index] = true;
//		subSet(index + 1);
//		isSelected[index] = false;
//		subSet(index + 1);
//
//	}

	private static void comb(int cnt, int start) {
		if (cnt == R) {
			for (int i = start; i < N; i++) {
				selected2[i - cnt] = i;
			}
			result = result > calc() ? calc() : result;
			return;
		}
		for (int i = start; i < N; i++) {
			if (i - cnt >= R) {
				break;
			}
			selected1[cnt] = i;
			comb(cnt + 1, i + 1);
			selected2[i - cnt] = i;
		}
	}

	private static int calc() {
		int sum1 = 0;
		int sum2 = 0;
		for (int i = 0; i < N / 2; i++) {
			for (int j = i + 1; j < N / 2; j++) {
				sum1 += table[selected1[i]][selected1[j]] + table[selected1[j]][selected1[i]];
				sum2 += table[selected2[i]][selected2[j]] + table[selected2[j]][selected2[i]];
			}
		}
		return Math.abs(sum1 - sum2);
	}
}
