
package com.day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon1753_ShortestPathPQ {

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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int start = Integer.parseInt(br.readLine()); // 시작점

		Node[] adjList = new Node[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, weight, adjList[from]);
		}
		int cnt = 0;
		int[] result = new int[V + 1];
		int[] D = new int[V + 1];
		Arrays.fill(result, Integer.MAX_VALUE);
		boolean[] visited = new boolean[V + 1];
		Arrays.fill(D, Integer.MAX_VALUE);
		PriorityQueue<Vertex> queue = new PriorityQueue<>((v1, v2) -> v1.weight - v2.weight);
		queue.offer(new Vertex(start, 0));
		D[start] = 0;
//		System.out.println("============" + end + "=============");
		// step1. 미방문 정점 중 출발지에서 자신으로의 비용이 최소인 정점 선택
		// 방문해야하는 나머지 정점 중 출발지에서 가장 가까운 정점 찾기
		while (!queue.isEmpty()) {
			cnt++;
			Vertex minVertex = queue.poll();
//			System.out.println(minVertex.no + " " + D[minVertex.no]);
			// 이미 방문했던 정점이면 최소값이 아님 -> 건너뛰기
			if (visited[minVertex.no])
				continue;
			// step2. 방문처리
			visited[minVertex.no] = true;
			result[minVertex.no] = D[minVertex.no];

			// step3. 선택된 정점을 경유지로 해서 미방문 정점들로 가는 비용을 따져보고 기존 최적해보다 유리하면 갱신
			for (Node temp = adjList[minVertex.no]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] && D[temp.vertex] > D[minVertex.no] + temp.weight) {
					D[temp.vertex] = D[minVertex.no] + temp.weight;
					queue.offer(new Vertex(temp.vertex, D[temp.vertex]));
				}

			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (result[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
				continue;
			}
			sb.append(result[i]).append("\n");
		}
		System.out.println(sb);
//		System.out.println("cnt : " + cnt);
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
 * 
 * 
 * 
 */
