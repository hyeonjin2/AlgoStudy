package com.day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11724_NumberOfLink {

	static class Node {
		int to;
		Node next;

		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}

	static Node[] adjList;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		adjList = new Node[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}
		visited = new boolean[N + 1];
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			if (!visited[i])
				sum += dfs(i);
		}
		System.out.println(sum);
	}

	private static int dfs(int cur) {
		visited[cur] = true;
		for (Node tmp = adjList[cur]; tmp != null; tmp = tmp.next) {
			if (!visited[tmp.to]) {
				dfs(tmp.to);
			}
		}
		return 1;
	}
}
