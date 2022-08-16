package com.day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon14889_StartLink {
	static int N, R, Ans;
	static int[][] map;
	static int[] team1, team2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 배열 초기화
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		R = N / 2;
		team1 = new int[R];
		team2 = new int[R];
		Ans = Integer.MAX_VALUE;
		comb(0, 0);
		System.out.println(Ans);
	}

	// 두 팀을 뽑는 조합 함수
	private static void comb(int cnt, int start) {
		if (cnt == R) {
			for (int i = start; i < N; i++) {
				team2[i - cnt] = i;
			}
			Ans = Ans > calc() ? calc() : Ans;
			return;
		}
		for (int i = start; i < N; i++) {
			if (i - cnt >= R)
				break;
			team1[cnt] = i;
			comb(cnt + 1, i + 1);
			team2[i - cnt] = i;
		}
	}

	// 두 팀의 능력치 차이 최솟값 계산
	private static int calc() {
		int num1 = 0;
		int num2 = 0;
		for (int i = 0; i < R; i++) {
			for (int j = i + 1; j < R; j++) {
				num1 += map[team1[i]][team1[j]] + map[team1[j]][team1[i]];
				num2 += map[team2[i]][team2[j]] + map[team2[j]][team2[i]];
			}
		}
		return Math.abs(num1 - num2);
	}
}
