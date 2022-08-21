package com.day0814;

import java.util.HashSet;
import java.util.Scanner;

public class BaekJoon16922_MakeRomeNumber {

	static int r;
	static int[] input;
	static int[] numbers;
	static int totalCnt;
	static HashSet<Integer> set;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt();
		input = new int[] { 1, 5, 10, 50 };
		numbers = new int[r];
		set = new HashSet<>();
		totalCnt = 0;
		comb(0, 0, 0);
		System.out.println(set.size());
	}

	private static void comb(int cnt, int start, int sum) {
		if (cnt == r) {
			set.add(sum);
			return;
		}
		for (int i = start; i < 4; i++) {
			comb(cnt + 1, i, sum + input[i]);
		}
	}
}
