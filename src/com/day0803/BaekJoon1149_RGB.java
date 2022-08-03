package com.day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1149_RGB {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] arr = new int[N][3];
		int[] sum = new int[N];
		int min;
		int pre_idx = -1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			min = Integer.MAX_VALUE;
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (i == 0) {
					
				}

//				if (min > arr[i][j]) {
//					if (pre_idx == j && i != 0) { // 위의 집과 같은 색 일 때
//						min = Math.min(sum[i - 2] + arr[i][j], sum[i - 1]);
//						if (min == sum[i - 2] + arr[i][j]) {
//							sum[i] = min;
//						}
//					} else { // 위의 집과 다른 색일 때
//						if (i != 0) {
//							min = sum[i - 1] + arr[i][j];
//							pre_idx = j;
//							sum[i] = min;
//						}
//					}
//					if (i != 0)
//						sum[i] = sum[i - 1] + min;
//				}
			}
		}
		System.out.println(sum[N - 1]);
	}
}
