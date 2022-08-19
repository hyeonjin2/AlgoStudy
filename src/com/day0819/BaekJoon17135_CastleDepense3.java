package com.day0819;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

// 궁수들의 위치를 어떻게 정해서 쏠거냐 - 조합
public class BaekJoon17135_CastleDepense3 {

	static int N, M, D;
	static int max; // 답
	static int[] selected; // 조합. 3명의 궁수정보가 저장됨.
	static List<Enemy> elist; // 적들의 정보

	static class Enemy {
		int x, y;

		public Enemy(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 가로 행
		M = sc.nextInt(); // 세로 열
		D = sc.nextInt(); // 유효거리
		selected = new int[3];

		elist = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (sc.nextInt() == 1)
					elist.add(new Enemy(i, j));
			}
		}
		// 궁수들의 조합생성해서 계산 : 궁수위치(N+1번째, 열)
		combi(0, 0);

		System.out.println(max);
	}

	private static void combi(int cnt, int start) {
		if (cnt == 3) {
			fire();
			return;
		}
		for (int i = start; i < M; i++) { // 열의 수 중에서
			selected[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}

	// 궁수조합이 완성될 때마다 호출
	private static void fire() {
		List<Enemy> copy = new ArrayList<>();
		for (Enemy e : elist) {
			copy.add(new Enemy(e.x, e.y));
		}

		int killed = gameStart(copy, selected); // 이 궁수의 조합을 이용해서 공격
		max = Math.max(max, killed);
	}

	// archers[] : 궁수들의 열값 정보(0,1,2) - 이 궁수의 조합을 이용해서 공격
	private static int gameStart(List<Enemy> list, int[] archers) {
		int cnt = 0; // 공격한 적의 수
		while (list.size() != 0) { // 적이 한명이라도 있으면
			// 3명의 궁수가 동시에 같은 적을 공격할 수도 있기 때문에 바로 공격하면 안되고 찜을 해놨다가 한번에 공격해야됨.
			List<Enemy> tmp = new ArrayList<>();
			for (int y : archers) {// y : 궁수들의 y값
				int targetIndex = findNear(list, y); // y위치에 있는 궁수한테서 제일 가까운 적의 인덱스를 받음.
				if (targetIndex >= 0) { // 공격할 적이 있다면
					tmp.add(list.get(targetIndex)); // 임시 리스트에 저장
				}
			}
			// 궁수 3명이 모두 tmp에 공격할 적의 정보를 저장한 후
			// 공격
			for (Enemy e : tmp) {
				if (list.remove(e))
					cnt++; // 공격당한 적의 수를 증가
			}

			// 적군이 움직임
			// 공격당하지 않은 적들의 정보만 있는 리스트를 전달
			enemyDown(list);
		}
		return cnt;
	}

	// 궁수의 위치(y)에서 가장 가까운 적을 찾아서 인덱스를 리턴
	private static int findNear(List<Enemy> list, int y) {
		int index = -1;
		int minDistance = Integer.MAX_VALUE; // 적-궁수간 거리
		int minColumn = Integer.MAX_VALUE; // 발견된 적의 열 값. 작은 값이 더 우선(더 왼쪽이니까)

		for (int i = 0, size = list.size(); i < size; i++) { // 모든 적에 대해 조사
			Enemy e = list.get(i);

			// 거리 계산(궁수-적)
			int d = (N - e.x) + Math.abs(y - e.y);
			if (d > D)
				continue;
			if (d < minDistance) { // 기존 정보보다 더 짧은 거리의 적이 발견되면
				minDistance = d;
				minColumn = e.y;
				index = i;
			} else if (d == minDistance) { // 같은 거리의 적이 발견되면
				if (e.y < minColumn) { // 누가 더 왼쪽이니?
					minColumn = e.y;
					index = i;
				}
			}
		}
		return index;
	}

	private static void enemyDown(List<Enemy> list) {
		Iterator<Enemy> it = list.iterator();
		while (it.hasNext()) { // 내용물이 있는 동안
			Enemy e = it.next();
			e.x++;
			if (e.x == N)
				it.remove();
		}
	}
}
