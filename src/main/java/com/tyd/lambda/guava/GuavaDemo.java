package com.tyd.lambda.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.base.*;
import com.google.common.collect.*;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


/**
 * @author tangyongdong
 * @create 2018-06-12 14:09
 */
public class GuavaDemo {
    public static void main(String[] args) {
        /**
         * 字符串连接器Joiner
         */
        //1.连接多个字符串并追加到StringBuilder
        StringBuilder stringBuilder = new StringBuilder("hello");
        Joiner joiner1 = Joiner.on("|").skipNulls();
        stringBuilder = joiner1.appendTo(stringBuilder, " tom", null, "jerry", "jack");
        System.out.println("===1: " + stringBuilder);

        //2.连接List元素
        List<Date> dateList = Lists.newArrayList(new Date(), null, new Date());
        String string1 = Joiner.on("#").useForNull("string").join(dateList);
        System.out.println("===2: " + string1);

        //3.将Map转化为字符串
        Map<String, String> maps = ImmutableMap.of("key1", "value1", "key2", "value2", "key3", "value3");
        String string2 = Joiner.on("#").withKeyValueSeparator(":").join(maps);
        System.out.println("===3: " + string2);

        /**
         * 字符串分割器Splitter
         */
        //1.将字符串分割为Iterable
        String str1 = "hello | world|your | name ";
        Iterable<String> iterable = Splitter.on("|").trimResults().split(str1);
        System.out.println("===4: " + JSON.toJSONString(iterable));

        //2.将字符串转化为Map
        String str2 = "key1:value1#key2:value2#key3:value3";
        Map<String, String> map1 = Splitter.on("#").withKeyValueSeparator(":").split(str2);
        System.out.println("===5: " + JSON.toJSONString(map1));

        /**
         * 字符串工具类Strings
         */
        System.out.print("===6: " + Strings.isNullOrEmpty(""));
        System.out.print("," + Strings.isNullOrEmpty(null));
        System.out.print("," + Strings.isNullOrEmpty(""));
        //将null转化为""
        System.out.print("," + Strings.nullToEmpty(null));
        //从尾部不断补充T只到总共8个字符，如果源字符串已经达到或操作，则原样返回。类似的有padStart
        System.out.println("," + Strings.padEnd("hello", 8, 'T'));

        /**
         * 字符匹配器CharMatcher
         */
        //1.空白一一替换
        String stringWithLineBreaks = "hello world\r\r\ryou are here\n\ntake it\t\t\teasy";
        String str3 = CharMatcher.BREAKING_WHITESPACE.replaceFrom(stringWithLineBreaks, "#");
        System.out.println("===7: " + str3);

        //2.连续空白缩成一个字符
        String tabString = "  hello   \n\t\tworld   you\r\nare       here  ";
        String str4 = CharMatcher.WHITESPACE.collapseFrom(tabString, '#');
        System.out.println("===8: " + str4);

        //3.去掉前后空白和缩成一个字符
        String str5 = CharMatcher.WHITESPACE.trimAndCollapseFrom(tabString, '#');
        System.out.println("===9: " + str5);

        //4.保留数字
        String letterAndNumber = "1234abcdABCD56789";
        String str6 = CharMatcher.JAVA_DIGIT.retainFrom(letterAndNumber);
        System.out.println("===10: " + str6);

        /**
         * 断言工具类Preconditions
         */
        /**String trimRet = null;
         * Preconditions.checkNotNull(trimRet,"label can not be null");
         * int data = 101;
         * Preconditions.checkArgument(data < 100, "data must be less than 100");
         */

        /**
         * 整体迭代接口FluentIterable
         */
        //1.使用Predicate整体过滤
        Person person1 = new Person("Wilma", 30, "F");
        Person person2 = new Person("Fred", 32, "M");
        Person person3 = new Person("Betty", 32, "F");
        Person person4 = new Person("Barney", 33, "M");
        List<Person> personList = Lists.newArrayList(person1, person2, person3, person4);

        Iterable<Person> personsFilteredByAge =
                FluentIterable.from(personList).filter(person -> person.getAge() > 31);
        System.out.print("===11: ");
        personsFilteredByAge.forEach(person -> System.out.print(JSON.toJSONString(person)));
        System.out.println("  " + Iterables.contains(personsFilteredByAge, person2));

        /**
         * 集合运算工具类Sets
         */
        //1.集合差
        Set<String> set1 = Sets.newHashSet("1", "2", "3", "4");
        Set<String> set2 = Sets.newHashSet("2", "3", "4", "5");
        Sets.SetView<String> res1 = Sets.difference(set1, set2);
        System.out.println("===12: " + JSON.toJSONString(res1));

        //2.集合对称差
        Sets.SetView<String> res2 = Sets.symmetricDifference(set1, set2);
        System.out.println("===13: " + JSON.toJSONString(res2));

        //3.集合交
        Sets.SetView<String> res3 = Sets.intersection(set1, set2);
        System.out.println("===14: " + JSON.toJSONString(res3));

        //4.集合并
        Sets.SetView<String> res4 = Sets.union(set1, set2);
        System.out.println("===15: " + JSON.toJSONString(res4));

        /**
         * Function和Predicate
         */
        //1.利用Functions将Map转化为Function
        Map<String, Person> params = ImmutableMap.of(
                person1.getName(), person1,
                person2.getName(), person2,
                person3.getName(), person3,
                person4.getName(), person4
        );
        Function<String, Person> lookup = Functions.forMap(params);
        Person betty = lookup.apply("Betty");
        System.out.println("===16: " + JSON.toJSONString(betty));

        //2.Predicate单个判断
        Predicate<Person> agePredicate = person -> person.getAge() > 32;
        Predicate<Person> namePredicate = person -> person.getName().equals("Betty");
        System.out.print("===17: " + agePredicate.apply(person2));
        System.out.print("," + agePredicate.apply(person3));
        System.out.print("," + namePredicate.apply(person2));
        System.out.println("," + namePredicate.apply(person3));

        //3.Predicates的and运算
        Predicate<Person> andPredicate = Predicates.and(agePredicate, namePredicate);
        System.out.print("===18: " + andPredicate.apply(person1));
        System.out.println("," + andPredicate.apply(person3));

        //4.Predicates的or运算
        Predicate<Person> orPredicate = Predicates.or(agePredicate, namePredicate);
        System.out.print("===19: " + orPredicate.apply(person1));
        System.out.println("," + orPredicate.apply(person3));

        //5.Predicates的compose运算
        Predicate<String> compose = Predicates.compose(agePredicate, lookup);
        System.out.print("===20: " + compose.apply("Betty"));
        System.out.println("," + compose.apply("Barney"));

        /**
         * Map工具类Maps
         */
        ImmutableMap<String, Person> stringPersonImmutableMap = Maps.uniqueIndex(personList.iterator(), person -> person.getName());
        System.out.println("===21: " + JSON.toJSONString(stringPersonImmutableMap));

        /**
         * 一键多值类Multimap
         */
        //1.数组存储多值类 ArrayListMultimap
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("name","tom");
        multimap.put("name","jerry");
        multimap.put("name","jack");
        multimap.put("age","11");
        multimap.put("age","11");
        multimap.put("age","12");
        System.out.print("===22: " + multimap.size());
        for(String key : multimap.keySet()){
            System.out.print("," + key + ":" + multimap.get(key));
        }
        System.out.println();

        //HashTable存储多值类 HashMultimap
        HashMultimap<String, String> hashMultimap = HashMultimap.create();
        hashMultimap.put("name","tom");
        hashMultimap.put("name","jerry");
        hashMultimap.put("name","jack");
        hashMultimap.put("age","11");
        hashMultimap.put("age","11");
        hashMultimap.put("age","12");
        System.out.print("===23: " + hashMultimap.size());
        for(String key : hashMultimap.keySet()){
            System.out.print("," + key + ":" + hashMultimap.get(key));
        }
        System.out.println();

        /**
         * 多键类Table
         */
        //两个键操作
        HashBasedTable<Integer, Integer, String> table = HashBasedTable.create();
        table.put(1, 1, "apple");
        table.put(1, 2, "orange");
        table.put(2, 2, "banana");
        System.out.print("===24: " + table.get(1, 1));
        System.out.print("," + table.contains(2, 3));
        System.out.print("," + table.containsRow(2));
        table.remove(2, 2);
        System.out.println("," + table.get(2, 2));

        //获取一个Map
        Map<Integer, String> row = table.row(1);
        Map<Integer, String> column = table.column(2);
        System.out.print("===25: " + JSON.toJSONString(row));
        System.out.println(", " + JSON.toJSONString(column));

        /**
         * 可以通过value获取key的 HashBiMap
         */
        //value不可以有相同的key
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("hello", "apple");
        biMap.put("hi", "orange");
        biMap.put("hi", "banana");
        //biMap.put("abc", "apple");  java.lang.IllegalArgumentException: value already present: apple
        biMap.forcePut("abc", "apple");
        System.out.print("===26: " + JSON.toJSONString(biMap));
        System.out.print("," + biMap.size());
        System.out.print("," + biMap.get("hello"));
        System.out.print("," + biMap.get("hi"));
        System.out.println("," + biMap.get("abc"));

        //键值对互换得到新的BiMap
        BiMap<String, String> inverseMap = biMap.inverse();
        System.out.println("===27: " + JSON.toJSONString(inverseMap));

        /**
         * 不可变集合类 ImmutableListMultimap
         */
        Multimap<Integer, String> map = new ImmutableListMultimap.Builder<Integer, String>()
                .put(1, "hello")
                .putAll(2, "hi", "world")
                .putAll(3, "get", "post", "delete")
                .build();
        System.out.print("===28: " + map.size());
        for(Integer key : map.keySet()){
            System.out.print("," + key + ":" + map.get(key));
        }
        System.out.println();

        /**
         * 区间工具类Range
         */
        //闭区间
        Range<Integer> closedRange = Range.closed(30, 33);
        //开区间
        Range<Integer> openRange = Range.open(30, 33);
        System.out.print("===29: " + closedRange.contains(30));
        System.out.print("," + closedRange.contains(33));
        System.out.print("," + openRange.contains(30));
        System.out.print("," + openRange.contains(33));

        Function<Person, Integer> ageFunction = person -> person.getAge();
        Predicate<Person> agePre = Predicates.compose(closedRange, ageFunction);
        System.out.println("," + agePre.apply(person1));

        /**
         * 比较器工具类 Ordering
         */
        //逆置比较器
        //Comparator<Person> ageCmp = (o1, o2) -> Ints.compare(o1.getAge(), o2.getAge());
        //Collections.sort(personList,Ordering.from(ageCmp).reversed());
        //System.out.println("===30: " + JSON.toJSONString(personList));
        //注:更简单的比较排序
        Collections.sort(personList, Comparator.comparing(Person::getAge).reversed());
        System.out.println("===30: " + JSON.toJSONString(personList));

        //组合多个比较器
        //Comparator<Person> nameCmp = (o1, o2) -> o1.getName().compareTo(o2.getName());
        //Collections.sort(personList, Ordering.from(ageCmp).compound(nameCmp);
        //System.out.println("===31: " + JSON.toJSONString(personList));
        //注:更简单的比较排序
        Collections.sort(personList, Comparator.comparing(Person::getAge).thenComparing(Person::getName));
        System.out.println("===31: " + JSON.toJSONString(personList));

        //直接获取最小几个和最大几个
        Comparator<Person> nameCmp = (o1, o2) -> o1.getName().compareTo(o2.getName());
        Ordering order = Ordering.from(nameCmp);
        List<Person> least = order.leastOf(personList, 2);
        List<Person> great = order.greatestOf(personList, 3);
        System.out.print("===32: " + JSON.toJSONString(least));
        System.out.println(", " + JSON.toJSONString(great));

    }

    static class Person{
        private String name;
        private String sex;
        private int age;

        public Person(String name, int age, String sex) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}






























