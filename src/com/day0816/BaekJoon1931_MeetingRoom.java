package com.day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1931_MeetingRoom {

	static int Ans;

	static class Meeting implements Comparable<Meeting> {
		int start, end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			// 회의 종료시간 기준 오름차순 정렬, 종료시간이 같다면 시작시간 기준 오름차순 정렬
			return this.end != o.end ? this.end - o.end : this.start - o.start;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// meeting 정보를 담을 배열 초기화
		Meeting[] meetings = new Meeting[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		getResult(meetings);
		System.out.println(Ans);
	}

	private static void getResult(Meeting[] meetings) {
		// 정렬
		Arrays.sort(meetings);
		int end = meetings[0].end;
		Ans = 1;
		for (int i = 1, size = meetings.length; i < size; i++) {
			Meeting m = meetings[i];
			if (end <= m.start) {
				Ans++;
				end = m.end;
			}
		}
	}
}
