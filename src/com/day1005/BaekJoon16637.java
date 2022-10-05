package com.day1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 괄호 추가하기
public class BaekJoon16637 {

	static int R;
	static int[] order;
	static int[] numbers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String line = br.readLine();
		R = (N - 1) / 2; // 연산자의 수
		order = new int[R];
		numbers = new int[R + 1];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (line.charAt(i) - '0' > '0' && line.charAt(i) - '0' <= '9')
				numbers[cnt++] = line.charAt(i) - '0';
		}
		System.out.println(Arrays.toString(numbers));
//		perm(0, 0, 0);
	}

	private static void perm(int cnt, int flag, int result) {
		if (cnt == R) {
			return;
		}
		for (int i = 0; i < R; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			order[cnt] = i;
			perm(cnt + 1, flag | 1 << i, result);
		}
	}
}
