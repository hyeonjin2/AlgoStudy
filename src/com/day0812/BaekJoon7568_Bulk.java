package com.day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Person {
	int weight; // 무게
	int height; // 키
	int index; // 인덱스

	Person(int weight, int height) {
		this.weight = weight;
		this.height = height;
	}

	@Override
	public String toString() {
		return "Person [index=" + index + "]";
	}
}

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
		Arrays.sort(persons, (p1, p2) -> {
			if (p1.weight > p2.weight && p1.height > p2.height) {
				// 키와 몸무게 모두 크면 p2-p1
				return p2.weight - p1.weight;
			} else {
				return 0;
			}
		});
		StringBuilder sb = new StringBuilder();
		System.out.println(Arrays.toString(persons));
	}
}
