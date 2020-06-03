package com.yym.springboot.base.java.lamdba;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestLambda {
    List<Person> persons = Arrays.asList(
            new Person("Max", 18),
            new Person("Peter", 23),
            new Person("Pamela", 23),
            new Person("David", 13));

    @Test
    public void test1(){
        List<Integer> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        list.add(5);
        list.add(3);
        System.out.println("list = " + list);
        List<Integer> collect = list.stream().filter(in -> in != null).sorted((a, b) -> a - b).collect(Collectors.toList());

        List<Integer> collect1 = list.stream().sorted((a,b)->{
            if(a == null || b == null){
                return 0;
            }
            return a-b;
        }).collect(Collectors.toList());

        System.out.println("collect+null = " + collect1);
        System.out.println("collect = " + collect);
    }

    // 平均年龄
    @Test
    public void test2(){
        // 只是获取平均数
        Double collect = persons.stream().collect(Collectors.averagingDouble(Person::getAge));
        System.out.println("collect = " + collect);
        // 获取数据的集合,个数,总和,平均数,最小数,最大数
        DoubleSummaryStatistics collect1 = persons.stream().collect(Collectors.summarizingDouble(Person::getAge));
        System.out.println("collect1 = " + collect1);
        String collect2 = persons.stream().filter(p -> p.getAge() > 18).map(Person::getName).collect(Collectors.joining(",","hello","world"));
        System.out.println("collect2 = " + collect2);
        // 如果一个key对应多个值,那么第三个参数可以自定义拼接方式
        Map<Integer, String> collect3 = persons.stream().collect(Collectors.toMap(Person::getAge, Person::getName, (s1, s2) -> s1 +"--"+ s2));
        System.out.println("collect3 = " + collect3);
    }
}


@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
class Person{
    private String name;
    private Integer age;
}