package com.day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1182_SubPermutation {

	static int N, S, totalCnt;
	static int[] numbers;
	static boolean[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		// 숫자들 배열에 저장
		numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		selected = new boolean[N];
		subSet(0);
		System.out.println(totalCnt);
	}

	private static void subSet(int index) {
		if (index == N) {
			int cnt = 0;
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (selected[i]) {
					sum += numbers[i];
					cnt++;
				}
			}
			if (sum == S && cnt >0)
				totalCnt++;
			return;
		}
		selected[index] = true;
		subSet(index + 1);
		selected[index] = false;
		subSet(index + 1);
	}
}
