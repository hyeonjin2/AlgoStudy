package com.day0818;

import java.util.Scanner;

public class BaekJoon11051 {

	static long[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		map = new long[n + 1][k + 1];
		System.out.println(comb(n, k));
	}

	private static long comb(int n, int k) {
		if (n == k || k == 0) {
			map[n][k] = 1;
			return map[n][k];
		}
		if (map[n][k] == 0) {
			map[n][k] = (comb(n - 1, k - 1) % 10_007 + comb(n - 1, k) % 10_007) % 10_007;
		}
		return map[n][k];
	}
}
