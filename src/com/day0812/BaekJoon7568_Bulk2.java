package com.day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Person {
	int weight; // 무게
	int height; // 키
	int index; // 인덱스
	int rank; // 순위

	Person(int weight, int height) {
		this.weight = weight;
		this.height = height;
		this.rank = 0;
	}

	@Override
	public String toString() {
		return "Person [index=" + index + "]";
	}
}

public class BaekJoon7568_Bulk2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 사람들의 무게, 키를 담는 배열 초기화
		Person[] persons = new Person[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			persons[i] = new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			persons[i].index = i;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int rank = 1;
			for (int j = 0; j < n; j++) {
				if (i == j)
					continue;
				Person p1 = persons[i];
				Person p2 = persons[j];
				if (p1.weight < p2.weight && p1.height < p2.height) {
					rank++;
				}
			}
			sb.append(rank + " ");
		}
		System.out.println(sb);
	}
}
