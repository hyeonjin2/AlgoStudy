package com.day0808;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BeakJoon1158 {

	public static void main(String[] args) {
		Queue<Integer> table = new ArrayDeque<>();
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		int K = sc.nextInt();
		// table 배열 초기화
		for (int i = 1; i <= N; i++) {
			table.offer(i);
		}
		sb.append("<");
		// K-1번째 수까지 큐의 뒤로 이동, K번째 수 poll
		int cnt = 1;
		while (table.size() > 1) {
			if (cnt % K == 0) {
				sb.append(table.peek() + ", ");
				table.poll();
				cnt++;
				continue;
			}
			int tmp = table.poll();
			table.offer(tmp);
			cnt++;
		}
		sb.append(table.peek() + ">");
		System.out.println(sb);
	}
}
