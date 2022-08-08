package com.day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW9229_SpotMart {
	static int[] snacks; // 과자의 무게를 담을 배열
	static int[] selected; // 선택한 과자의 무게
	static int M; // 선택할 수 있는 과자의 최대 개수
	static int weight; // 과자의 최대 무게

	static void comb(int cnt, int start) {
		// 종료 조건 2개를 뽑았을 때, 두 과자의 무게를 합했을 때 M보다 크면 안됨
		if (cnt == 2) {
			int sum = selected[0] + selected[1];
			if (sum > M) // 두 과자의 무게를 합친 것이 M을 넘으면 안됨
				return;
			if (weight < sum) {
				weight = sum;
			}
			return;
		}
		for (int i = start; i < snacks.length; i++) {
			selected[cnt] = snacks[i];
			comb(cnt + 1, i + 1);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			snacks = new int[N];
			selected = new int[2]; // 과자 2개 선택
			weight = -1;
			st = new StringTokenizer(br.readLine());
			// 과자 무게 배열 초기화
			for (int i = 0; i < N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}
			comb(0, 0);
			System.out.println("#" + tc + " " + weight);
		}
	}
}
