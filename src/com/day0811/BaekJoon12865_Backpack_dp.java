package com.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon12865_Backpack_dp {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 담을 수 있는 물건의 수
		int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
		int[][] objects = new int[N + 1][2 + 1];
		int[][] dp = new int[N + 1][K + 1];
		// 물건 배열 초기화
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < 3; j++) {
				objects[i][0] = 0;
				objects[0][j] = 0;
			}
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			objects[i][0] = Integer.parseInt(st.nextToken()); // 물건의 무게
			objects[i][1] = Integer.parseInt(st.nextToken()); // 물건의 가치
		}
		for (int i = 1; i <= N; i++) {
			for (int k = 1; k <= K; k++) {
				dp[i][k] = dp[i - 1][k];
				int w = objects[i][0];
				if (k - w >= 0) {
					int v = objects[i][1];
					dp[i][k] = Math.max(dp[i - 1][k - w] + v, dp[i - 1][k]);
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}
