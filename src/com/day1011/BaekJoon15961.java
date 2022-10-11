package com.day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 회전 초밥
public class BaekJoon15961 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[] dishes = new int[N];
		int[] selected = new int[D + 1]; // 접시들의 선택여부를 저장할 배열
		selected[C] = 1;
		for (int i = 0; i < N; i++) {
			dishes[i] = Integer.parseInt(br.readLine());
		}
		int left = 0;
		int right = K;
		int max = 0; // 선택된 접시의 종류
		int count = 1; // 윈도우마다 접시의 종류->쿠폰으로 이미 한접시가 선택된 상태
		for (int i = 0; i < right; i++) {
			selected[dishes[i]]++;
			if (selected[dishes[i]] == 1)
				count++;
		}
		int rotate = 0;
		max = count;
		while (rotate < N) {
			if (--selected[dishes[left]] == 0)
				count--;
			if (++selected[dishes[right]] == 1) {
				count++;
			}
			if (count > max) {
				max = count;
			}
			left = (left + 1) % N;
			right = (right + 1) % N;
			rotate++;
		}

		System.out.println(max);
	}
}
