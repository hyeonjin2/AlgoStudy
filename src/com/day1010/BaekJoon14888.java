package com.day1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연산자 끼워넣기
public class BaekJoon14888 {

	static long Ans, min, max;
	static int N;
	static long[] num;
	static int[] op;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		op = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		max = Long.MIN_VALUE;
		min = Long.MAX_VALUE;
		perm(0, num[0]);
		System.out.println(max);
		System.out.println(min);
	}

	private static void perm(int cnt, long result) {
		if (cnt == N - 1) {
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}
		for (int i = 0; i < 4; i++) {
			// 연산자의 수가 남아있다면
			if (op[i] > 0) {
				// 연산해보기
				op[i]--;
				perm(cnt + 1, calc(result, num[cnt + 1], i));
				op[i]++;
			}
		}
	}

	private static long calc(long a, long b, int i) {
		if (i == 0) {
			return a + b;
		} else if (i == 1) {
			return a - b;
		} else if (i == 2) {
			return a * b;
		} else if (i == 3) {
			return a / b;
		}
		return 0;
	}
}
