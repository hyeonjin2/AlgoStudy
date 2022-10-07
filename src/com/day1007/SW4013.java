package com.day1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 특이한 자석
public class SW4013 {

	static int Ans;
	static int[][] magnetics;
	static boolean[] numbers;
	static int[] direction;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine());
			Ans = 0;
			// 자성 정보 입력 받기
			magnetics = new int[4][8];
			StringTokenizer st;
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnetics[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 자석 돌려보기
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());
				// 자석 돌리기
				direction = new int[4];
				numbers = new boolean[4];
				direction[num] = dir;
				numbers[num] = true;
				setMagnetic(num);
				moveMagnetic();
			}
			// 점수 계산하기
			int[] score = { 1, 2, 4, 8 };
			for (int i = 0; i < 4; i++) {
				if (magnetics[i][0] == 1)
					Ans += score[i];
			}
			sb.append("#").append(tc).append(" ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void moveMagnetic() {
		// 입력받은 자석 한칸 돌려보기
		for (int k = 0; k < 4; k++) {
			if (!numbers[k])
				continue;
			int dir = direction[k];
			// 시계방향으로 돌리는 경우
			if (dir == 1) {
				// 마지막 자석의 자성 정보 기억해두기
				int temp = magnetics[k][7];
				for (int i = 7; i > 0; i--) {
					magnetics[k][i] = magnetics[k][i - 1];
				}
				magnetics[k][0] = temp;
			}
			// 반시계 방향으로 돌리는 경우
			else {
				// 첫번째 자석의 자성 정보 기억해두기
				int temp = magnetics[k][0];
				for (int i = 0; i < 7; i++) {
					magnetics[k][i] = magnetics[k][i + 1];
				}
				magnetics[k][7] = temp;
			}
		}
	}

	// 자석의 번호와 방향을 입력받아 자석을 돌리는 메소드
	private static void setMagnetic(int num) {
		// 맞물리는 위치의 자성 확인하고 돌려야하는지 판단하기
		// 자석이 맞물리는 위치 2,6번 인덱스
		int n = num + 1;
		int b = num - 1;
		if (n < 4 && magnetics[num][2] != magnetics[n][6] && !numbers[n]) {
			direction[n] = direction[num] * (-1);
			numbers[n] = true;
			setMagnetic(n);
		}
		if (b >= 0 && magnetics[num][6] != magnetics[b][2] && !numbers[b]) {
			direction[b] = direction[num] * (-1);
			numbers[b] = true;
			setMagnetic(b);
		}
	}
}
