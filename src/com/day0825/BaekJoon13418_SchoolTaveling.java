package com.day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon13418_SchoolTaveling {

	static class Edge {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	static int N, M;
	static int[] parents;
	static Edge[] edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) + 1; // 정점의 수
		M = Integer.parseInt(st.nextToken()) + 1; // 간선의 수
		// 간선 정보 입력 받기
		edgeList = new Edge[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			weight = 1 - weight;
			edgeList[i] = new Edge(from, to, weight);
		}
		make();
		// 최선의 경로
		Arrays.sort(edgeList, (e1, e2) -> {
			return e1.weight - e2.weight;
		});
		int min = 0;
		int count = 0;
		for (int i = 0; i < M; i++) {
			Edge tmp = edgeList[i];
			if (union(tmp.from, tmp.to)) {
				min += tmp.weight;
				if (++count == N - 1)
					break;
			}
		}
		make();
		// 최악의 경로
		Arrays.sort(edgeList, (e1, e2) -> {
			return e2.weight - e1.weight;
		});
		int max = 0;
		count = 0;
		for (int i = 0; i < M; i++) {
			Edge tmp = edgeList[i];
			if (union(tmp.from, tmp.to)) {
				max += tmp.weight;
				if (++count == N - 1)
					break;
			}
		}
		System.out.println(max * max - min * min);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;
	}

	private static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	private static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
}
