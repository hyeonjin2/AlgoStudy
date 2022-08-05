package com.day0805;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WriterTest {

	public static void main(String[] args) {
		ArrayList<Writer> list = new ArrayList<Writer>();
		list.add(new Writer(235, "jane", "kim", "who moved my cheese?"));
		list.add(new Writer(120, "harry", "porter", "will you marry me?"));
		list.add(new Writer(300, "billy", "jones", "my love"));
		list.add(new Writer(535, "bruce", "willis", "ant story"));
		list.add(new Writer(270, "bruce", "kim", "baseball history"));

//		Collections.sort(list, new WriterFirstNameComparator()); // firstName 기준으로 정렬
		
		/*// 무명 클래스 이용
		Collections.sort(list, new Comparator<Writer>() {

			@Override
			public int compare(Writer o1, Writer o2) {
				return o1.getBookTitle().compareTo(o2.getBookTitle());
			}
		});
		*/

		// lambda
		Collections.sort(list, (e1, e2) -> {
			return e1.getLastName().compareTo(e2.getLastName());
		});

//		Collections.sort(list); // no 기준으로 정렬

		for (Writer writer : list) {
			System.out.println(writer);
		}

	}

}
