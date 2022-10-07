package com.day1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 다리 만들기2
public class BaekJoon17472 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int distance(Point p) {
			return this.x == p.x ? Math.abs(this.y - p.y) : Math.abs(this.x - p.x);
		}
	}

	static int N, M, ind;
	static int[][] map, group;
	static List<Point> lands;
	static List<int[]> bridge;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		lands = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0)
					lands.add(new Point(i, j));
			}
		}
		// 섬 그룹 지어주기->일직선으로 출발했을 때 같은 땅의 다른 부분에 도착할 수 있기 때문.
		// 아직 다리를 짓기 전이므로 dfs로 섬을 그룹핑한다.
		group = new int[N][M];
		for (int i = 0; i < lands.size(); i++) {
			Point cur = lands.get(i);
			// 그룹에 속해있지 않다면 그룹 만들어주기
			if (group[cur.x][cur.y] == 0) {
				ind++;
				grouping(cur);
			}
		}
		map = group;
		// 세로,가로 탐색을 통해 섬이 2개 이상있는지 확인
		bridge = new ArrayList<>();
		makeBridge();
		// 다리길이의 오름차순으로 정렬
		Collections.sort(bridge, (e1, e2) -> e1[0] - e2[0]);
		makeSet();
		int Ans = 0;
		int cnt = 0;
		for (int i = 0; i < bridge.size(); i++) {
			int[] cur = bridge.get(i);
			if (union(cur[1], cur[2])) {
				Ans += cur[0];
				cnt++;
			}
		}
		if (cnt != ind - 1) {
			Ans = -1;
		}
		System.out.println(Ans);
	}

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	// 섬들을 그룹핑하는 메소드
	private static void grouping(Point start) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(start);
		group[start.x][start.y] = ind;
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				// 배열의 범위 밖이거나 이미 그룹에 속해있다면 건너뛰기.
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 0 || group[nx][ny] != 0)
					continue;
				group[nx][ny] = ind;
				queue.offer(new Point(nx, ny));
			}
		}
	}

	// 섬에서 다른 섬으로 다리를 지어보는 메소드
	private static void makeBridge() {
		Queue<Point> queue = new ArrayDeque<>();
		for (Point p : lands) {
			queue.offer(p);
		}
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int d = 0; d < 2; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				while (nx >= 0 && nx < N && ny >= 0 && ny < M && group[cur.x][cur.y] != group[nx][ny]) {
					if (map[nx][ny] != 0 && group[cur.x][cur.y] != group[nx][ny]) {
						// 다른 섬을 발견하면 다리 정보 저장(출발점,도착점)
						Point p = new Point(nx, ny);
						int distance = cur.distance(p) - 1;
						if (distance > 1) {
							bridge.add(new int[] { distance, group[cur.x][cur.y], group[nx][ny] });
						}
						break;
					}
					nx += dx[d];
					ny += dy[d];
				}
			}
		}
	}

	private static void makeSet() {
		parents = new int[ind + 1];
		for (int i = 1; i <= ind; i++) {
			parents[i] = i;
		}
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
	}

	private static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}
}
