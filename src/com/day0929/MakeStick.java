package com.day0929;

public class MakeStick {

	public static void main(String[] args) {
		// 2*(현재길이-1)의 방법의 수+(현재길이-2)의 방법의 수
		// 2f(n-1)+f(n-2)
		int N = 6;

		int[] d = new int[N + 1];
		d[0] = 1;
		d[1] = 2;
		for (int i = 2; i <= N; i++) {
			d[i] = 2 * d[i - 1] + d[i - 2];
		}
		System.out.println(d[N]);
	}

}
