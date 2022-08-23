package com.day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon1026_Treasure {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Integer[] arr1 = new Integer[N];
		Integer[] arr2 = new Integer[N];
		for (int i = 0; i < 2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (i == 0) {
					arr1[j] = Integer.parseInt(st.nextToken());
					continue;
				}
				arr2[j] = Integer.parseInt(st.nextToken());
			}
		}
		Arrays.sort(arr1);
		Arrays.sort(arr2, Collections.reverseOrder());
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += arr1[i] * arr2[i];
		}
		System.out.println(sum);
	}
}
