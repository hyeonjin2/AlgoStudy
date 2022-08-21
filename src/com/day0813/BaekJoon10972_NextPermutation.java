package com.day0813;

import java.util.Scanner;

public class BaekJoon10972_NextPermutation {
	static int N;
	static int[] numbers;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = sc.nextInt();
		}
		StringBuilder sb = new StringBuilder();
		if (!np()) {
			System.out.println(-1);
		} else {
			for (int i = 0; i < N; i++) {
				sb.append(numbers[i] + " ");
			}
			System.out.println(sb);
		}
	}

	private static boolean np() {
		// 꼭대기 i찾기
		int i = N - 1;
		while (i > 0 && numbers[i - 1] >= numbers[i]) {
			i--;
		}
		// 큰 수인 꼭대기가 0번쨰 인덱스면 다음 순열이 없음.
		if (i == 0)
			return false;
		// i-1과 바꿀 j 찾기
		int j = N - 1;
		while (numbers[i - 1] >= numbers[j]) {
			j--;
		}
		// i-1과 j바꾸기
		swap(i - 1, j);
		// i~N-1까지 자리 바꾸기
		int k = N - 1;
		while (i < k) {
			swap(i++, k--);
		}
		return true;
	}

	private static void swap(int i, int j) {
		int tmp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = tmp;
	}
}
