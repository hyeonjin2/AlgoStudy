package com.day0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon9081_MakeWord {

	static char[] words;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		words = new char[n];
		StringBuilder sb = new StringBuilder();
		// 넥퍼로 찾기
		for (int i = 0; i < n; i++) {
			words = br.readLine().toCharArray();
			boolean exit = np();
			System.out.println(words);
		}
	}

	private static boolean np() {
		int N = words.length;
		// 꼭대기 i 찾기
		int i = N - 1;
		while (i > 0 && words[i - 1] >= words[i]) {
			i--;
		}
		if (i == 0) {
			return false;
		}
		// i-1과 바꿀 j 찾기
		int j = N - 1;
		while (words[i - 1] >= words[j]) {
			j--;
		}
		// i-1과 j 자리 바꾸기
		swap(i - 1, j);
		// i ~ N-1까지 오름차순 정렬
		int k = N - 1;
		while (i < k) {
			swap(i++, k--);
		}
		return true;
	}

	private static void swap(int i, int j) {
		char tmp = words[i];
		words[i] = words[j];
		words[j] = tmp;
	}
}
