package com.day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SW5644_WirelessCharging {

	static class Point { // 좌표의 정보를 담는 클래스
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Charger { // 충전기의 정보를 담는 클래스
		Point point;
		int c, p;

		public Charger(Point point, int c, int p) {
			this.point = point;
			this.c = c;
			this.p = p;
		}
	}

	// 사용자 이동 델타 배열 이동x,상,우,하,좌
	static int[] dr = { 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 1, 0, -1 };
	static int time, numBC, Ans;
	static int[] timeA, timeB;
	static Charger[] chargers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time = Integer.parseInt(st.nextToken()) + 1;
			numBC = Integer.parseInt(st.nextToken());
			chargers = new Charger[numBC]; // 충전기들의 정보를 담을 배열
			// 사용자의 이동정보를 담을 배열
			timeA = new int[time];
			timeB = new int[time];
			// 사용자 이동배열 초기화
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j < time; j++) {
					if (i == 0) {
						timeA[j] = Integer.parseInt(st.nextToken());
						continue;
					}
					timeB[j] = Integer.parseInt(st.nextToken());
				}
			}
			// 충전기 배열 초기화
			for (int i = 0; i < numBC; i++) {
				st = new StringTokenizer(br.readLine());
				int tmpC = Integer.parseInt(st.nextToken());
				int tmpR = Integer.parseInt(st.nextToken());
				Point p = new Point(tmpR, tmpC);
				chargers[i] = new Charger(p, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			Ans = 0;
			calc();
			// 출력
			System.out.println("#" + tc + " " + Ans);
		}
	}

	// A, B의 위치에 따라 충전량을 계산하는 메소드
	private static void calc() {
		Point a = new Point(1, 1);
		Point b = new Point(10, 10);
		// BC를 기준으로 A, B의 충전량 계산
		for (int j = 0; j < time; j++) {
			// A, B의 이동 후 좌표
			a.r += dr[timeA[j]];
			a.c += dc[timeA[j]];
			b.r += dr[timeB[j]];
			b.c += dc[timeB[j]];
			List<Charger> inA = new ArrayList<>(); // A가 선택할 수 있는 충전기 정보를 담을 배열
			List<Charger> inB = new ArrayList<>(); // B가 선택할 수 있는 충전기 정보를 담을 배열
			for (int i = 0; i < numBC; i++) {
				Charger charger = chargers[i];
				Point addC = charger.point; // 충전기의 위치
				int c = charger.c; // 충전 가능한 범위
				// A, B의 좌표와 충전기 사이 거리 계산
				int distanceA = Math.abs(a.r - addC.r) + Math.abs(a.c - addC.c);
				int distanceB = Math.abs(b.r - addC.r) + Math.abs(b.c - addC.c);

				// 충전기 정보 배열에 저장
				if (distanceA <= c) {
					inA.add(charger);
				}
				if (distanceB <= c) {
					inB.add(charger);
				}
			}
			if (inA.size() != 0 && inB.size() != 0) {
				int sum = 0;
				for (int i = 0; i < inA.size(); i++) {
					for (int k = 0; k < inB.size(); k++) {
						if (inA.get(i) == inB.get(k)) {// 같은 충전기면 겹치는 충전기 더하기
							sum = Math.max(sum, inA.get(i).p);
						} else { // 다른 충전기면 각각 더하기
							sum = Math.max(sum, inA.get(i).p + inB.get(k).p);
						}
					}
				}
				Ans += sum;
			} else if (inA.size() != 0) { // 충전기를 A만 사용할 경우
				int sum = 0;
				for (int i = 0; i < inA.size(); i++) {
					sum = Math.max(sum, inA.get(i).p);
				}
				Ans += sum;
			} else if (inB.size() != 0) { // 충전기를 B만 사용할 경우
				int sum = 0;
				for (int i = 0; i < inB.size(); i++) {
					sum = Math.max(sum, inB.get(i).p);
				}
				Ans += sum;
			}
		}
	}
}
