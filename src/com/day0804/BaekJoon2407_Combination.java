package com.day0804;

import java.math.BigInteger;
import java.util.Scanner;

public class BaekJoon2407_Combination {
	static BigInteger[][] numbers;

	static BigInteger comb(int n, int r) {
		// 1. 종료조건 n==r 또는 n==0 이면 1리턴
		if (n == r || r == 0) {
			numbers[n][r] = BigInteger.ONE;
			return numbers[n][r];
		}
		if (numbers[n][r] == null) {
			BigInteger tmp1 = comb(n - 1, r);
			BigInteger tmp2 = comb(n - 1, r - 1);

			numbers[n][r] = tmp1.add(tmp2);
		}
		return numbers[n][r];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int R = sc.nextInt();
		numbers = new BigInteger[N + 1][R + 1];
		comb(N, R);
		System.out.println(numbers[N][R]);
	}
}
