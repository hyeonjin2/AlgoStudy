package com.day0805;
// Comparable 사용해서 기본적인 정렬기준으로 객체를  정렬시킴
// 클래스 내부에 정렬 기준(compareTo())을 만들어 놓고 사용함.
public class Writer implements Comparable<Writer> {
	private int no;
	private String firstName;
	private String lastName;
	private String bookTitle;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public Writer(int no, String firstName, String lastName, String bookTitle) {
		super();
		this.no = no;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bookTitle = bookTitle;
	}


	@Override
	public String toString() {
		return "Writer [no=" + no + ", firstName=" + firstName + ", lastName=" + lastName + ", bookTitle=" + bookTitle
				+ "]";
	}

	// 객체 정렬시 사용되는 정렬 기준을 가지고 있는 메소드
	@Override
	public int compareTo(Writer o) {
		// 양수, 0, 음수 : 음수나 0이면 객체의 자리가 그대로 유지가 되고 양수인 경우에는 자리가 바뀐다.
		int result = this.no - o.no;
		return result;
//		return o.no - this.no; // 내림차순
//		return this.firstName.compareTo(o.firstName); // 비교대상이 되는 객체가 파라메터로 들어간다.
	}

}
