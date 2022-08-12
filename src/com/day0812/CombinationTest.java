package com.day0812;

import java.util.Arrays;

public class CombinationTest {

	static int N, R;
	static int[] selected1;
	static int[] selected2;

	public static void main(String[] args) {
		N = 4;
		R = 2;
		selected1 = new int[R];
		selected2 = new int[R];
		comb(0, 0);
	}

	private static void comb(int cnt, int start) {
		if (cnt == R) {
			for (int i = start; i < N; i++) {
				selected2[i - cnt] = i;
			}
			System.out.println(Arrays.toString(selected1) + " " + Arrays.toString(selected2));
			return;
		}
		for (int i = start; i < N; i++) {
			if (i - cnt >= R) {
				break;
			}
			selected1[cnt] = i;
			comb(cnt + 1, i + 1);
			selected2[i - cnt] = i;
		}
	}
}
