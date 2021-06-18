package com.zpj.lambdatest;

import java.util.Comparator;
import java.util.TreeSet;

public class TestLambda {
    public static void main(String[] args) {
         /*函数式接口 变量名 = （参数1，参数2...） ->{
              方法体
         };*/

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("run.....");
            }
        };
        new Thread(runnable).start();

        Runnable runnable1 = () -> System.out.println("hello,lambda");
        new Thread(runnable1).start();

        new Thread(()-> System.out.println("hello world")).start();
         // 匿名内部类的方式
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()+o2.length();
            }
        };
        TreeSet<String> treeSet = new TreeSet<>(comparator);
        // lambda方式
        Comparator<String> comparator1 = (o1, o2) -> o1.length()+o2.length();
        TreeSet<String> treeSet1 = new TreeSet<>(comparator1);
        // 进阶简化
        TreeSet<String> treeSet2 = new TreeSet<>((o1, o2) -> {
            return o1.length()+o2.length();
        });
    }
}
