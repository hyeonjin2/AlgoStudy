package com.day0823;

import java.util.Arrays;
import java.util.Scanner;

public class SW3289_Disjoint2 {

	static int[] parents;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();

			parents = new int[N + 1];

			// 1. makeSet
			Arrays.fill(parents, -1);

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");

			for (int i = 0; i < M; i++) {
				int c = sc.nextInt();
				int a = sc.nextInt();
				int b = sc.nextInt();

				switch (c) {
				case 0:
					union(a, b);
					break;
				case 1:
					sb.append((find(a) == find(b) ? "1" : "0"));
					break;
				}
			}
			System.out.println(sb);
		}
	}

	// find(a) : a가 속한 대표자 리턴
	private static int find(int a) {
		if (parents[a] == -1)
			return a;
		return parents[a] = find(parents[a]);
	}

	// union
	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa != pb) { // 서로 다른 그룹
			parents[pb] = pa;
			return true;
		}
		return false;
	}
}
