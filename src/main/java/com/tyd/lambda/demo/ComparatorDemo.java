package com.tyd.lambda.demo;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author tangyongdong
 * @create 2018-04-23 11:22
 */
public class ComparatorDemo {

    public static void main(String[] args) {
        User userA = new User("zhangsan",24,"shanghai");
        User userB = new User("lisi",22,"shanghai");
        User userC = new User("wangwu",21,"beijing");
        User userD = new User("zhaoliu",23,"beijing");
        List<User> userList = Lists.newArrayList(userA,userB,userC,userD);

        //1.使用lamda表达式或方法引用进行排序
        userList.sort((l1,l2)->l1.getAge().compareTo(l2.getAge()));
        userList.stream().forEach(l->{
            System.out.println(l.toString());
        });
        System.out.println("====================================");

        //2.lamda表达式也可以使用静态方法的引用代替
        userList.sort(User::compareByAge);
        userList.stream().forEach(l->{
            System.out.println(l.toString());
        });
        System.out.println("====================================");

        //3.增强版的Comparator接口
        Collections.sort(userList, Comparator.comparing(User::getAge));
        userList.stream().forEach(l->{
            System.out.println(l.toString());
        });
        System.out.println("====================================");

        Collections.sort(userList, Comparator.comparing(User::getAge).reversed());
        userList.stream().forEach(l->{
            System.out.println(l.toString());
        });
        System.out.println("====================================");

        //4.多条件排序
        userList.sort(Comparator.comparing(User::getCity).thenComparing(User::getAge));
        userList.stream().forEach(l->{
            System.out.println(l.toString());
        });
    }
}
