package com.day0817;

import java.util.Scanner;

public class BaekJoon1074_Z {

	static int N, r, c, cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();

		int length = (int) Math.pow(2, N);
		find(length, 0, 0);
	}

	private static void find(int n, int x, int y) {
		// 종료조건 -> 찾았을 때
		if (x == r && y == c) {
			System.out.println(cnt);
			return;
		}
		// 아직 못찾은 경우 -> 범위찾기
		// 찾는 값(r,c)이 현재위치(x,y)보다 뒤에 있으면서 탐색하려는 범이 안에 있다면 -> 범위 좁혀 들어가기
		if (x <= r && y <= c && r < (x + n) && c < (y + n)) {
			int half = n / 2; // half는 결국 1이 됨
			find(half, x, y); // 1사분면
			find(half, x, y + half); // 2사분면
			find(half, x + half, y); // 3사분면
			find(half, x + half, y + half); // 4사분면
		} else {// 찾는 값(r,c)이 현재위치(x,y)보다 뒤에 있긴 하지만 탐색하려는 범위 안에 없다면 다른 영역에 있음.
			cnt += n * n; // 현재 조사하려던 영역의 크기 만큼 누적 시킴.
		}
	}
}
