package com.day0828;

import java.util.Scanner;

public class BaekJoon9663_NQueen {

	static int N, totalCnt;
	static int[] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N];
		nQueen(0);
		System.out.println(totalCnt);
	}

	private static void nQueen(int cnt) {
		// 모든 퀸이 놓인 경우
		if (cnt == N) {
			totalCnt++;
			return;
		}
		for (int i = 0; i < N; i++) {
			map[cnt] = i;
			if (check(cnt)) {
				nQueen(cnt + 1);
			}
		}
	}

	// 퀸을 놓을 수 있는지 확인
	private static boolean check(int cnt) {
		for (int i = 0; i < cnt; i++) {
			// map[i]==map[cnt]: 같은 행에 놓인 경우
			// map[i]-map[cnt]==1 : 대각선에 놓인 경우
			if (map[i] == map[cnt]) {
				return false;
			} else if (Math.abs(map[i] - map[cnt]) == Math.abs(i - cnt)) {
				return false;
			}
		}
		return true;
	}
}
