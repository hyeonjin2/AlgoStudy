package com.day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시험 감독
public class BaekJoon13458 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] persons = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			persons[i] = Long.parseLong(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		long pri = Long.parseLong(st.nextToken());
		long sub = Long.parseLong(st.nextToken());
		long sum = 0;
		for (int i = 0; i < N; i++) {
			if (persons[i] <= pri) {
				sum++;
				continue;
			} else {
				persons[i] -= pri;
				sum++;
				if (persons[i] % sub > 0)
					sum++;
				sum += persons[i] / sub;
			}
		}
		System.out.println(sum);
	}
}
