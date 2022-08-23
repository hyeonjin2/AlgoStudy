package com.day0823;

import java.util.Scanner;

public class BaekJoon15649_NM {

	static int N, K;
	static int[] selected;
	static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		selected = new int[K];
		sb = new StringBuilder();
		perm(0, 0);
		System.out.println(sb);
	}

	private static void perm(int cnt, int flag) {
		if (cnt == K) {
			for (int i = 0; i < K; i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 1; i <= N; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			selected[cnt] = i;
			perm(cnt + 1, flag | 1 << i);
		}
	}
}
