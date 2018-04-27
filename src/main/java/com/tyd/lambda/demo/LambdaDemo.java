package com.tyd.lambda.demo;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.primitives.Ints.asList;

/**
 * Created by tangyongdong on 2018/4/23
 */
public class LambdaDemo {

    public static void main(String[] args) {

        User userA = new User("zhangsan", 24, "shanghai");
        User userB = new User("lisi", 22, "shanghai");
        User userC = new User("wangwu", 21, "beijing");
        User userD = new User("zhaoliu", 23, "beijing");
        List<User> userList = Lists.newArrayList(userA, userB, userC, userD);

        long count1 = userList.stream().filter(l -> l.getCity().equals("shanghai")).count();
        System.out.println(count1);

        System.out.println("=================惰性求职=====================");
        //惰性求职
        userList.stream().filter(l -> {
            System.out.println(l.getName());
            return l.getCity().equals("shanghai");
        });

        long count2 = userList.stream().filter(l -> {
            System.out.println(l.toString());
            return l.getCity().equals("shanghai");
        }).count();
        System.out.println(count2);

        //collect(toList()): 方法由 Stream 里的值生成一个列表，是一个及早求值操作。
        System.out.println("=================collect(toList())====================");
        List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());
        collected.stream().forEach(System.out::println);


        //map: 如果有一个函数可以将一种类型的值转换成另外一种类型，map 操作就可以 使用该函数，将一个流中的值转换成一个新的流。
        System.out.println("=================map====================");
        List<String> collect1 = collected.stream().map(l -> l.toUpperCase()).collect(Collectors.toList());
        collect1.stream().forEach(System.out::println);

        //filter: 遍历数据并检查其中的元素
        System.out.println("=================filter====================");
        List<User> userList1 = userList.stream().filter(l -> l.getCity().equals("beijing")).collect(Collectors.toList());
        userList1.stream().forEach(l -> {
            System.out.println(l.toString());
        });

        //flatMap: 将多个 Stream 连接成一个 Stream
        System.out.println("=================flatMap====================");
        List<Integer> ints = Stream.of(asList(1, 2), asList(3, 4)).flatMap(number -> number.stream()).collect(Collectors.toList());
        ints.stream().forEach(System.out::println);

        //max和min
        System.out.println("=================max and min====================");
        User user1 = userList.stream().max(Comparator.comparing(l -> l.getAge())).get();
        System.out.println("max:" + user1.toString());
        User user2 = userList.stream().min(Comparator.comparing(l -> l.getAge())).get();
        System.out.println("min:" + user2.toString());

        //reduce
        System.out.println("=================reduce====================");
        int count3 = Stream.of(1, 2, 3, 4).reduce(0, (acc, l) -> acc + l);
        System.out.println(count3);

        System.out.println("=========================================");
        userList.stream().filter(l -> l.getCity().startsWith("shang"))
                .sorted(Comparator.comparing(User::getAge))
                .map(User::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //并行化
        userList.parallelStream().forEach(l->{
            System.out.println(l.toString());
        });
    }
}
