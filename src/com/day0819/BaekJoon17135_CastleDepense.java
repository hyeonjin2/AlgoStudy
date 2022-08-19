package com.day0819;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class BaekJoon17135_CastleDepense {

	static int N, M, D, Cnt, cntTotal, cntKill, answer;
	static List<Enemy>[] enemy;
	static int[][] map, game;
	static int[] selected; // 조합으로 뽑인 궁수들의 인덱스

	static class Enemy {
		Point p;
		int distance;

		public Enemy(Point p, int distance) {
			this.p = p;
			this.distance = distance;
		}

		@Override
		public String toString() {
			return "Enemy [p=" + p + ", distance=" + distance + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		game = new int[N][M];
		selected = new int[3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 적의 수 파악해두기
				if (map[i][j] == 1)
					Cnt++;
			}
		}
		comb(0, 0);
		System.out.println(answer);
	}

	// 궁수의 위치 mC3으로 뽑기 [0,1,2,3,4] 뽑은 조합을 궁수의 인덱스로 사용
	private static void comb(int cnt, int start) {
		if (cnt == 3) { // 궁수는 3명을 둔다.
			// 뽑은 궁수의 위치마다 적까지의 거리 계산하기
			// 남은 적이 없을 때까지 반복해서 계산
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					game[i][j] = map[i][j];
				}
			}
			cntTotal = Cnt;
			cntKill = 0;
			while (cntTotal > 0) {
				calc();
			}
			answer = Math.max(answer, cntKill);
			return;
		}
		for (int i = start; i < M; i++) {
			selected[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	// 뽑은 궁수의 위치마다 적까지의 거리 계산후 공격
	private static void calc() {
		enemy = new ArrayList[3];
		for (int k = 0; k < 3; k++) {
			// 궁수의 위치 point 배열에 저장
			Point archer = new Point(N, selected[k]);
			// 적과의 거리 계산 후 공격할 수 있는 범위의 적 배열에 저장
			enemy[k] = new ArrayList<Enemy>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 배열의 값이 1이면 -> 적이 있으면
					if (game[i][j] == 1) { // 궁수과의 거리 계산
						int distance = Math.abs(i - archer.x) + Math.abs(j - archer.y);
						// 공격할 수 있는 범위 내의 적이면 리스트에 저장
						if (distance <= D)
							enemy[k].add(new Enemy(new Point(i, j), distance));
					}
				}
			}
		}
		// 공격할 수 있는 적 찾기 -> 가장 가깝고 왼쪽에 있는 적 -> 열 값이 가장 작은 적
		HashSet<Point> target = new HashSet<>();
		for (int k = 0; k < 3; k++) {
			int minC = M;
			int minD = Integer.MAX_VALUE;
			int idx = -1;
			for (int i = 0, size = enemy[k].size(); i < size; i++) {
				Enemy e = enemy[k].get(i);
				if (minD > e.distance) {
					minD = e.distance;
					minC = e.p.y;
					idx = i;
				} else if (minD == e.distance) {
					if (minC > e.p.y) {
						minC = e.p.y;
						idx = i;
					}
				}
			}
			if (idx >= 0) {
				target.add(enemy[k].get(idx).p);
			}
		}
		// 적 공격하기
		// 배열에서 1->0바꾸기
		for (Point e : target) {
			game[e.x][e.y] = 0;
			cntKill++;
			cntTotal--;
		}

		// 적들 아래로 한칸 옮기기
		move();

	}

	// map 배열 아래로 한칸 씩 이동시키기 N-2행 부터 옮기고 0번째 행 모두 0으로 바꾸기
	private static void move() {
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if (i == 0) {
					game[0][j] = 0;
					continue;
				}
				// 이동 후 성벽에 닿는 적의 수 빼주기
				if (i == N - 1 && game[i][j] == 1) {
					cntTotal--;
				}
				game[i][j] = game[i - 1][j];
			}
		}
	}
}
