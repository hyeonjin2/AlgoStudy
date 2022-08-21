package com.day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BeakJoon11441_Sum {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] sum = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (i == 0) {
				sum[i] = num;
				continue;
			}
			sum[i] = sum[i - 1] + num;
		}
		int K = Integer.parseInt(br.readLine());
		int result = 0;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if (start - 1 == 0) {
				result = sum[end - 1];
			} else {
				result = sum[end - 1] - sum[start - 2];
			}
			System.out.println(result);
		}
	}
}
