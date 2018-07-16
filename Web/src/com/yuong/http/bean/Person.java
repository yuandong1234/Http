package com.yuong.http.bean;

public class Person {
	public String userName;
	public int userAge;
	public int userSex;
	public Person() {
		super();
	}
	public Person(String userName, int userAge, int userSex) {
		super();
		this.userName = userName;
		this.userAge = userAge;
		this.userSex = userSex;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public int getUserSex() {
		return userSex;
	}
	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}
	

}
