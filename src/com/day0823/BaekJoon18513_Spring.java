package com.day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon18513_Spring {

	static int N, M;
	static HashMap<Integer, Boolean> map;
	static Queue<Integer> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new HashMap<>();
		queue = new ArrayDeque<>();

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			queue.offer(num);
			map.put(num, true);
		}
		cnt = -1;
		bfs();
		System.out.println(sum);
	}

	static int cnt;
	static int totalCnt;
	static long sum;

	private static void bfs() {
		while (!queue.isEmpty()) {
			int size = queue.size();
			cnt++;
			for (int k = 0; k < size; k++) {
				int cur = queue.poll();
				totalCnt++;
				sum += cnt;
//				System.out.println(cur + " " + cnt);
				if (totalCnt == M + N) {
					return;
				}
				map.put(cur, true);
				int[] move = { -1, 1 };
				for (int i = 0; i < 2; i++) {
					int next = cur + move[i];
					if (map.get(next) == null || !map.get(next)) {
						map.put(next, true);
						queue.offer(next);
					}
				}
			}
		}
	}
}
