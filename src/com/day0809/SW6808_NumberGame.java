package com.day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW6808_NumberGame {
	static int[] a;
	static int[] b;
	static int[] cards = new int[9];
	static boolean isSelected[];
	static int sum_a;
	static int sum_b;
	static int count_win;
	static int count_lose;

	static void game(int cnt) {
		// 9라운드가 끝났을 때
		if (cnt == 9) {
			sum_a = 0;
			sum_b = 0;
			for (int i = 0; i < 9; i++) {
				if (cards[i] > a[i]) {
					sum_b += cards[i] + a[i];
				} else {
					sum_a += cards[i] + a[i];
				}
			}
			if (sum_a > sum_b) {
				count_win++;
			} else {
				count_lose++;
			}
			return;
		}
		// 규영이 카드와 인영이 카드 뽑기
		for (int i = 0; i < 9; i++) {
			if (isSelected[b[i]]) {
				continue;
			}
			cards[cnt] = b[i];
			isSelected[b[i]] = true;
			game(cnt + 1);
			
			isSelected[b[i]] = false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		a = new int[9];
		b = new int[9];
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			boolean[] card = new boolean[19];
			// 규영이와 인영이 숫자 카드 배열 초기화
			int idx = 0;
			for (int i = 0; i < 9; i++) {
				int num = Integer.parseInt(st.nextToken());
				a[i] = num;
				card[num] = true;
			}
			for (int i = 1; i <= 18; i++) {
				if (!card[i]) {
					b[idx++] = i;
				}
			}
			count_win = 0;
			count_lose = 0;
			isSelected = new boolean[19];
			game(0);
			System.out.println("#" + tc + " " + count_win + " " + count_lose);
		}
	}
}
