package com.day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW3289_Disjoint {

	static int N, M;
	static int[] parents;

	// 크기가 1인 서로소 집합 만들기
	static void make() {
		parents = new int[N + 1];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	// 부모 인덱스 반환
	static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	// 부모가 서로 다르다면 연산 수행
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) {
			return false;
		}
		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			make();
			sb.append("#").append(tc).append(" ");

			// M번 연산 수행
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				// 합집합 연산
				if (num == 0) {
					union(a, b);
					// 같은 집합에 포함되어있는지의 여부 -> 같은 집합에 속해있다면 1, 아니면 0
				} else if (num == 1) {
					int aRoot = find(a);
					int bRoot = find(b);
					if (aRoot == bRoot) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
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
 */
