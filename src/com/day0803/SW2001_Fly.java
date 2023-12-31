package com.day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW2001_Fly {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			// 배열 초기화
			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int max = Integer.MIN_VALUE;
			int sum = 0;
			// 배열 검색
			for (int i = 0; i <= N - M; i++) {
				for (int j = 0; j <= N - M; j++) {
					sum = 0;
					// 파리채의 크기만큼 검색 후 max 찾기
					for (int k = i; k < i + M; k++) {
						for (int l = j; l < j + M; l++) {
							sum += arr[k][l];
						}
					}
					max = Math.max(max, sum);
				}
			}
			System.out.println("#" + tc + " " + max);
		}

	}

}
