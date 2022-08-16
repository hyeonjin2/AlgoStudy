package com.day0816;

import java.util.Scanner;

public class BaekJoon1074_Z {

	static int row, col, cnt;
	static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 배열의 크기 2^N * 2^N
		row = sc.nextInt();
		col = sc.nextInt();
		int size = (int) Math.pow(2, N);
		zSearch(size, row, col);
		System.out.println(cnt);
	}

	private static void zSearch(int size, int row, int col) {
		if (size == 1)
			return;
		int tmp = size / 2;
		if (row < tmp && col < tmp) {
			zSearch(tmp, row, col);
		} else if (row < tmp && col >= tmp) {
			cnt += size * size / 4;
			zSearch(tmp, row, col - tmp);
		} else if (row >= tmp && col < tmp) {
			cnt += size * size / 4 * 2;
			zSearch(tmp, row - tmp, col);
		} else if (row >= tmp && col >= tmp) {
			cnt += size * size / 4 * 3;
			zSearch(tmp, row - tmp, col - tmp);
		}

	}

}
