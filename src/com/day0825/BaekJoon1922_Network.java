package com.day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon1922_Network {

	static class Node {
		int vertex, weight;
		Node next;

		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}

	static class Vertex {
		int no, weight;

		public Vertex(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());

		Node[] adjList = new Node[V];
		// 간선 정보 입력 받기.
		StringTokenizer st;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
		}
		// 프림 알고리즘
		int[] minEdge = new int[V];
		boolean[] visited = new boolean[V];
		// 정점의 가중치 맥스값으로 두기
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		// 시작점 가중치 0으로 두기
		minEdge[0] = 0;
		int result = 0; // 가중치의 합
		PriorityQueue<Vertex> pQueue = new PriorityQueue<>((v1, v2) -> v1.weight - v2.weight);
		pQueue.offer(new Vertex(0, minEdge[0]));

		int cnt = 0;
		while (!pQueue.isEmpty()) {

			Vertex minVertex = pQueue.poll();

			if (visited[minVertex.no])
				continue;

			visited[minVertex.no] = true;
			result += minVertex.weight;
			if (++cnt == V)
				break;

			for (Node temp = adjList[minVertex.no]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] && minEdge[temp.vertex] > temp.weight) {
					minEdge[temp.vertex] = temp.weight;
					pQueue.offer(new Vertex(temp.vertex, minEdge[temp.vertex]));
				}

			}
		}
		System.out.println(result);
	}
}
