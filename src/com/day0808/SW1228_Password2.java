package com.day0808;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class SW1228_Password2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			int N = sc.nextInt();
			LinkedList<Integer> list = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				list.add(sc.nextInt());
			}

			int num = sc.nextInt(); // 명령어 개수.
			for (int i = 0; i < num; i++) {
				sc.next(); // 'I'
				int position = sc.nextInt(); // 원래 암호문의 position 위치 다음에 삽입해야 함.
				int count = sc.nextInt(); // 삽입할 숫자 갯수

				ArrayList<Integer> temp = new ArrayList<>(count); // count만큼 사이즈 지정
				for (int j = 0; j < count; j++) {
					temp.add(sc.nextInt());
				}
				list.addAll(position, temp); // list의 position위치에 temp를 추가함.
			}
			System.out.print("#" + tc + " ");
			for (int i = 0; i < 10; i++) {
				System.out.print(list.get(i) + " ");
			}
			System.out.println();
		}
	}
}
