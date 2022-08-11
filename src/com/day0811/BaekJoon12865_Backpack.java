package com.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon12865_Backpack {
	static int N, K;
	static int[][] objects; // 물건들의 가치, 무게를 담는 배열
	static boolean[] isSelected;
	static int weight, value;
	static int totalVal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 담을 수 있는 물건의 수
		K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
		objects = new int[N][2];
		isSelected = new boolean[N];
		// 물건 배열 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			objects[i][0] = Integer.parseInt(st.nextToken()); // 물건의 무게
			objects[i][1] = Integer.parseInt(st.nextToken()); // 물건의 가치
		}
		subSet(0);
		System.out.println(totalVal);
	}

	private static void subSet(int index) {
		if (index == N) {
			calc();
			return;
		}
		isSelected[index] = true;
		subSet(index + 1);
		isSelected[index] = false;
		subSet(index + 1);
	}

	private static void calc() {
		weight = 0;
		value = 0;
		for (int i = 0; i < N; i++) {
			if (isSelected[i]) {
				weight += objects[i][0];
				value += objects[i][1];
			}
		}
		// 무거우면 return
		if (weight > K) {
			return;
		}
		totalVal = Math.max(totalVal, value);
	}
}
