package com.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon3040_SnowWhite {

	static int[] dwarfs;
	static boolean[] isSelected;
	static int[] result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dwarfs = new int[9];
		// 난쟁이가 9명이므로 9개의 숫자 입력받기
		for (int i = 0; i < 9; i++) {
			dwarfs[i] = Integer.parseInt(br.readLine());
		}
		isSelected = new boolean[9];
		result = new int[7];
		subSet(0);
		for (int i = 0; i < 7; i++) {
			System.out.println(result[i]);
		}
	}

	private static void subSet(int index) {
		if (index == 9) {
			check();
			return;
		}
		isSelected[index] = true;
		subSet(index + 1);
		isSelected[index] = false;
		subSet(index + 1);

	}

	private static void check() {
		int sum = 0;
		int count = 0;
		for (int i = 0; i < 9; i++) {
			if (isSelected[i]) {
				sum += dwarfs[i];
				count++;
			}
		}
		if (sum == 100 && count == 7) {
			count = 0;
			for (int i = 0; i < 9; i++) {
				if (isSelected[i]) {
					result[count++] = dwarfs[i];
				}
			}
		}
	}
}
