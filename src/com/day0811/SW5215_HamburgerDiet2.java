package com.day0811;

import java.util.Arrays;
import java.util.Scanner;

public class SW5215_HamburgerDiet2 {
	static int[][] material; // 재료들의 맛점수, 열량 저장하는 배열
	static int N, totalScore, maxCal;
	static boolean[] isSelected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // testcase 수

		for (int tc = 1; tc <= T; tc++) {
			totalScore = 0;
			N = sc.nextInt(); // 재료 개수
			maxCal = sc.nextInt(); // 제한 칼로리

			material = new int[N][2]; // N개의 재료에 대해 맛점수[0], 열량[1]
			isSelected = new boolean[N]; // 부분집합 만들 때 사용

			for (int i = 0; i < N; i++) {
				material[i][0] = sc.nextInt();
				material[i][1] = sc.nextInt();
			}
			subSet(0); // 각 재료를 가지고 부분 집합을 만들어 체크해 봐야 한다.

			System.out.println("#" + tc + " " + totalScore);
		}
	}

	private static void subSet(int index) {// index:부분집합에 고려할 대상 원소의 인덱스
		if (index == N) { // 더이상 고려할 원소가 없다면 부분집합의 구성이 완성
			check(); // 부분집합이 만들어지면 조건에 맞는지 확인
			System.out.println(Arrays.toString(isSelected));
			return;
		}
		// 원소 선택
		isSelected[index] = true;
		subSet(index + 1);
		// 원소 미선택
		isSelected[index] = false;
		subSet(index + 1);
	}

	// 하나의 부분집합이 완성됐을 때 그 재료들을 가지고
	private static void check() {
		int score = 0;
		int cal = 0;

		for (int i = 0; i < isSelected.length; i++) {
			if (isSelected[i]) { // i번째 재료 사용함
				score += material[i][0];
				cal += material[i][1];
			}
		}
		if (cal <= maxCal) { // 칼로리 제한에 걸리지 않으면
			totalScore = Math.max(totalScore, score);
		}
	}
}
