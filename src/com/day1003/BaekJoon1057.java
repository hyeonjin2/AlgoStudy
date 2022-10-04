package com.day1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1057 {
	static class Egg {
		int d, w;

		public Egg(int d, int w) {
			this.d = d;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Egg [d=" + d + ", w=" + w + "]";
		}
	}

	static int N, Ans;
	static Egg[] eggs, game;
	static int[] order;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		eggs = new Egg[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		order = new int[N];
		perm(1, 0);
		System.out.println(Ans);
	}

	// 오른쪽 달걀을 집는 순서 정하기
	private static void perm(int cnt, int flag) {
		if (cnt == N) {
			game = new Egg[N];
			copy(game, eggs);
			System.out.println(Arrays.toString(order));
			int result = calc();
			System.out.println(result);
			Ans = Math.max(Ans, result);
			return;
		}
		for (int i = 1; i < N; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			order[cnt] = i;
			perm(cnt + 1, flag | 1 << i);
		}
	}

	// 깨지는 달걀의 수 계산
	private static int calc() {
		int cnt = 0;
		while (true) {
			// 왼쪽 달걀 들기
			Egg left = null;
			int indL = 0;
			for (int i = 0; i < N; i++) {
				if (game[order[i]].d <= 0)
					continue;
				left = game[order[i]];
				indL = i;
				break;
			}
			// 오른쪽 달걀 들기
			Egg right = null;
			for (int i = indL + 1; i < N; i++) {
				if (game[order[i]].d <= 0)
					continue;
				right = game[order[i]];
				break;
			}
			// 부딪힐 오른쪽 달걀이 없으면 개수 리턴
			System.out.println(left + " " + right);
			if (right == null)
				break;
			// 달걀 부딪혀보기
			left.d -= right.w;
			right.d -= left.w;
		}
		// 깨진 달걀의 수 세보기
		for (int i = 0; i < N; i++) {
			if (game[i].d <= 0)
				cnt++;
		}
		return cnt;
	}

	private static void copy(Egg[] g, Egg[] e) {
		for (int i = 0; i < N; i++) {
			g[i] = new Egg(e[i].d, e[i].w);
		}
	}

}
