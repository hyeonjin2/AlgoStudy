package com.day1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 숫자 만들기
public class SW4008 {
	static int N, max, min, Ans;
	static int[] operators, numbers, order;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			operators = new int[4];
			numbers = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operators[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}

			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			order = new int[N - 1];
			perm(0);
			Ans = Math.abs(max - min);
			sb.append("#").append(tc).append(" ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void perm(int cnt) {
		if (cnt == N - 1) {
			int result = calc();
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (operators[i] > 0) {
				order[cnt] = i;
				operators[i]--;
				perm(cnt + 1);
				operators[i]++;
			}
		}
	}

	private static int calc() {
		int result = numbers[0];
		for (int i = 0; i < N - 1; i++) {
			int oper = order[i];
			if (oper == 0) {
				result = result + numbers[i + 1];

			} else if (oper == 1) {
				result = result - numbers[i + 1];

			} else if (oper == 2) {
				result = result * numbers[i + 1];

			} else if (oper == 3) {
				result = result / numbers[i + 1];
			}
		}
		return result;
	}
}
