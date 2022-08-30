package com.day0827;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SW_No1_FishingSpot {

	static int N, min, fisherCnt, Ans;
	static int[] spots, persons;
	static int[] map;
	static boolean[] selected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			spots = new int[4];
			persons = new int[4];
			selected = new boolean[4];
			map = new int[N + 1];
			for (int i = 1; i <= 3; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				spots[i] = Integer.parseInt(st.nextToken());
				persons[i] = Integer.parseInt(st.nextToken());
			}
			// 낚시터 순서 뽑으러 가기
			min = Integer.MAX_VALUE;
			perm(0, 0);
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

	private static void perm(int cnt, int sum) {
		if (sum >= min)
			return;
		if (cnt == 3) {
			min = Math.min(min, sum);
			return;
		}
		for (int i = 1; i <= 3; i++) {
			if (selected[i])
				continue;
			// 1. 게이트 선택
			selected[i] = true;
			// 2. 낚시꾼 배치
			perm(cnt + 1, sum + inFishers(i, persons[i], true));
			outFishers(i);
			if (persons[i] % 2 == 0) {
				perm(cnt + 1, sum + inFishers(i, persons[i], false));
				outFishers(i);
			}
			selected[i] = false;
		}
	}

	// 배치했던 낚시꾼을 원래 상태로 되돌림
	private static void outFishers(int idx) {
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (map[i] == idx) {
				map[i] = 0;
				cnt++;
			}
			if (cnt == persons[idx])
				return;
		}

	}

	// 낚시꾼 배치 함수, flag=true 왼쪽->오른쪽 순서로 배치 ,flag=false 오른쪽->왼쪽 순서로 배치
	private static int inFishers(int idx, int person, boolean flag) {
		int distance = 0;
		fisherCnt = 0;
		int sum = 0;
		while (fisherCnt < person) {
			sum += flag ? left(idx, distance) : right(idx, distance);

			if (fisherCnt == person)
				break;

			sum += flag ? right(idx, distance) : left(idx, distance);
			distance++;
		}
		return sum;
	}

	// 왼쪽 배치
	private static int left(int idx, int distance) {
		int left = spots[idx] - distance;

		if (left >= 0 && map[left] == 0) {
			map[left] = idx;
			fisherCnt++;
			return distance + 1;
		}
		return 0;
	}

	// 오른쪽 배치
	private static int right(int idx, int distance) {
		int right = spots[idx] + distance;

		if (right <= N && map[right] == 0) {
			map[right] = idx;
			fisherCnt++;
			return distance + 1;
		}
		return 0;
	}

}