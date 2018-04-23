package com.tyd.lambda.demo;

import java.util.function.*;

/**
 * @author tangyongdong
 * @create 2018-04-23 14:04
 */
public class FunctionDemo {

    public static void main(String[] args) {

        // Function<T, R> -T作为输入，返回的R作为输出
        Function<String, String> function = x -> {
            System.out.print(x + " : ");
            return "Function";
        };
        System.out.println(function.apply("hello"));

        //Predicate<T> -T作为输入，返回的boolean值作为输出
        Predicate<String> predicate = x -> {
            System.out.print(x + " : ");
            return Boolean.FALSE;
        };
        System.out.println(predicate.test("hello"));

        //Consumer<T> - T作为输入，执行某种动作但没有返回值
        Consumer<String> consumer = x -> {
            System.out.println(x);
        };
        consumer.accept("hello");

        //Supplier<T> - 没有任何输入，返回T
        Supplier<String> supplier = () -> {
            return "Supplier";
        };
        System.out.println(supplier.get());

        //BinaryOperator<T> -两个T作为输入，返回一个T作为输出，对于“reduce”操作很有用
        BinaryOperator<String> binaryOperator = (x, y) -> {
            System.out.print(x + " " + y + " : ");
            return "BinaryOperator";
        };
        System.out.println(" " + binaryOperator.apply("hello", "world"));
    }
}
