package com.day0816;

import java.util.Scanner;

public class BaekJoon2839_SugarDelivery {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int bag = 0;

		// 우선 5kg짜리 봉지로 해결할 수 있는지 체크
		while (N % 5 != 0 && N >= 0) {
			bag++; // 5kg 안되면 3kg 짜리로
			N -= 3;
		}

		// N이 음수이거나 N이 5로 나눠질 경우
		bag = N < 0 ? -1 : N / 5 + bag;
		System.out.println(bag);
	}
}
