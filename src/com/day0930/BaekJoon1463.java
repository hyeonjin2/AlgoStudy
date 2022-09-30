package com.day0930;

import java.util.Scanner;

// 1로 만들기
// d[n]:n을 1로 만들 때 필요한 최소 연산 횟수
public class BaekJoon1463 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		// n을 1로 만들 때 필요한 최소 연산 횟수를 저장할 배열
		int[] d = new int[n + 1];

		// 초기값
		d[1] = 0;

		for (int i = 2; i <= n; i++) {
			// 1. n-1의 연산의 경우
			d[i] = d[i - 1] + 1;

			// 2. n/2의 연산의 경우
			if (i % 2 == 0) {
				d[i] = Math.max(d[i], d[i / 2] + 1);
			}
			
			// 3. n/3의 연산의 경우
			if (i % 3 == 0) {
				d[i] = Math.max(d[i], d[i / 3] + 1);
			}
		}
	}
}
