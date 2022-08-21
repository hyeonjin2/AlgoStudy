package com.day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BeakJoon2167_MatrixSum {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] sum = new int[N + 1][M + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				sum[0][j] = 0;
				sum[i][0] = 0;
			}
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1]
						+Integer.parseInt(st.nextToken());
			}
		}
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int startR = Integer.parseInt(st.nextToken());
			int startC = Integer.parseInt(st.nextToken());
			int endR = Integer.parseInt(st.nextToken());
			int endC = Integer.parseInt(st.nextToken());
			int result = sum[endR][endC] - sum[startR - 1][endC] 
					- sum[endR][startC - 1] + sum[startR - 1][startC - 1];
			System.out.println(result);
		}
	}
}
