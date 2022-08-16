package com.day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//class Person {
//	int weight; // 무게
//	int height; // 키
//	int index; // 인덱스
//	int rank; // 순위
//
//	Person(int weight, int height) {
//		this.weight = weight;
//		this.height = height;
//		this.rank = 0;
//	}
//
//	@Override
//	public String toString() {
//		return "Person [index=" + index + "]";
//	}
//}

public class BaekJoon7568_Bulk {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Person[] persons = new Person[n];
		// 사람들의 몸무게, 키 정보를 담은 배열 초기화
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			persons[i] = new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			persons[i].index = i;
		}
		// 무게가 큰 순서대로 정렬
		Arrays.sort(persons, (p1, p2) -> {
			if (p1.weight == p2.weight) {
				return p2.height - p1.height;
			}
			return p2.weight - p1.weight;
		});
		// 키와 비교
		int count = 1;
		persons[0].rank = 1;
		for (int i = 0; i < n - 1; i++) {
			Person p1 = persons[i];
			Person p2 = persons[i + 1];
			if (p1.height < p2.height) { // p1과 p2를 비교할 수 없는 경우
				p2.rank = p1.rank;
				count++;
			} else if (p1.height > p2.height) {
				p2.rank = p1.rank + count;
				count = 1;
			} else if (p1.weight == p2.weight && p1.height == p2.height) {
				p2.rank = p1.rank;
				count++;
			}
		}
		Arrays.sort(persons, (p1, p2) -> {
			return p1.index - p2.index;
		});
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(persons[i].rank + " ");
		}
		System.out.println(sb);
	}
}
