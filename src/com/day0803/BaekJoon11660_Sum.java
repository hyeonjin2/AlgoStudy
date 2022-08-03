package com.day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11660_Sum {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 배열 초기화
		int[][] arr = new int[N][N];
		int[][] sum = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (i == 0 && j == 0) {
					sum[i][j] = arr[i][j];
					continue;
				} else if (i == 0) {
					sum[i][j] = sum[0][j - 1] + arr[i][j];
					continue;
				} else if (j == 0) {
					sum[i][j] = sum[i - 1][0] + arr[i][j];
					continue;
				}
				sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + arr[i][j];
			}
		}
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		int diffX;
		// 계산하기
		for (int i = 0; i < M; i++) {
			int result = 0;
			// 인덱스 값 받기
			st = new StringTokenizer(br.readLine(), " ");
			x1 = Integer.parseInt(st.nextToken()) - 1;
			y1 = Integer.parseInt(st.nextToken()) - 1;
			x2 = Integer.parseInt(st.nextToken()) - 1;
			y2 = Integer.parseInt(st.nextToken()) - 1;
			if (x1 - 1 < 0 && y1 - 1 < 0) {
				result = sum[0][0];
			} else if (x1 - 1 < 0) {
				result = sum[x2][y2] - sum[0][y2] - sum[x2][y1 - 1] + sum[0][y1 - 1];
			} else if (y1 < 0) {
				result = sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][0] + sum[x1 - 1][0];
			} else {
				result = sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
			}
			System.out.println(result);
		}
	}
}
