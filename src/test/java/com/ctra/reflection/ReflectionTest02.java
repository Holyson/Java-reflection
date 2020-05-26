package com.ctra.reflection;

import com.ctra.reflection.pojo.Person;
import com.ctra.reflection.pojo.Student;

public class ReflectionTest02 {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student();
        System.out.println(person.name);

        // 获取 Class 的方式一：
        Class c1 = person.getClass();
        // 获取 Class 的方式二：
        Class c2 = Student.class;
        // 获取 Class 的方式三：
        Class c3 = Class.forName("com.ctra.reflection.pojo.Student");

        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());

        // 获取 student 的父类对象
        Class c4 = c2.getSuperclass();
        System.out.println(c4.hashCode());

    }
}
