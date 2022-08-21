package com.day0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1722_PermutationOrder {

	static int N, K, totalCnt;
	static int[] input;
	static int[] numbers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 순열의 크기 입력 받기
		numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = i + 1;
		}
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int type = Integer.parseInt(st.nextToken());
		if (type == 1) { // 1이 들어오면 K를 입력받고 K번째순열을 출력한다. -> 순열을 만들면서 count를 ++한다. count==K일때 순열을 출력한다.
			K = Integer.parseInt(st.nextToken());
			totalCnt = 1;
			while (totalCnt != K) {
				np();
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				sb.append(numbers[i] + " ");
			}
			System.out.println(sb);
		} else {// 2가 들어오면 순열을 입력받고 몇번째 순열인지 출력한다. -> input으로 들어온 숫자를 거꾸로 오름차순 순열로 만들어 cnt를 출력
			// 순열 입력 받기
			input = new int[N];
			totalCnt = 1;
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			boolean find = true;
			while (find) {
				find = pp();
			}
			System.out.println(totalCnt);
		}

	}

	private static boolean pp() { // input으로 들어온 숫자를 거꾸로 오름차순 순열로 만들어 cnt를 출력
		// 꼭대기 i찾기
		int i = N - 1;
		while (i > 0 && input[i] >= input[i - 1]) {
			i--;
		}
		if (i == 0) {
			return false;
		}
		// i-1과 바꿀 j찾기
		int j = N - 1;
		while (input[i - 1] <= input[j]) {
			j--;
		}
		// i-1과 j자리 바꾸기
		swap(input, i - 1, j);
		// i~N-1까지 자리 바꾸기
		int k = N - 1;
		while (i < k) {
			swap(input, i++, k--);
		}
		totalCnt++;
		return true;
	}

	private static boolean np() {
		// 꼭대기 i찾기
		int i = N - 1;
		while (i > 0 && numbers[i - 1] >= numbers[i]) {
			i--;
		}
		if (i == 0) {
			return false;
		}
		// i-1과 바꿀 j찾기
		int j = N - 1;
		while (numbers[i - 1] >= numbers[j]) {
			j--;
		}
		// i-1과 j자리 바꾸기
		swap(numbers, i - 1, j);
		// i~N-1까지 자리 바꾸기
		int k = N - 1;
		while (i < k) {
			swap(numbers, i++, k--);
		}
		totalCnt++;
		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
