package com.day0827;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 헌터
public class SW_No2_Hunter {

	static int N, totalCnt, Ans;
	static int[] selected, input;
	static boolean[] killed;
	static Monster[] monster;
	static Client[] client;

	static class Monster implements Comparable<Monster> {
		int x, y;
		int d;
		int id;
		boolean killed;

		public Monster(int x, int y, int id, int d) {
			this.x = x;
			this.y = y;
			this.id = id;
			this.killed = false;
		}

		@Override
		public int compareTo(Monster o) {
			return this.d - o.d;
		}
	}

	static class Client {
		int x, y;
		int id;

		public Client(int x, int y, int id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			monster = new Monster[5];
			client = new Client[5];
			input = new int[8];
			totalCnt = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num > 0) {
						monster[num] = new Monster(i, j, num, i + j);
						input[totalCnt++] = num;
					} else if (num < 0) {
						input[totalCnt++] = num;
						num = Math.abs(num);
						client[num] = new Client(i, j, num);
					}
				}
			}
			selected = new int[totalCnt];
			Ans = Integer.MAX_VALUE;
			perm(0, 0);
			sb.append("#").append(tc).append(" ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void perm(int cnt, int flag) {
		if (cnt == totalCnt) {
			Ans = Math.min(Ans, calc());
			return;
		}
		for (int i = 0; i < totalCnt; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			selected[cnt] = input[i];
			perm(cnt + 1, flag | 1 << i);
		}
	}

	private static int calc() {
		int sum = 0;
		killed = new boolean[5];
		Point hunter = new Point(0, 0);
		int d = 0;
		for (int i = 0; i < totalCnt; i++) {
			int num = selected[i];
			if (num > 0) {
				killed[num] = true;
				Monster m = monster[num];
				d = Math.abs(hunter.x - m.x) + Math.abs(hunter.y - m.y);
				sum += d;
				hunter = new Point(m.x, m.y);
			} else {
				// 해당 고객집에 왔는데 몬스터가 살아있으면 안됨.
				num = Math.abs(num);
				try {
					if (!killed[num]) {
						return Integer.MAX_VALUE;
					}
					// 해당 몬스터가 없으면 예외 발생
				} catch (Exception e) {

				}
				Client c = client[num];
				d = Math.abs(hunter.x - c.x) + Math.abs(hunter.y - c.y);
				sum += d;
				hunter = new Point(c.x, c.y);
			}
		}
		return sum;
	}
}
