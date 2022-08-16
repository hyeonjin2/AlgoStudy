package com.day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon11582_ChickenTopN {

	static int N, R;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		R = Integer.parseInt(br.readLine());
		sortArr(0, N - 1); // 사람 수
	}

	private static void sortArr(int start, int end) {// size: 검사해야하는 배열의 길이 ,cnt : 정렬을 진행하는 사람의 수
		if (start == end)
			return;
		int mid = (start + end) / 2;
		sortArr(start, mid);
		sortArr(mid + 1, end);
		merge(start, end);
	}

	private static void merge(int start, int end) {
		if ((end - start) > N / R) {// 배열의 크기가 출력하려는 조건(R명의 사람이 정렬할 수 있는 배열의 크기)보다 커지면 리턴
			return;
		}
		int mid = (start + end) / 2;
		int i = start;
		int j = mid + 1;
		int k = 0;
	}
}
