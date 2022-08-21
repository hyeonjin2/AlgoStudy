package com.day0814;

import java.util.Scanner;

public class BaekJoon16395 {

	static int[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		arr = new int[n + 1][k + 1];

		System.out.println(comb(n - 1, k - 1));
	}

	private static int comb(int n, int k) {
		if (n == k || k == 0) {
			arr[n][k] = 1;
			return arr[n][k];
		}
		if (arr[n][k] == 0) {
			arr[n][k] = comb(n - 1, k - 1) + comb(n - 1, k);
		}
		return arr[n][k];
	}
}
