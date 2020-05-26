package com.ctra.reflection;

// Class的存储位置：元空间（Metaspace）
public class ReflectionTest01 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class c1 = Class.forName("com.ctra.reflection.pojo.User");
        System.out.println(c1);

        Class c2 = Class.forName("com.ctra.reflection.pojo.User");
        Class c3 = Class.forName("com.ctra.reflection.pojo.User");

        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());
    }
}
