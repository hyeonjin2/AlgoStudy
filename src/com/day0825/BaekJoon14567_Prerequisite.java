package com.day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon14567_Prerequisite {

	static class Node {
		int vertex;
		Node next;

		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
	}

	static int V, E;
	static Node[] adjList;
	static int[] inDegree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new Node[V + 1];
		inDegree = new int[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++;
		}
		int[] list = topologySort();
		StringBuilder sb = new StringBuilder();
		for (Integer i : list) {
			if (i == 0) {
				continue;
			}
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}

	private static int[] topologySort() {
		int[] list = new int[V + 1];
		Queue<Integer> queue = new ArrayDeque<>();

		// 진입차수 0인 정점 큐에 넣기
		for (int i = 1; i <= V; i++) {
			if (inDegree[i] == 0)
				queue.offer(i);
		}
		int cnt = 0;
		// BFS
		while (!queue.isEmpty()) {
			int size = queue.size();
			cnt++;
			for (int i = 0; i < size; i++) {
				int cur = queue.poll();
				list[cur] = cnt;

				for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
					if (--inDegree[temp.vertex] == 0)
						queue.offer(temp.vertex);
				}
			}
		}

		return list;
	}
}
