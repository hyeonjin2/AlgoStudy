package com.day0806;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Toss6 {
	static String[] solution(int[] steps_one, String[] names_one, int[] steps_two, String[] names_two,
			int[] steps_three, String[] names_three) {
		String[] answer = {};
		int[][] steps = { steps_one, steps_two, steps_three };
		String[][] users = { names_one, names_two, names_three };
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < steps.length; i++) {
			for (int j = 0; j < steps[i].length; j++) {
				if (i == 0) {
					map.put(users[i][j], steps[i][j]);
					continue;
				}
				if (map.containsKey(users[i][j])) {
					int tmp = map.get(users[i][j]);
					map.put(users[i][j], tmp + steps[i][j]);
				} else {
					map.put(users[i][j], steps[i][j]);
				}
			}
		}
		List<String> keys = new ArrayList<>(map.keySet());
		Collections.sort(keys, (e1, e2) -> {
			if (map.get(e2) == map.get(e1)) {
				return e1.compareTo(e2);
			}
			return map.get(e2).compareTo(map.get(e1));
		});
		answer = new String[keys.size()];
		for (int i = 0; i < keys.size(); i++) {
			answer[i] = keys.get(i);
		}
		return answer;
	}

	public static void main(String[] args) {
		int[] steps_one = { 1, 2, 3 };
		String[] names_one = { "v", "e", "v" };

		int[] steps_two = { 9999 };
		String[] names_two = { "r" };

		int[] steps_three = { 1 };
		String[] names_three = { "c" };

		String[] result = solution(steps_one, names_one, steps_two, names_two, steps_three, names_three);
		System.out.println(Arrays.toString(result));

	}

}
