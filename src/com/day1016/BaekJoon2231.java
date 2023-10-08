package com.day1016;

import java.util.Arrays;
import java.util.Scanner;

// 분해합
public class BaekJoon2231 {
	static int num, size, ans;
	static int[] number;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String N = sc.next();
		size = N.length();
		number = new int[size];
		num = 0;
		for (int i = 0; i < size; i++) {
			number[i] = N.charAt(i) - '0';
			num += number[i] * Math.pow(10, size - 1 - i);
		}
		for (int i = 1; i < num; i++) {
			String temp = Integer.toString(i);
			int sum = 0;
			for (int j = 0; j < temp.length(); j++) {
				sum += temp.charAt(j) - '0';
			}
			sum += i;
			if (sum == num) {
				ans = i;
				break;
			} else if (i == num) {
				ans = 0;
				break;
			}
		}
		System.out.println(ans);
	}
}
