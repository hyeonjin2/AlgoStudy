package com.day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon16236_BabyShark2 {

	static class Fish {
		int x, y;
		int d;

		public Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	static int N;
	static Queue<Fish> queue;
	static List<Fish> fishes;
	static Fish baby;
	static int sharkSize, sharkCnt;
	static boolean[][] visited;
	static int[][] map;

	// 먹을 수 있는 상어 위치 찾기
	// 먹을 수 있는 상어가 하나라면 먹으러 가기
	// 여러개라면 가장 위쪽, 가장 왼쪽에 있는 물고기 먹기
	// 상어가 먹은 물고기의 수 확인 후 크기가 커질 수 있는지 보기
	// 반복
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 입력받기
		map = new int[N][N];
		queue = new ArrayDeque<>();
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 9) {
					// 아기 상어 위치 저장해두기
					baby = new Fish(i, j, 0);
					visited[i][j] = true;
					queue.add(baby);
					sharkSize = 2;
					continue;
				}
				map[i][j] = num;
			}
		}
		// 먹을 수 있는 상어 위치 찾기
		while (true) {
			fishes = new ArrayList<>();
			bfs();
			if (fishes.size() == 0) {
				break;
			}
			// 먹을 수 있는 상어가 하나라면 먹으러 가기
			// 여러개라면 가장 위쪽, 가장 왼쪽에 있는 물고기 먹기
			Collections.sort(fishes, (e1, e2) -> {
				// 거리가 같다면
				// row 기준 오름차순 row가 같다면 col 기준 오름차순 정렬
				return e1.x != e2.x ? e1.x - e2.x : e1.y - e2.y;
			});
			// 상어 먹기
			// 상어 위치로 이동, 아기상어 시간 증가
			Fish tmp = fishes.get(0);
			int t = baby.d;
			map[baby.x][baby.y] = 0;
			baby = new Fish(tmp.x, tmp.y, tmp.d);
			// 아기상어가 먹은 상어 수 증가
			sharkCnt++;
			// 배열에 상어자리 0
			map[tmp.x][tmp.y] = 0;
			// 상어 사이즈 확인
			if (sharkSize == sharkCnt) {
				sharkSize++;
				sharkCnt = 0;
			}
			visited = new boolean[N][N];
			queue.clear();
			queue.offer(baby);
			visited[baby.x][baby.y] = true;
		}
		System.out.println(baby.d);
		// 더 이상 먹을 수 있는 상어가 없을 때까지 반복 -> 리스트의 사이즈가 0이 될 때 까지
	}

	// 4방 탐색 델타 배열 상,좌,우,하
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int level; // 아기상어와의 거리

	private static void bfs() {
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Fish cur = queue.poll();
				// 먹을 수 있는 상어라면 상어 리스트에 넣기
				if (map[cur.x][cur.y] != 0 && map[cur.x][cur.y] < sharkSize) {
					fishes.add(cur);
				}
				// 4방 탐색하기
				for (int d = 0; d < 4; d++) {
					Fish next = new Fish(cur.x + dx[d], cur.y + dy[d], cur.d + 1);
					// 배열의 범위 밖이면 탐색 중지
					if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= N)
						continue;
					if (!visited[next.x][next.y] && map[next.x][next.y] <= sharkSize) {
						visited[next.x][next.y] = true;
						queue.offer(next);
					}
				}
			}
			if (fishes.size() != 0) {
				return;
			}
		}
	}
}
