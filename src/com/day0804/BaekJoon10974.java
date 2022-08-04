package com.day0804;

import java.util.Scanner;

public class BaekJoon10974 {
	static int N;
	static boolean[] isSelected;
	static int[] numbers;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		numbers = new int[N]; // 순열을 저장할 배열
		isSelected = new boolean[N + 1]; // 중복체크 배열

		perm(0);
		System.out.print(sb);
	}

	// nPn을 구하는 함수
	static void perm(int cnt) {
		if (cnt == N) {
			for (int i = 0; i < N; i++) {
				sb.append(numbers[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (isSelected[i])
				continue;
			numbers[cnt] = i;
			isSelected[i] = true;
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}
}
