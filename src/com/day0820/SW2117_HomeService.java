package com.day0820;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW2117_HomeService {

	static class MyPoint extends Point {
		int x;
		int y;
		int level;

		public MyPoint(int x, int y, int level) {
			this.x = x;
			this.y = y;
			this.level = level;
		}

		@Override
		public String toString() {
			return "MyPoint [x=" + x + ", y=" + y + ", level=" + level + "]";
		}
	}

	static int[][] map;
	static boolean[][] visited;
	static int N, M, price, Ans;

	// bfs 4방 탐색 델타배열
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 배열의 크기
			M = Integer.parseInt(st.nextToken()); // 하나의 집이 지불할 수 있는 비용
			// 배열 초기화
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
				}
			}
			Ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
//					System.out.println("==========" + i + j + "==========");
					sum = 0;
					price = 0;
					bfs(new MyPoint(i, j, 1));
				}
			}
			sb.append("#").append(tc).append(" ").append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	// 집들을 중심으로 4방 탐색하여 비용을 비교해본다.
	private static void bfs(MyPoint start) {
		Queue<MyPoint> queue = new ArrayDeque<>();
		queue.offer(start);
		visited = new boolean[N][N];
		visited[start.x][start.y] = true;

		while (!queue.isEmpty()) {
			int size = queue.size();
			int curL = 0;
//			System.out.println(sum);
			while (--size >= 0) {
				MyPoint curr = queue.poll();
				curL = curr.level;
				if (map[curr.x][curr.y] == 1)
					sum++;
//				System.out.println(curr + " " + map[curr.x][curr.y]);
				for (int d = 0; d < 4; d++) {
					MyPoint next = new MyPoint(curr.x + dx[d], curr.y + dy[d], curr.level);
					// 배열의 범위 밖이면 건너뜀
					if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= N)
						continue;
					// 이미 방문한 집이면 건너뜀
					if (visited[next.x][next.y])
						continue;
					visited[next.x][next.y] = true;
					next.level = curr.level + 1;
					queue.offer(next);
				}
			}
			// 현재까지 레벨범위의 비용 계산
			// 비용 = M*집의 수 - K*K +(K-1)*(K-1)
			price = M * sum - curL * curL - (curL - 1) * (curL - 1);
			if (price >= 0) {
				Ans = Math.max(Ans, sum);
//				System.out.println(curL + " " + sum + " " + price);
			}
		}
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
 * 
 */
