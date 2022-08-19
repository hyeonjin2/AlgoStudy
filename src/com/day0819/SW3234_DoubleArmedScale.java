package com.day0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW3234_DoubleArmedScale {

	static int N, Ans, total;
	static int[] weight;
	static int[] selected; // 왼쪽 저울에 올라갈 무게추들

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			// 무게추 배열 초기화
			weight = new int[N];
			Ans = 0;
			total = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
				total += weight[i];
			}
			selected = new int[N];
			perm(0, 0);

			sb.append("#").append(tc).append(" ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	static int tmp;

	private static void perm(int cnt, int flag) {
		if (cnt == N) {
			setting(0, 0, 0);
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			selected[cnt] = weight[i];
			perm(cnt + 1, flag | 1 << i);
		}
	}

	private static void setting(int cnt, int left, int right) {
		if (cnt == N) {
			Ans++;
			return;
		}
		setting(cnt + 1, left + selected[cnt], right);
		if (left >= right + selected[cnt]) {
			setting(cnt + 1, left, right + selected[cnt]);
		}
	}
}
