package com.day1013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 링크와 스타트
public class BaekJoon15661 {

	static int N, Ans;
	static int[][] map;
	static boolean[] isSelected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		isSelected = new boolean[N];
		Ans = Integer.MAX_VALUE;
		makeTeam(0);
		System.out.println(Ans);
	}

	private static void makeTeam(int index) {
		if (index == N) {
			List<Integer> slist = new ArrayList();
			List<Integer> llist = new ArrayList();
			for (int i = 0; i < N; i++) {
				if (isSelected[i])
					slist.add(i);
				else
					llist.add(i);
			}
			if (slist.size() == 0 || llist.size() == 0)
				return;
			int score = calc(slist, llist);
			Ans = Math.min(Ans, score);
			return;
		}
		isSelected[index] = true;
		makeTeam(index + 1);
		isSelected[index] = false;
		makeTeam(index + 1);
	}

	private static int calc(List<Integer> s, List<Integer> l) {
		int sumS = 0;
		int sumL = 0;
		for (int i = 0; i < s.size(); i++) {
			for (int j = i + 1; j < s.size(); j++) {
				sumS += map[s.get(i)][s.get(j)];
				sumS += map[s.get(j)][s.get(i)];
			}
		}
		for (int i = 0; i < l.size(); i++) {
			for (int j = i + 1; j < l.size(); j++) {
				sumL += map[l.get(i)][l.get(j)];
				sumL += map[l.get(j)][l.get(i)];
			}
		}
		return Math.abs(sumL - sumS);
	}

}
