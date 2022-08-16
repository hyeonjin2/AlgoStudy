package com.day0816;

import java.util.Scanner;

public class BaekJoon14916_Change {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int money = sc.nextInt();
		int Ans = 0;
		while (money % 5 != 0 && money >= 0) {
			money -= 2;
			Ans++;
		}
		Ans = Ans > 0 ? Ans + money / 5 : -1;
//		while (money > 0) {
//			if (money % 5 != 0) {
//				if (money >= 2) {
//					money -= 2;
//					Ans++;
//				} else {
//					Ans = -1;
//					break;
//				}
//			} else {
//				money -= 5;
//				Ans++;
//			}
//		}
		System.out.println(Ans);
	}
}
