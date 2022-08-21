package com.day0806;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Toss5 {

	static int solution(int[] tasks) {
		int answer = 0;
		long[] isFinished = new long[tasks.length];
		Arrays.fill(isFinished, 0);
		for (int i = 0; i < tasks.length; i++) {
			if (isFinished[tasks[i]] == 0) {
				isFinished[tasks[i]] = 1;
				continue;
			}
			long tmp1 = isFinished[tasks[i]];
			isFinished[tasks[i]]++;
		}
		int max = -1;
		boolean possible = true;
		for (int i = 0; i < tasks.length; i++) {
			long tmp2 = isFinished[tasks[i]];
			if (tmp2 == 1) {
				possible = false;
				break;
			}
			if (isFinished[tasks[i]] != 0 && max < tmp2) {
				max = tasks[i];
			}
		}
		if (possible) {
			answer = max;
		} else {
			answer = -1;
		}
		return answer;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 2, 3, 3, 2, 2 };
		int[] a2 = { 4, 1, 1, 1, 1, 2, 3 };
		int[] a3 = { 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 5, 4, 6, 3, 5, 4, 6 };
		System.out.println(solution(a1));
		System.out.println(solution(a2));
		System.out.println(solution(a3));
	}

}
