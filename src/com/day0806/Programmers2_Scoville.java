package com.day0806;

import java.util.PriorityQueue;

public class Programmers2_Scoville {

	static int solution(int[] scoville, int K) {
		int answer = 0;
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < scoville.length; i++) {
			queue.add(scoville[i]);
		}
		int tmp1, tmp2, min;
		while (!queue.isEmpty() && queue.peek() < K) {
			tmp1 = queue.poll();
			if (queue.isEmpty()) {
				answer = -1;
				break;
			}
			tmp2 = queue.poll();
			min = tmp1 + tmp2 * 2;
			queue.add(min);
			answer++;
		}
		return answer;
	}

	public static void main(String[] args) {
		int[] scoville = { 1, 2, 3, 9, 10, 12 };
		int k = 7;
		System.out.println(solution(scoville, k));
	}
}
