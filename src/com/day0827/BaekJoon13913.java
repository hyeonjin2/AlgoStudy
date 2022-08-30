package com.day0827;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BaekJoon13913 {

	static class Point {
		int x, d;

		public Point(int x, int d) {
			this.x = x;
			this.d = d;
		}
	}

	static int s, e, Ans;
	static boolean[] visited;
	static int[] list;
	static Stack<Integer> stack;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		s = sc.nextInt();
		e = sc.nextInt();
		list = new int[100001];
		stack = new Stack<>();
		Arrays.fill(list, -1);
		bfs();
		StringBuilder sb = new StringBuilder();
		sb.append(Ans).append("\n");
		sb.append(s).append(" ");
		while (!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb);
	}

	private static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		Point start = new Point(s, 0);
		queue.offer(start);
		visited = new boolean[100001];
		visited[s] = true;
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int x = cur.x;
			int d = cur.d;
			if (x == e) {
				Ans = cur.d;
				while (x != s) {
					stack.push(x);
					x = list[x];
				}
				return;
			}
			int[] dx = { -1, 1, x };
			for (int i = 0; i < 3; i++) {
				int nx = x + dx[i];
				if (nx < 0 || nx > 100000 || visited[nx])
					continue;
				visited[nx] = true;
				list[nx] = x;
				queue.offer(new Point(nx, d + 1));
			}
		}
	}
}
