package com.zpj.lambdatest;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StudentStream {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("zpj",20,1000));
        students.add(new Student("cqs",25,2000));
        students.add(new Student("asd",20,4000));
        students.add(new Student("fxs",30,3000));

        Stream<Student> studentStream = students.stream().filter(student -> student.getAge() > 23);
        studentStream.forEach(System.out::println);
        System.out.println("-----------------");

        Stream<Student> sorted = students.stream().sorted((o1, o2) -> {
            if (o1.getAge() == o2.getAge()) {
                return o1.getGongzi() - o2.getGongzi();
            } else {
                return o1.getAge() - o2.getAge();
            }
        });
        sorted.forEach(System.out::println);
    }
}
