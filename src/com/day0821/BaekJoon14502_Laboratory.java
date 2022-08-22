package com.day0821;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon14502_Laboratory {

	static int N, M, Ans;
	static int[][] map;
	static int[][] copy;
	static List<Point> area;
	static List<Point> viruses;
	static Point[] walls;

	// 바이러스 4방 확산 델타 배열 상,하,좌,우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 배열 크기 입력 받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 배열 초기화
		map = new int[N][M];
		viruses = new ArrayList<>(); // 바이러스의 위치 정보를 담을 리스트
		area = new ArrayList<>(); // 벽이 세워질 수 있는 위치 정보를 담을 리스트
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if (num == 2) {
					viruses.add(new Point(i, j));
				} else if (num == 0) {
					area.add(new Point(i, j));
				}
			}
		}
		// 벽 3개를 세우는 경우 조합으로 구하기
		walls = new Point[3];
		comb(0, 0);

		System.out.println(Ans);
	}

	// 0~7 0~7 -> (0,1) (2,6) (3,1) 과 같이 조합을 뽑아야함.
	private static void comb(int cnt, int start) {
		if (cnt == 3) {
			// 벽을 세우면 바이러스가 퍼져나간 후의 안전구역 구하기
			search();
			// 안전구역 크기 구하기
			Ans = Math.max(Ans, calc());
			return;
		}
		for (int i = start, size = area.size(); i < size; i++) {
			walls[cnt] = area.get(i);
			comb(cnt + 1, i + 1);
		}
	}

	private static void search() {
		// 배열 복사해두기
		copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}
		// copy된 배열에 벽 세우기
		for (int i = 0; i < 3; i++) {
			Point tmp = walls[i];
			copy[tmp.x][tmp.y] = 1;
		}
		// 바이러스가 퍼질 수 있는 범위까지 퍼지기
		for (int i = 0, size = viruses.size(); i < size; i++) {
			bfs(viruses.get(i));
		}
	}

	// 바이러스가 4방으로 퍼지는 것을 copy에 담을 메소드
	private static void bfs(Point start) {
		Queue<Point> queue = new ArrayDeque<>();
//		System.out.println(start);
		boolean[][] visited = new boolean[N][M];
		visited[start.x][start.y] = true;
		queue.offer(start);

		while (!queue.isEmpty()) {
			Point curr = queue.poll();
			for (int d = 0; d < 4; d++) {
				Point next = new Point(curr.x + dx[d], curr.y + dy[d]);
				// 배열의 범위인지 확인
				if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= M)
					continue;
				// 방문한 적이 있는 곳이면 확산 x
				if (visited[next.x][next.y])
					continue;
				// 0인 구역이면 바이러스 확산
				if (copy[next.x][next.y] == 0) {
					visited[next.x][next.y] = true;
					copy[next.x][next.y] = 2;
					queue.offer(next);
				}
			}
		}
	}

	private static int calc() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 0)
					sum++;
			}
		}
		return sum;
	}
}
