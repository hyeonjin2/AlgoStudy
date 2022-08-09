package com.day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW1223_OperatorCheck {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine()); // 트리를 구성하는 전체 노드 수
			int result = 0; // 답

			for (int i = 0; i < N; i++) { // 모든 노드에 대해서
				char[] str = br.readLine().toCharArray(); // 1 - 2 3
				// N = 7, N/2=>3
				if (result == 0)
					continue;
				if (i < N / 2) { // 내부노드 -> 연산자이어야 된다.
					if (str[1] == '+' || str[1] == '-' || str[1] == '/' || str[1] == '*') {
						result = 1;
					} else {
						result = 0;
					}
				} else { // 리프노드 -> 숫자여야 된다.
					if (str[1] == '+' || str[1] == '-' || str[1] == '/' || str[1] == '*') {
						result = 0;
					} else {
						result = 1;
					}
				}
			}
			System.out.println("#" + tc + " " + result);
		}
	}
}
