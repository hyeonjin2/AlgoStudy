package com.day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BaekJoon11286_AbsoluteHeap {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> {
			int tmp1 = Math.abs(a);
			int tmp2 = Math.abs(b);

			if (tmp1 == tmp2) { // 절댓값이 같으면 작은 수가 먼저 온다.
				return a - b;
			} else {
				return tmp1 - tmp2; // 절댓값이 작은 수가 먼저 온다.
			}
		});
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num != 0) { // 0이 아닐 때는 queue에 값을 넣는다.
				queue.offer(num);
			} else { // 0이 입력되었을 때는 출력한다.
				if (!queue.isEmpty()) { // queue가 비어있지 않으면 poll
					sb.append(queue.poll()).append("\n");
				} else { // queue가 비어있으면 0출력
					sb.append(0).append("\n");
				}
			}
		}
		System.out.println(sb);
	}
}
