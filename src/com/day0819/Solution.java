package com.day0819;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, totalCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();

		for (int t = 1; t <= tc; t++) {
			totalCnt = 0;
			N = Integer.parseInt(br.readLine());
			int[] weights = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				weights[i] = Integer.parseInt(st.nextToken());

			perm(0, 0, 0, 0, weights);

			answer.append("#" + t + " " + totalCnt + "\n");
		}

		System.out.println(answer);
	}

	private static void perm(int cnt, int left, int right, int selected, int[] weights) {
		if (cnt == N) {
			totalCnt += 1;
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((selected & 1 << i) != 0)
				continue;
			int tmp = weights[i];
			if (left >= right + tmp) {
				perm(cnt + 1, left, right + tmp, selected | 1 << i, weights);
			}
			perm(cnt + 1, left + tmp, right, selected | 1 << i, weights);
		}
	}

}
