package com.tyd.lambda.demo;

/**
 * Created by tangyongdong on 2018/4/23
 */
public class User {

    private String name;
    private Integer age;
    private String city;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }

    public User(String name, Integer age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public static int compareByAge(User user1, User user2) {
        return user1.age.compareTo(user2.age);
    }
}
