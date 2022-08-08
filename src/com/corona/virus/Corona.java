package com.corona.virus;

public class Corona extends Virus {
	private int spreadSpeed;

	public Corona(String name, int level, int spreadSpeed) {
		super(name, level);
//		setName(name);
//		setLevel(level);
		setSpreadSpeed(spreadSpeed);
	}

	public int getSpreadSpeed() {
		return spreadSpeed;
	}

	public void setSpreadSpeed(int spreadSpeed) {
		this.spreadSpeed = spreadSpeed;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append("\t").append(getSpreadSpeed());
		return sb.toString();
	}
}