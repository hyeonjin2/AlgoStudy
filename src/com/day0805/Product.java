package com.day0805;

// 정렬 기준 : Comparable. 제품번호 기준
public class Product implements Comparable<Product> {
	private int num;
	private String name;
	private int price;

	// constructor

	public Product(int num, String name, int price) {
		super();
		this.num = num;
		this.name = name;
		this.price = price;
	}

	// getter/setter

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// toString()

	@Override
	public String toString() {
		return "Product [num=" + num + ", name=" + name + ", price=" + price + "]";
	}

	@Override
	public int compareTo(Product o) {
		return this.num - o.num;
	}
}
