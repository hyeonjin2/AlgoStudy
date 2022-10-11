package com.day1006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 맥주 마시면서 걸어가기
public class BaekJoon9205 {

	static class Point {
		int no; // 편의점 번호
		int x, y;

		public Point(int no, int x, int y) {
			this.no = no;
			this.x = x;
			this.y = y;
		}

		public int distance(Point p) {
			return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
		}

		@Override
		public String toString() {
			return "Point [no=" + no + ", x=" + x + ", y=" + y + "]";
		}

	}

	static int N;
	static boolean flag;
	static Point[] stores;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine()); // 맥주를 파는 편의점의 개수
			// 집 - 편의점 - 페스티벌 사이의 최단거리가 1000이하이면 happy
			// 아니면 sad
			StringTokenizer st = new StringTokenizer(br.readLine());
			Point start = new Point(0, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			stores = new Point[N];
			// 편의점 정보 저장하기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				stores[i] = new Point(i + 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			Point end = new Point(101, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			flag = false;
			bfs(start, end);
			if (flag)
				sb.append("happy").append("\n");
			else
				sb.append("sad").append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs(Point start, Point end) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(start);
		boolean[] visited = new boolean[102];
		visited[start.no] = true;
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			if (cur.distance(end) <= 1000) {
				flag = true;
				return;
			}
			for (int i = 0; i < N; i++) {
				// 거리가 1000미터 이하면 갈 수 있다.
				int distance = stores[i].distance(cur);
				if (!visited[stores[i].no] && distance <= 1000) {
					visited[stores[i].no] = true;
					queue.offer(stores[i]);
				}
			}
		}
	}
}
