package com.day0803;

import java.util.Scanner;

public class BaekJoon1010_Bridge {

	static int[][] calced;

	static int calc(int n, int c) { // nCm = n-1C1+n-1Cm-1
		// 1. 종료조건
		if (n == c || c == 0)
			calced[n][c] = 1;
		// 2. 반복조건 한 번도 계산하지 않은 경우
		if (calced[n][c] == 0) {
			calced[n][c] = calc(n - 1, c) + calc(n - 1, c - 1);
		}
		// 3. 계산된 경우
		return calced[n][c];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int c = sc.nextInt(); // 작은 거
			int n = sc.nextInt(); // 큰 거
			calced = new int[n + 1][c + 1];
			System.out.println(calc(n, c));
		}
	}
}
