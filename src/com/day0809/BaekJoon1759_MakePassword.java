package com.day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1759_MakePassword {
	static StringBuilder sb;
	static char[] input;
	static char[] selected;
	static int N;
	static int C;
	static boolean valid;
	static int cntV;
	static int cntC;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		input = new char[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		selected = new char[C];
		Arrays.sort(input);
		comb(0, 0);
		System.out.println(sb.toString());
	}

	private static void comb(int cnt, int start) {
		cntV = 0;
		cntC = 0;
		// 종료조건 C개를 다 뽑았을 때 모음은 꼭 가지고 있어야 함
		if (cnt == C) {
			for (int i = 0; i < C; i++) {
				if (selected[i] == 'a' || selected[i] == 'e' || selected[i] == 'i' || selected[i] == 'o'
						|| selected[i] == 'u') {
					cntV++;
				} else {
					cntC++;
				}
			}
			if (cntV >= 1 && cntC >= 2) {
				for (int i = 0; i < C; i++) {
					sb.append(selected[i]);
				}
				sb.append("\n");
			}
			return;
		}
		// 글자 뽑기
		for (int i = start; i < N; i++) {
			selected[cnt] = input[i];
			comb(cnt + 1, i + 1);
		}
	}
}
