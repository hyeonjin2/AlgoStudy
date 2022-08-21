package com.day0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon6603_Lotto {

	static int[] input;
	static int[] numbers;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while (true) {
			String str = br.readLine();
			String[] tmp = str.split(" ");
			// 입력으로 0이 들어오면 종료
			int size = Integer.parseInt(tmp[0]);
			if (size == 0) {
				break;
			}
			// 입력 숫자 받기
			input = new int[size];
			for (int i = 0; i < size; i++) {
				input[i] = Integer.parseInt(tmp[i + 1]);
			}
			// 집합 경우의 수 뽑으러 가기
			numbers = new int[input.length];
			comb(0, 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void comb(int cnt, int start) {
		if (cnt == 6) {
			for (int i = 0; i < 6; i++) {
				sb.append(numbers[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = start; i < input.length; i++) {
			numbers[cnt] = input[i];
			comb(cnt + 1, i + 1);
		}
	}
}
