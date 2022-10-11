package com.day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 괄호 추가하기
public class BaekJoon16637 {

	static int N, Ans;
	static char[] operator;
	static int[] number;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		number = new int[N / 2 + 1];
		operator = new char[N / 2];
		int ind1 = 0;
		int ind2 = 0;
		String str = br.readLine();
		for (int i = 0; i < N; i++) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				number[ind1++] = c - '0';
			} else {
				operator[ind2++] = c;
			}
		}
		Ans = Integer.MIN_VALUE;
		setBracket(0, number[0]);
		System.out.println(Ans);
	}

	private static void setBracket(int index, int result) {
		if (index >= N / 2) {
			Ans = Math.max(Ans, result);
			return;
		}
		// 괄호를 추가하지 않을 때->이전 값 계산해서 넘기기
		setBracket(index + 1, calc(result, number[index + 1], operator[index]));
		// 괄호를 추가할 때->sum의 오른쪽을 계산한 후 sum과 다시 연산해 넘기기
		if (index + 1 < N / 2) {
			int temp = calc(number[index + 1], number[index + 2], operator[index + 1]);
			// 오른쪽 연산자를 계산했으므로 그 다음 연산자로 간다.
			setBracket(index + 2, calc(result, temp, operator[index]));
		}
	}

	private static int calc(int a, int b, char op) {
		int result = a;
		if (op == '+') {
			result += b;
		} else if (op == '-') {
			result -= b;
		} else {
			result *= b;
		}
		return result;
	}
}
