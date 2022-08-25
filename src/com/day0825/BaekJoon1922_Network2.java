package com.day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1922_Network2 {

	static class Edge implements Comparable<Edge> {
		int from, to, weigth;

		public Edge(int from, int to, int weigth) {
			this.from = from;
			this.to = to;
			this.weigth = weigth;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weigth - o.weigth;
		}
	}

	static int V, E;
	static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());

		// 간선 정보 입력 받기.
		Edge[] edgeList = new Edge[E];
		StringTokenizer st;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());

			edgeList[i] = new Edge(from, to, weight);
		}

		make();
		Arrays.sort(edgeList);
		int cnt = 0;
		int sum = 0;
		for (int i = 0; i < E; i++) {
			Edge temp = edgeList[i];
			if (union(temp.from, temp.to)) {
				sum += temp.weigth;
				if (++cnt == V - 1)
					break;
			}
		}
		System.out.println(sum);
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
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}
}
