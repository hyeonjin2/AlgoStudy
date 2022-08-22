package com.day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1759_MakePassword {

	static int L, C;
	static char[] input;
	static char[] password;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		input = new char[C]; // 입력받은 문자들을 담을 배열
		// 배열 입력 받기
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		// 사전 순으로 정렬하기
		Arrays.sort(input);
		password = new char[L];
		// 4개 조합으로 뽑기
		comb(0, 0);
	}

	private static void comb(int cnt, int start) {
		if (cnt == L) {
			// 조건에 맞는 비밀번호인지 체크
			check();
			return;
		}
		for (int i = start; i < C; i++) {
			password[cnt] = input[i];
			comb(cnt + 1, i + 1);
		}
	}

	// 조건에 맞는 비밀번호인지 체크 후 출력
	private static void check() {
		String aeiou = "aeiou";
		StringBuilder sb = new StringBuilder();
		int count1 = 0; // 모음의 수를 셀 변수
		int count2 = 0; // 자음의 수를 셀 변후
		for (int i = 0; i < L; i++) {
			// 모음이면 count1++
			if (aeiou.contains(password[i] + "")) {
				count1++;
				// 모음이 아니면 -> 자음이므로 count2++
			} else {
				count2++;
			}
			sb.append(password[i]);
		}
		// 비밀번호의 조건 : 최소 한개의 모음 && 최소 2개의 자음
		if (count1 >= 1 && count2 >= 2) {
			// 조건에 맞으면 출력
			System.out.println(sb);
		} else {
			return;
		}
	}
}
