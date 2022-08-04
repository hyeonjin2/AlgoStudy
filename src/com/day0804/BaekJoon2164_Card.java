package com.day0804;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BaekJoon2164_Card {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		int num = 0;
		while (!queue.isEmpty()) {
			// 1. 제일 위에 있는 카드를 버린다.
			num = queue.poll();
			// 2. 그다음 위에 있는 카드를 맨 밑으로 넣는다.
			if (!queue.isEmpty()) {
				num = queue.poll();
				queue.offer(num);
			}
		}
		System.out.println(num);
	}
}
