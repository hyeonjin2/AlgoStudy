package com.day0805;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProductTest {

	public static void main(String[] args) {
		// 객체 생성
		ArrayList<Product> list = new ArrayList<Product>();
		list.add(new Product(100, "마이쮸", 1000));
		list.add(new Product(150, "자유시간", 1500));
		list.add(new Product(113, "포테토칩", 1800));
		list.add(new Product(250, "자가비", 2500));
		list.add(new Product(332, "불닭볶음면", 1800));

		// 정렬(기본, comparator 이용 정렬
		Collections.sort(list);
		System.out.println("--------------------제품 번호 순--------------------");
		for (Product product : list) {
			System.out.println(product);
		}

		// 정렬(가격 오름차순 정렬 - lmabda)
		Collections.sort(list, (e1, e2) -> {
			if (e1.getPrice() == e2.getPrice()) {
				return e1.getName().compareTo(e2.getName());
			}
			return e1.getPrice() - e2.getPrice();
		});
		System.out.println("--------------------가격 순1--------------------");
		for (Product product : list) {
			System.out.println(product);
		}
		
		// 정렬(가격 오름차순 정렬 - 무명 클래스) 
		Collections.sort(list, new Comparator<Product>() {

			@Override
			public int compare(Product o1, Product o2) {
				if (o1.getPrice() == o2.getPrice()) {
					return o1.getName().compareTo(o2.getName());
				}
				return o1.getPrice() - o2.getPrice();
			}

		});
		System.out.println("--------------------가격 순2--------------------");
		list.forEach(e1 -> System.out.println(e1));

	}

}
