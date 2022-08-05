package com.corona.virus;

import java.io.Serializable;

public class Virus implements Serializable,Comparable<Virus> {//***
	private String name;
	private int level;

	// public Virus() {}
	public Virus(String name, int level) {
		setName(name);
		setLevel(level);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append("\t").append(getLevel());
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return 1133715;
	}

	public boolean equals(Object obj) {
		if (this == obj) // 주소값이 같으면 같은 객체
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Virus))
			return false;
		// obj는 Virus타입
		Virus v = (Virus) obj;
		
		if (this.name == null) {
			if (v.getName() != null)
				return false;
		} else if (!this.name.equals(v.name)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Virus o) {
		return this.name.compareTo(o.getName());
	}
}
