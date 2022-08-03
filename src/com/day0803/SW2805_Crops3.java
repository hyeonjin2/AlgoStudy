package com.day0803;

import java.util.Scanner;

public class SW2805_Crops3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				char[] tmp = sc.next().toCharArray(); // char 배열로 저장
				for (int j = 0; j < N; j++) {
					map[i][j] = tmp[j] - '0'; // char 타입의 숫자 -> 정수형 타입으로 변환. '1'-'0'

				}
			} // 입력 완료
				// 영역 수확
			int center = N / 2;
			int havest = 0, start, end, gap = 0;

			for (int i = 0; i < N; i++) { // 모든 행에 대해 작업
				// 각 행별로 시작위치, 끝위치 결정(열 값)
				start = center - gap; // 수확을 할 시작 위치
				end = center + gap; // 수확을 할 마지막 위치

				for (int j = start; j <= end; j++) {
					havest += map[i][j]; // 수확
				}
				// gap 조정
				if (i < center) { // i가 행값인데 center보다 작으면 세로방향 기준으로 볼 때 위 쪽
					gap++;
				} else { // i가 center이거나 크거나
					gap--;
				}
			}
			System.out.println("#"+tc+" "+havest);
		}
	}
}
