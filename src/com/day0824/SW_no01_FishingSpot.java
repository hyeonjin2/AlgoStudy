package com.day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_no01_FishingSpot {

	static class Gate {
		int x;
		int n;

		public Gate(int x, int n) {
			this.x = x;
			this.n = n;
		}
	}

	static int N, Ans;
	static Gate[] gates;
	static int[] selected;
	static int[] spotL, spotR;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 낚시터 자리 개수
			// 낚시터 정보 입력받기
			gates = new Gate[3];
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				gates[i] = new Gate(x, n);
			}
			// bfs돌릴 낚시터 순서 뽑기
			selected = new int[3]; // 낚시터의 인덱스 순서가 들어감.
			spotL = new int[N + 1];
			spotR = new int[N + 1];
			perm(0, 0);

			sb.append("#").append(tc).append(" ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	static int start, totalCnt;

	private static void perm(int cnt, int flag) {
		if (cnt == 3) {
			// 탐색
			for (int i = 0; i < 3; i++) {
				start = gates[selected[i]].x;
				totalCnt = gates[selected[i]].n;
				search(0, 0, 0);
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			selected[cnt] = i;
			perm(cnt + 1, flag | 1 << i);
		}
	}

	private static void search(int cnt, int left, int right) {
		if (cnt == totalCnt) {
			
			return;
		}
//		search(cnt+1,)
	}
}
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
