package com.day0929;

import java.util.Scanner;

// 1로 만들기
public class BaekJoon1463 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] d = new int[N + 1];
		d[1] = 0;
		for (int i = 2; i <= N; i++) {
			if (i % 3 != 0 && i % 2 != 0) {
				d[i] = d[i - 1] + 1;
			} else if (i % 3 != 0 && i % 2 == 0) {
				d[i] = Math.min(d[i / 2] + 1, d[i - 1] + 1);
			} else if (i % 2 != 0 && i % 3 == 0) {
				d[i] = Math.min(d[i / 3] + 1, d[i - 1] + 1);
			} else {
				d[i] = Math.min(d[i / 3] + 1, d[i / 2] + 1);
				d[i] = Math.min(d[i], d[i - 1] + 1);
			}
		}
		System.out.println(d[N]);
	}
}
