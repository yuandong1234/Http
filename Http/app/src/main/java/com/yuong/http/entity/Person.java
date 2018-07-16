package com.yuong.http.entity;

/**
 * Created by yuandong on 2018/7/13.
 */

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

    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userSex=" + userSex +
                '}';
    }
}
