package com.day0830;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

// 숨바꼭질2
public class BaekJoon12851 {

	static int start, end, Ans, Cnt;
	static int[] time;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		start = sc.nextInt();
		end = sc.nextInt();
		Ans = Integer.MAX_VALUE;
		time = new int[100001];
		bfs();
		System.out.println(Ans);
		System.out.println(Cnt);
	}

	private static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (time[cur] > Ans)
				return;
			if (cur == end) {
				Ans = Math.min(Ans, time[cur]);
				if (Ans == time[cur])
					Cnt++;
			}
			int next;
			int[] d = { -1, 1, cur };
			for (int i = 0; i < 3; i++) {
				next = cur + d[i];
				if (next < 0 || next > 100000)
					continue;
				if (time[next] == 0 || time[next] == time[cur] + 1) {
					queue.offer(next);
					time[next] = time[cur] + 1;
				}
			}
		}
	}
}
