package com.day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1149_RGB {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] arr = new int[N][3];
		int[] sum = new int[N];// 최솟값들의 합의 배열
		int[] tmp = new int[N]; // i번째 배열은 두번째 값을 더한 배열 -> 다음 배열과 비교하기 위한 임시 배열
		int pre_idx = -1;
		int max_idx = -1;
		int min_idx = -1;
		// 배열 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (min > arr[i][j]) { // 한 줄의 집들 중 가장 작은 값 -> min
					min = arr[i][j];
					min_idx = j;
				}
				if (max < arr[i][j]) {
					max = arr[i][j];
					max_idx = j;
				}
			} // 비교
			int tmp_idx = 3 - min_idx - max_idx; // 두번째로 작은 값의 인덱스
			if (pre_idx == min_idx) {
				min = Math.min(tmp[i - 1] + arr[i][min_idx], sum[i - 1] + arr[i][tmp_idx]);
				if (min == tmp[i - 1] + arr[i][min_idx]) {
					tmp[i] = sum[i - 1] + arr[i][tmp_idx];
				} else {
					tmp[i] = tmp[i - 1] + arr[i][min_idx];
				}
				sum[i] = min;
			} else {
				if (i == 0) {
					sum[i] = arr[i][min_idx];
					tmp[i] = arr[i][tmp_idx];
				} else {
					sum[i] = sum[i - 1] + arr[i][min_idx];
					tmp[i] = sum[i - 1] + arr[i][tmp_idx];
				}
				pre_idx = min_idx;
			}
		}
		System.out.println(sum[N - 1]);
	}
}
