package com.day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2559_Sum {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] sum = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (i == 0) {
				sum[i] = num;
				continue;
			}
			sum[i] = sum[i - 1] + num;
		}
		int max = Integer.MIN_VALUE;
		int tmp = 0;
		for (int i = K - 1; i < N; i++) {
			if (i == K - 1) {
				tmp = sum[i];
			} else {
				tmp = sum[i] - sum[i - K];
			}
			if (max < tmp) {
				max = tmp;
			}
		}
		System.out.println(max);
	}
}
