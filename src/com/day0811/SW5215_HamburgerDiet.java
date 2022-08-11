package com.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW5215_HamburgerDiet {

	static int N, L;
	static int[][] ingredient; // 재료의 선호도와 칼로리를 담는 배열
	static int max, score, calorie;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 재료의 개수
			L = Integer.parseInt(st.nextToken()); // 칼로리 제한
			// 재료 배열 초기화
			ingredient = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				ingredient[i][0] = Integer.parseInt(st.nextToken());
				ingredient[i][1] = Integer.parseInt(st.nextToken());

			}
			// 변수 초기화
			max = 0;
			score = 0;
			calorie = 0;
			// 조합 함수 호출
			comb(0, 0);
			System.out.println("#" + tc + " " + max);
		}
	}
	// 재료를 조합하여 고르는 경우를 정하는 함수
	private static void comb(int cnt, int start) {
		// 종료 조건 : 누적 칼로리가 최대 칼로리를 넘는 경우
		if (calorie > L) {
			return;
		}
		// 종료 조건 : N를 모두 고른 경우
		if (cnt == N) {
			max = Math.max(max, score);
			return;
		}
		for (int i = start; i < N; i++) {
			// 선호도 점수, 칼로리 더하기
			score += ingredient[i][0];
			calorie += ingredient[i][1];
			// 재귀 호출
			comb(cnt + 1, i + 1);
			// 선호도 점수, 칼로리 다시 빼기 -> 이전 값으로 돌리기
			score -= ingredient[i][0];
			calorie -= ingredient[i][1];
		}
		// 중간 중간에 최대 점수 값 정해주기
		max = Math.max(max, score);
	}
}
