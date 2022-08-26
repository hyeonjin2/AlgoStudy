package com.day0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_No1_FishingSpot {

	static int N;
	static int[] spots, persons, selected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			spots = new int[3];
			persons = new int[3];
			selected = new int[3];
			for (int i = 0; i < 3; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				spots[i] = Integer.parseInt(st.nextToken());
				persons[i] = Integer.parseInt(st.nextToken());
			}
			// 낚시터 순서 뽑으러 가기
			perm(0, 0);
		}
	}

	private static void perm(int cnt, int flag) {
		if (cnt == 3) {
			calc();
			System.out.println(Arrays.toString(selected));
			return;
		}
		for (int i = 0; i < 3; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			selected[cnt] = i;
			perm(cnt + 1, flag | 1 << i);
		}
	}

	private static void calc() {
		// TODO Auto-generated method stub
		
	}
}
