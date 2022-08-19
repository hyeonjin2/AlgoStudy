package com.day0819;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BaekJoon1697_HideAndSeek {

	static class Move {
		int p;
		int t;

		public Move(int p, int t) {
			this.p = p;
			this.t = t;
		}
	}

	static int n, k, Ans;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		visited = new boolean[100_001];
		bfs(n);
		System.out.println(Ans);
	}

	private static void bfs(int start) {
		Queue<Move> queue = new ArrayDeque<>();
		queue.offer(new Move(start, 0));
		visited[start] = true;
		while (!queue.isEmpty()) {
			Move m = queue.poll();
			if (m.p == k) {
				Ans = m.t;
				return;
			}
			int[] next = { m.p - 1, m.p + 1, m.p * 2 };
			for (int i = 0; i < 3; i++) {
				if (next[i] >= 0 && next[i] <= 100_000 && !visited[next[i]]) {
					visited[next[i]] = true;
					queue.offer(new Move(next[i], m.t + 1));
				}
			}
		}
	}
}
