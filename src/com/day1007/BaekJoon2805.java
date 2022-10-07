package com.day1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 나무 자르기
public class BaekJoon2805 {

	static int N, M, temp, Ans;
	static int[] trees, copy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		int max = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			if (trees[i] > max)
				max = trees[i];
			if (trees[i] < min)
				min = trees[i];
		}
		Arrays.sort(trees);
		find(min, max, false);
		System.out.println(Ans);
	}

	private static void find(int start, int end, boolean flag) {
		int sum = 0;
		int mid = (start + end) / 2;
		if (start >= end) {
			Ans = temp;
			return;
		}
		for (int i = 0; i < N; i++) {
			if (trees[i] >= mid) {
				sum += (trees[i] - mid);
				copy[i] = mid;
			}
		}
		System.out.println("start:" + start + " mid:" + mid + " sum:" + sum);
		if (sum == M) {
			Ans = mid;
			return;
		} else if (sum > M) {
			temp = mid;
			find(mid, end, true);
		}
	}
}
