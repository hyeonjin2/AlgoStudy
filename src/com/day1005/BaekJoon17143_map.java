package com.day1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

// 낚시왕
public class BaekJoon17143_map {

	static class Shark {
		int r, c, speed, dir, size;

		public Shark() {
		}

		public Shark(int r, int c, int speed, int dir, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}

	static int R, C, K, Ans;
	static int[][] map;
	static HashMap<Integer, Shark> sharks;

	// 상하좌우 1->2 2->1
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[R + 1][C + 1];
		sharks = new HashMap<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r][c] = z;
			sharks.put(z, new Shark(r, c, s, d, z));
		}
		// 낚시왕이 이동한다.
		for (int col = 1; col <= C; col++) { // 낚시왕의 위치
			// 낚시왕이 해당 열에 있는 상어 중 가장 가까운 상어를 한마리 잡는다.
			fishing(col);
			// 상어가 이동한다.
			moveShark();
		}
		System.out.println(Ans);
	}

	// 해당 컬럼에서 낚시
	private static void fishing(int col) {
		// 현재 걸럼에서 아래쪽으로 한칸씩 내려가 봄
		for (int row = 1; row <= R; row++) {
			// 상어를 발견하면 잡기
			if (map[row][col] != 0) {
				Ans += map[row][col];
				// 상어 삭제
				sharks.remove(map[row][col]);
				map[row][col] = 0;
				return;
			}
		}
	}

	// 상어가 이동하는 메소드
	// 1. 상어가 이동(방향대로 속도만큼 이동)
	// 2. 상어들 정리(한 칸에 여러 마리가 들어오는 경우)
	private static void moveShark() {
		// 이동 후의 상어들 표시한 배열
		int[][] temp = new int[R + 1][C + 1];
		Queue<Integer> losers = new ArrayDeque<>();

		// 1. 이동
		for (Integer key : sharks.keySet()) {
			Shark cur = sharks.get(key);
			map[cur.r][cur.c] = 0; // 상어가 있던 원래 위치는 0
			for (int i = 0; i < cur.speed; i++) {
				// 자신의 속도(speed)만큼 이동
				if (cur.dir == 1 && cur.r == 1) {
					cur.dir = 2;
				} else if (cur.dir == 2 && cur.r == R) {
					cur.dir = 1;
				} else if (cur.dir == 3 && cur.c == C) {
					cur.dir = 4;
				} else if (cur.dir == 4 && cur.c == 1) {
					cur.dir = 3;
				}

				cur.r += dr[cur.dir];
				cur.c += dc[cur.dir];
			}
			// 상어가 자기 속도만큼 다 이동한 뒤 상어가 가지고 있는 좌표(cur.r, cur.c)
			// 2. 이동 후 위치가 겹치는지 체크 후 정리
			if (temp[cur.r][cur.c] == 0) {
				temp[cur.r][cur.c] = cur.size;
			} else if (temp[cur.r][cur.c] < cur.size) {
				losers.offer(temp[cur.r][cur.c]);
				temp[cur.r][cur.c] = cur.size;
			} else {
				losers.offer(cur.size);
			}
		}
		// 삭제할 모든 상어들이 losers에 들어가 있음
		while (!losers.isEmpty()) {
			sharks.remove(losers.poll());
		}
		// 이후에 살아남은 상어들 정보 반영
		for (Integer key : sharks.keySet()) {
			Shark s = sharks.get(key);
			map[s.r][s.c] = temp[s.r][s.c];
		}
	}
}
