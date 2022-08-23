package com.day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon13023_ABCDE {

	static class Node {
		int to;
		Node next;

		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}

	static int N, M, max, Ans;
	static Node[] adjList;
	static int[] friends;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new Node[N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			dfs(i, 0);
		}
		System.out.println(0);
	}

	private static void dfs(int cur, int cnt) {
		if (cnt == 4) {
			System.out.println(1);
			System.exit(0);
		}
		visited[cur] = true;
		for (Node tmp = adjList[cur]; tmp != null; tmp = tmp.next) {
			if (!visited[tmp.to]) {
				dfs(tmp.to, cnt + 1);
			}
		}
		visited[cur] = false;
	}
}
