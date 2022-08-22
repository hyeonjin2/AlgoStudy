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

public class BaekJoon18428_AvoidWatching {

	static int N, Cnt;
	static boolean notFind;
	static char[][] map;
	static char[][] copy;
	static Point[] obstacles;
	static List<Point> teachers;
	static List<Point> others;
	// 4방 탐색 델타 배열 상,하,좌,우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 배열 초기화
		// 선생님의 위치 기억해두기
		map = new char[N][N];
		teachers = new ArrayList<>();
		others = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] == 'T') {
					teachers.add(new Point(i, j));
				} else if (map[i][j] == 'X') {
					others.add(new Point(i, j));
				}
			}
		}
		// 장애물 고르기 -> 조합
		obstacles = new Point[3];
		comb(0, 0);
		String answer = notFind ? "YES" : "NO";
		System.out.println(answer);
	}

	private static void comb(int cnt, int start) {
		if (cnt == 3) {
			// 모든 학생이 감시를 피할 수 있는지 계산
			Cnt = 0;
			search();
			if (Cnt == 0)
				notFind = true;
			return;
		}
		for (int i = start, size = others.size(); i < size; i++) {
			obstacles[cnt] = others.get(i);
			comb(cnt + 1, i + 1);
		}
	}

	// 선생님들은 학생들을 감시 -> 4방으로 감시 dfs 메소드
	private static void search() {
		// 맵 copy 해두기
		copy = new char[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = map[i][j];
			}
		}
		// 장애물 세우기
		for (int i = 0; i < 3; i++) {
			Point tmp = obstacles[i];
			copy[tmp.x][tmp.y] = 'O';
		}
		// 선생님들 별로 dfs 학생 탐색
		for (int i = 0, size = teachers.size(); i < size; i++) {
			bfs(teachers.get(i));
		}
	}

	// 학생을 한명이라도 발견하면 return
	private static void bfs(Point start) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(start);
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			if (copy[cur.x][cur.y] == 'S')
				Cnt++;
			for (int d = 0; d < 4; d++) {
				Point next = new Point(cur.x + dx[d], cur.y + dy[d]);
				// 해당 방향으로 장애물이나 배열 밖으로 벗어나기 전까지 탐색
				while (next.x >= 0 && next.x < N && next.y >= 0 && next.y < N) {
					// 방문한 적 있거나 장애물이 있으면 건너뜀
					if (copy[next.x][next.y] == 'O')
						break;
					if (copy[next.x][next.y] == 'S') {
						Cnt++;
						break;
					}
					next = new Point(next.x + dx[d], next.y + dy[d]);
				}
			}
		}
	}
}
