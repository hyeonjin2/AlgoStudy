package com.day0813;

import java.util.Scanner;

public class BaekJoon10973_PrePermutation {

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
		if (!pp()) {
			System.out.println(-1);
		} else {
			for (int i = 0; i < N; i++) {
				sb.append(numbers[i] + " ");
			}
			System.out.println(sb);
		}
	}

	private static boolean pp() {
		// 꼭대기 i찾기
		int i = N - 1;
		while (i > 0 && numbers[i - 1] <= numbers[i]) {
			i--;
		}
		if (i == 0) {
			return false;
		}
		// i-1와 바꿀 j찾기
		int j = N - 1;
		while (numbers[i - 1] <= numbers[j]) {
			j--;
		}
		// i와 j바꾸기
		swap(i - 1, j);
		// i~N-1정렬하기
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
