package com.day0819;

import java.awt.Point;
import java.util.HashSet;

public class Test {

	public static void main(String[] args) {
		HashSet<Point> set = new HashSet<>();
		set.add(new Point(1,1));
		set.add(new Point(1,1));
		
		System.out.println(set);
	}

}
