package com.day0929;

// 연습문제 1
public class PaintingAPT {

	public static void main(String[] args) {
		int N = 8;
		int[][] d = new int[N + 1][2];
		int yellow = 0, blue = 1;

		// 초기값
		d[1][yellow] = 1;
		d[1][blue] = 1;

		for (int i = 2; i <= N; i++) {
			// i층을 노란색으로 칠할 수 있는 경우의 수:아래층이 노란색인 경우+아래층이 파란색인 경우
			d[i][yellow] = d[i - 1][yellow] + d[i - 1][blue];
			// i층을 파란색으로 칠할 수 있는 경우의 수:아래층이 노란색인 경우
			d[i][blue] = d[i - 1][yellow];
		}
		System.out.println(d[N][yellow] + d[N][blue]);
	}
}
