package com.day0816;

import java.util.Scanner;

public class BaekJoon2839_Sugar {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int weight = sc.nextInt();
		int count = 0;
		while (weight > 0) {
			if (weight % 5 != 0) {
				if (weight >= 3) {
					weight -= 3;
					count++;
				} else {
					count = -1;
					break;
				}
			} else {
				if (weight >= 5) {
					weight -= 5;
					count++;
				} else {
					count = -1;
					break;
				}
			}
		}
		System.out.println(count);
	}
}
