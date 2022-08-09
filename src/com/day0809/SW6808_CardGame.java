package com.day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW6808_CardGame {
	static boolean[] cards; // 전체 18장의 선택 정보 저장. 참이면 규영이 카드. 거짓이면 인영이 카드.
	static int[] numbers1; // 규영이 카드 번호. 9장 들어감
	static int[] numbers2; // 인영이 카드 번호. 9장 들어감
	static int win, lose;
	static int N = 9;

	// 순열관련 변수
	static boolean[] flag;
	static int[] selected; // 순열로 정해진 값들이들어감.

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			win = lose = 0;
			selected = new int[N];
			flag = new boolean[N];

			numbers1 = new int[N];
			numbers2 = new int[N];

			cards = new boolean[19]; // 카드 번호가 1번부터 들어옴
			StringTokenizer st = new StringTokenizer(br.readLine().trim()); // 규영이 카드 번호
			// 1. 규영이 카드 받기
			int cno = 0;
			for (int i = 0; i < N; i++) {
				cno = Integer.parseInt(st.nextToken());
				numbers1[i] = cno;
				cards[cno] = true; // 규영이 카드 -> 참
			}

			// 2. 인영이 카드 받기
			cno = 0;
			for (int i = 0; i < cards.length; i++) {
				if (!cards[i]) {
					numbers2[cno++] = i;
				}
			}

			// 인영이가 가진 9장의 카드로 순열 생성.
			permu(0);
			sb.append("#").append(tc).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		System.out.println(sb.toString());
	}

	// 인영이의 9장의 카드를 가지고 9개를 선택해서 순서를 달리해 늘어놓는 순열을 만듦.
	private static void permu(int cnt) {
		if (cnt == N) { // 인영이가 낼 카드 순서 한가지가 완성
			checkResult(selected);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (flag[i]) {
				continue;
			}
			flag[i] = true;
			selected[cnt] = numbers2[i]; // 인영이의 것 중에서 뽑음
			permu(cnt + 1);
			flag[i] = false;
		}
	}

	// selected : 인영이가 낼 카드 순서가 들어 있음.
	private static void checkResult(int[] selected) {
		int ksum = 0;
		int isum = 0;
		for (int i = 0; i < N; i++) {
			if (numbers1[i] > selected[i]) { // 규영이 카드 번호 > 인영이 카드 번호
				ksum += numbers1[i] + selected[i];

			} else { // 규영이 카드 번호 < 인영이 카드 번호
				isum += numbers1[i] + selected[i];
			}
		}
		if (ksum > isum) {
			win++;
		} else if (ksum < isum) {
			lose++;
		}
	}
}
