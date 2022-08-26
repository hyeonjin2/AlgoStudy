package com.day0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//게리맨더링
public class BaekJoon17471 {

	static class Node {
		int to;
		Node next;

		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}

	static int N, Ans;
	static Node[] adjList;
	static List<Integer> region1, region2;
	static int[] persons, parents;
	static boolean[] selected, visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		persons = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			persons[i] = Integer.parseInt(st.nextToken());
		}
		adjList = new Node[N + 1];
		for (int from = 1; from <= N; from++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				int to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
				adjList[to] = new Node(from, adjList[to]);
			}
		}
		// 두 개의 선거구로 나누기
		selected = new boolean[N + 1];
		Ans = Integer.MAX_VALUE;
		subSet(1, 0, 0);
		if (Ans == Integer.MAX_VALUE)
			Ans = -1;
		System.out.println(Ans);
	}

	private static void subSet(int index, int sum1, int sum2) {
		if (index == N + 1) {
			// 두 선거구가 잘 구성되어있는지 확인
			if (sum1 > 0 && sum2 > 0 && check()) {
				int diff = Math.abs(sum1 - sum2);
				Ans = Math.min(Ans, diff);
			}
			return;
		}
		selected[index] = true;
		subSet(index + 1, sum1 + persons[index], sum2);
		selected[index] = false;
		subSet(index + 1, sum1, sum2 + persons[index]);
	}

	private static boolean check() {
		region1 = new ArrayList<>();
		region2 = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			if (selected[i]) {
				region1.add(i);
			} else {
				region2.add(i);
			}
		}
		// 선거구1이 잘 연결되어 있는지 확인
		int count1 = 0;
		int count2 = 0;
		visited = new boolean[N + 1];
		// 모두 인접해 있는지 확인
		for (int i = 0, size = region1.size(); i < size; i++) {
			int from = region1.get(i);
			if (!visited[from]) {
				count1 += dfs(from, 1);
			}
		}
		visited = new boolean[N + 1];
		// 모두 인접해 있는지 확인
		for (int i = 0, size = region2.size(); i < size; i++) {
			int from = region2.get(i);
			if (!visited[from]) {
				count2 += dfs(from, 2);
			}
		}

		if (count1 == 1 && count2 == 1) {
			return true;
		}
		return false;
	}

	private static int dfs(int cur, int ver) {
		visited[cur] = true;
		for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
			if (!visited[temp.to]) {
				if (ver == 1 && region1.contains(temp.to))
					dfs(temp.to, ver);
				else if (ver == 2 && region2.contains(temp.to))
					dfs(temp.to, ver);
			}
		}
		return 1;
	}
}
