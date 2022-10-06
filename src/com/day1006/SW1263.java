package com.day1006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사람 네트워크
public class SW1263 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int Ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			final int INF = 10_000_000;
			int[][] D = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) {
						D[i][j] = 0;
						continue;
					}
					D[i][j] = INF;
				}
			}

			int[][] adjList = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					adjList[i][j] = Integer.parseInt(st.nextToken());
					if (adjList[i][j] != 0) {
						D[i][j] = 1;
					}
				}
			}
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
					}
				}
			}
			int[] sumD = new int[N];
			Ans = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sumD[i] += D[i][j];
				}
				Ans = Math.min(Ans, sumD[i]);
			}
			sb.append("#").append(tc).append(" ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}
}
