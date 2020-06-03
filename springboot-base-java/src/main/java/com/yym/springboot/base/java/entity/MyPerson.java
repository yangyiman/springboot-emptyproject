package com.yym.springboot.base.java.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class MyPerson extends Person{
    private String gender;

    public MyPerson() {
        super();
        System.out.println("构造方法被调用了");
    }

    static {
        System.out.println("static代码");
    }

    {
        System.out.println("{}代码 ");
    }

    public static void main(String[] args) {
        MyPerson mp = new MyPerson();
        System.out.println(mp);
    }
}


@Data
@Accessors(chain = true)
class Person {
    private String name;
    private Integer age;
    private static Map<String,String> map;

    public Person() {
        System.out.println("person -- 构造方法被调用了");
    }

    static {
        System.out.println("person -- static代码");
    }

    {
        System.out.println("person -- {}代码 ");
    }

}