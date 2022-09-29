package com.day0929;

// 연습문제 1
public class PaintingAPT2 {

	public static void main(String[] args) {
		int N = 8;

		// 경우의 수
		int up_yellow = 0, up_blue = 0;
		int down_yellow = 1, down_blue = 1; // 1층부터 시작

		for (int i = 2; i <= N; i++) {
			up_yellow = down_yellow + down_blue;
			up_blue = down_yellow;

			down_yellow = up_yellow;
			down_blue = up_blue;
		}
		System.out.println(up_yellow + up_blue);

	}
}
