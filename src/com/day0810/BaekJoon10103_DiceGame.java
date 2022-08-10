package com.day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon10103_DiceGame {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int round = Integer.parseInt(br.readLine());
		int sum1 = 100;
		int sum2 = 100;
		for (int i = 0; i < round; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			if (num1 > num2) {
				sum2 -= num1;
			} else if (num1 < num2) {
				sum1 -= num2;
			}
		}
		System.out.println(sum1);
		System.out.println(sum2);
	}
}
