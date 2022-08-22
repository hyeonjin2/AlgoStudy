package com.day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW1238_Contact2 {

	static class Node {
		int to;
		Node next;

		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}

	static int Ans;
	static Node[] adjList;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 간선의 수
			int N = Integer.parseInt(st.nextToken());
			// 시작점
			int start = Integer.parseInt(st.nextToken());
			// 배열 초기화
			adjList = new Node[101];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
			}
			// 동시에 통화 가능하므로 bfs구현
			bfs(start);
			sb.append("#").append(tc).append(" ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		visited = new boolean[101];
		visited[start] = true;
		while (!queue.isEmpty()) {
			// 가장 마지막에 연락한 사람 중 가장 큰 번호를 구해야하므로 같은 레벨끼리 검사
			int size = queue.size();
			Ans = 0;
			for (int k = 0; k < size; k++) {
				int cur = queue.poll();
				Ans = Math.max(Ans, cur);
				for (Node tmp = adjList[cur]; tmp != null; tmp = tmp.next) {
					if (!visited[tmp.to]) {
						visited[tmp.to] = true;
						queue.offer(tmp.to);
					}
				}
			}
		}
	}


}
