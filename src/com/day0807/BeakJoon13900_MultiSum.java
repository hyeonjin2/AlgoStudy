package com.day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BeakJoon13900_MultiSum {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] sum = new long[N];
		int[] numbers = new int[N];
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (i == 0) {
				sum[i] = num;
				numbers[i] = num;
				continue;
			}
			numbers[i] = num;
			sum[i] = sum[i - 1] + num;
		}
		long result = 0;
		for (int i = 0; i < N - 1; i++) {
			result += numbers[i] * (sum[N - 1] - sum[i]);
		}
		System.out.println(result);
	}
}
