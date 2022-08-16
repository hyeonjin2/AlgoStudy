package com.day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JungOl1828_Refrigerator {

	static int Ans;

	static class Matter implements Comparable<Matter> {
		int min, max;

		public Matter(int min, int max) {
			this.min = min;
			this.max = max;
		}

		@Override
		public int compareTo(Matter o) {
			// 최소온도 기준 오름차순 정렬 만약 같다면 최고온도 기준 오름차순 정렬
			return this.min != o.min ? this.min - o.min : this.max - o.max;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Matter[] matters = new Matter[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			matters[i] = new Matter(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		getResult(matters);
		System.out.println(Ans + 1);
	}

	private static void getResult(Matter[] matters) {
		// 정렬
		Arrays.sort(matters);
		// 처음 값의 최소온도, 최고온도
		int min = matters[0].min;
		int max = matters[0].max;
		for (int i = 1; i < matters.length; i++) {
			Matter m = matters[i];
			// 현재 저장되어있는 최고온도와 다음 물질의 최소 온도 비교
			// 최고온도보다 최소온도가 낮다면 보관 가능
			if (max >= m.min) {
				min = Math.max(min, m.min);
				max = Math.min(max, m.max);
			} else {
				Ans++;
				min = m.min;
				max = m.max;
			}
		}
	}
}
