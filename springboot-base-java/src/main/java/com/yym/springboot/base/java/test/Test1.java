package com.yym.springboot.base.java.test;

public class Test1 {
    public static void main(String[] args) {
        String name = "zhangsan";
        String name2 = name;
        name = "Lisi";
        System.out.println("name = " + name);
        System.out.println("name2 = " + name2);
        Dog dog = new Dog();
        System.out.println("row - dog = " + dog);
        System.out.println("------changeDog Begin-------------");
        // name = newTom  age = 4
        changeDog(dog);
        System.out.println("dog = " + dog);

        Dog dog2 = new Dog();
        System.out.println("row - dog2 = " + dog2);
        System.out.println("------changeDog2 Begin-------------");
        // name = newTom2  age = 8
        changeDog2(dog2);
        System.out.println("dog2 = " + dog2);

    }

    private static void changeDog(Dog dog) {
        dog.setName("newTom");
        dog.setAge(4);
        System.out.println("new - dog = " + dog);
    }

    private static void changeDog2(Dog dog) {
        dog = new Dog("newTome2", 8);
        System.out.println("new - dog2 = " + dog);
    }
}


class Dog{
    private String name = "tom";
    private Integer age = 2;

    public Dog() {}

    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
