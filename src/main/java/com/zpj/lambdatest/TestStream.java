package com.zpj.lambdatest;

import java.util.ArrayList;
import java.util.List;

public class TestStream {
    public static void main(String[] args) {

        // 传统模式
        List<String> list = new ArrayList<>();
        list.add("aasd");
        list.add("fdfsfsdfsf");
        list.add("sda");
        int count = 0;
        for (String s : list) {
            if(s.length()>3){
                count++;
            }
        }
        System.out.println(count);

        // stream流
        // 1、创建流stream()
        // 2、中间操作filter()
        // 3、终止操作 产生一个结果后，就终止了count()
        long count1 = list.stream().filter((s -> s.length() > 3)).count();
        System.out.println(count1);

    }
}
