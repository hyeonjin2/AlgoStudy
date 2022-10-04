package com.day1001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 차이를 최대로
public class BaekJoon10819 {

	static int N, Ans;
	static int[] inputs, numbers;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		inputs = new int[N];
		for (int i = 0; i < N; i++) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}
		numbers = new int[N];
		perm(0, 0);
		System.out.println(Ans);
	}

	private static void perm(int cnt, int flag) {
		if (cnt == N) {
			int ans = calc();
			Ans = Math.max(Ans, ans);
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			numbers[cnt] = inputs[i];
			perm(cnt + 1, flag | 1 << i);
		}
	}

	private static int calc() {
		int result = 0;
		for (int i = 0; i < N - 1; i++) {
			result += Math.abs(numbers[i] - numbers[i + 1]);
		}
		return result;
	}
}
