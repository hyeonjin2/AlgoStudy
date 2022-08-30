package com.day0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_No3_Card {
	static int N, min, Ans;
	static int[] cards;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			cards = new int[N];
			for (int i = 0; i < N; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}
			min = Integer.MAX_VALUE;
			dfs(0, cards);
			Ans = min > 5 ? -1 : min;
			sb.append("#").append(tc).append(" ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int cnt, int[] cards) {
		if (cnt > 5)
			return;
		if (cnt >= min)
			return;
		// 1. 오름차순, 내림차순인지 검사
		if (isSorted(cards)) {
			min = Math.min(min, cnt);
			return;
		}

		// 2. 셔플을 위해 카드 나누기
		int[] left = new int[N / 2];
		int[] right = new int[N / 2];

		for (int i = 0; i < N; i++) {
			if (i < N / 2)
				left[i] = cards[i];
			else
				right[i - N / 2] = cards[i];
		}

		// 3. 카드 셔플
		for (int x = 0; x < N; x++) {
			int[] next = x < N / 2 ? shuffle(x, left, right) : shuffle(x - N / 2, right, left);
			dfs(cnt + 1, next);
		}
	}

	// 정렬이 되었는지 검사하는 메소드
	private static boolean isSorted(int[] cards) {
		boolean up = true;
		boolean down = true;
		for (int i = 0; i < N; i++) {
			if (cards[i] != i + 1)
				up = false;
			if (cards[i] != N - i)
				down = false;

			if (!up && !down)
				return false;
		}
		return true;
	}

	private static int[] shuffle(int x, int[] left, int[] right) {
		int[] next = new int[N];
		int idx = 0;
		int leftIdx = 0;
		int rightIdx = 0;

		while (leftIdx < N / 2 - x) {
			next[idx++] = left[leftIdx++];
		}

		int order = 0;
		while (leftIdx < N / 2 && rightIdx < N / 2) {
			next[idx++] = order % 2 == 0 ? right[rightIdx++] : left[leftIdx++];
			order++;
		}

		while (rightIdx < N / 2) {
			next[idx++] = right[rightIdx++];
		}

		return next;
	}
}
