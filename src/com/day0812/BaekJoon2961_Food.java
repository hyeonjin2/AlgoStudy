package com.day0812;

import java.util.Scanner;

// nCr : r값이 계속 변함
public class BaekJoon2961_Food {

	static int n, r;
	static int[] sour, bitter; // 재료별 신맛, 쓴맛 값
	static int[] numbers; // 선택된 재료번호 저장
	static int min = Integer.MAX_VALUE; // 답

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		sour = new int[n]; // 신맛
		bitter = new int[n]; // 쓴맛
		numbers = new int[n];

		for (int i = 0; i < n; i++) {
			sour[i] = sc.nextInt();
			bitter[i] = sc.nextInt();
		}

		for (int i = 1; i <= n; i++) { // 뽑는 재료 개수
			r = i;
			combi(0, 0);
		}
		System.out.println(min);
	}

	private static void combi(int cnt, int start) {
		if (cnt == r) {
			int sum = 0; // 쓴맛
			int mul = 1; // 신맛

			for (int i = 0; i < r; i++) {
				mul *= sour[numbers[i]];
				sum += bitter[numbers[i]];
			}

			min = Math.min(min, Math.abs(sum - mul));
			return;
		}
		for (int i = 0; i < n; i++) {
			numbers[cnt] = i;
			combi(cnt + 1, i + 1);

		}
	}
}
