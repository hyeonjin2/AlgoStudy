package com.day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon19236_TeenagerShark {

	static class Fish {
		int x, y;
		int n, d;

		public Fish(int x, int y, int n, int d) {
			this.x = x;
			this.y = y;
			this.n = n;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Fish [x=" + x + ", y=" + y + ", n=" + n + ", d=" + d + "]";
		}
	}

	static Fish[][] fishes;
	static Fish[] fishList;
	static Fish shark;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 배열 초기화
		// 2차원 배열에 저장
		fishes = new Fish[4][4];
		// 번호 순서대로 저장
		fishList = new Fish[17];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				fishes[i][j] = new Fish(i, j, num, dir - 1);
				fishList[num] = fishes[i][j];
			}
		}
		// 0,0에 있는 물고기를 먹는다.
		eatFish(fishes[0][0]);
		if (fishes[0][0].equals(shark))
			System.out.println("true");

		// 물고기가 이동한다.
		// 번호순서대로 이동. 상어가 있거나 경계를 넘으면 이동 X
		// 물고기는 이동할 수 있는 칸을 향할 때까지 방향이 45도 반시계 회전한다.
		// 물고기가 있는 곳으로 이동하면 서로 자리를 바꾼다.

		// 상어가 이동한다.
		// 상어는 여러개 칸을 이동할 수 있다.
		// 상어가 먹을 수 있는 물고기 번호의 합 최대 출력
	}

	// 8방향 이동 델타 배열 상,좌상,좌,좌하,하,우하,우,우상
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	private static void eatFish(Fish eat) {
		// 물고기를 먹는다.상어의 방향은 먹은 물고기의 방향과 같아진다.
		shark = new Fish(eat.x, eat.y, 0, eat.d);
		fishes[eat.x][eat.y] = shark;
		// 물고기가 이동한다.
		moveFish();
	}

	// 물고기가 이동한다.
	private static void moveFish() {
		// 번호순서대로 이동.
		for (int i = 1; i <= 16; i++) {
			Fish cur = fishList[i];
			System.out.println(cur);
			int d = cur.d;
			Fish next = new Fish(cur.x + dx[d], cur.y + dy[d], cur.n, cur.d);
			// 상어가 있거나 경계를 넘으면 이동 X -> 물고기는 이동할 수 있는 칸을 향할 때까지 방향이 45도 반시계 회전한다.
			if (next.x < 0 || next.x >= 4 || next.y < 0 || next.y >= 4 || fishes[next.x][next.y].equals(shark)) {
				// 물고기의 이동방향 정하기
				System.out.println(next.n + " " + next.d);
				d = setDir(next);
				next = new Fish(cur.x + dx[d], cur.y + dy[d], cur.n, d);
				System.out.println(next.n + " " + next.d);
			}
			// 물고기가 있는 곳으로 이동하면 서로 자리를 바꾼다.
			if (fishes[next.x][next.y].n != 0) {
				Fish tmp = fishes[next.x][next.y];
				fishes[next.x][next.y] = next;
				fishes[cur.x][cur.y] = tmp;
				System.out.println("switch" + next + "<->" + tmp);
			}
//			print(fishes);
		}
	}

	// 물고기의 이동방향 정하기
	private static int setDir(Fish cur) {
		int d = (cur.d + 1) % 8;
		Fish next = new Fish(cur.x + dx[d], cur.y + dy[d], cur.n, d);
		System.out.println(next.n + " " + next.d);
		if (next.x < 0 || next.x >= 4 || next.y < 0 || next.y >= 4 || fishes[next.x][next.y].equals(shark)) {
//			System.out.println(next.n + " " + d);
			d = setDir(next);
		}
		return d;
	}

	private static void print(Fish[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
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
 */
