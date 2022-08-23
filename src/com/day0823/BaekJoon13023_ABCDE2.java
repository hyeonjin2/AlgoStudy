package com.day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BaekJoon13023_ABCDE2 {

	static int N, M, answer;
	static boolean[] visited;
	static List<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N]; // 정점의 수 만큼
		list = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		// 간선정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			// from <-> to
			list[from].add(to);
			list[to].add(from);
		}

		// 5명만 연결되어 있으면 됨. 각 정점(사람)에 대해 연결 정보가 존재하는지 조사해 봄.
		for (int i = 0; i < N; i++) {
			go(i, 1); // dfs 0번 정점부터 시작하고 조사된 인원수는 1

			if (answer > 0)
				break;
		}
		System.out.println(answer);
	}

	// 해당 정점(사람)부터 시작해서 4사람이 더 연결되어 있다면 ok.
	private static void go(int vertex, int cnt) {
		if (cnt == 5) { // 4사람이 연결되어 있는 경우 발견
			answer = 1;
			return;
		}
		visited[vertex] = true;
		for (int next : list[vertex]) {
			if (!visited[next]) {
				go(next, cnt + 1);
			}
		}
		visited[vertex] = false;
	}
}
