package com.ctra.reflection;

import com.ctra.reflection.pojo.Base;
import com.ctra.reflection.pojo.Father;
import com.ctra.reflection.pojo.Son;

// 测试类的初始化
public class ReflectionTest05 {
    static {
        System.out.println("测试main方法启动");
    }
    public static void main(String[] args) throws ClassNotFoundException {
        // 1. 主动引用
        // Father fa =new Son();

        // 2.反射引用
        // Class.forName("com.ctra.reflection.pojo.Son");

        // 3.被动引用一：通过子类访问父类静态域（变量、方法）
        // System.out.println(Son.b);

        // 4.被动引用二：通过数组定义类引用
        // Son[] sons = new Son[10];

        // 5. 被动引用三：引用常量final 不会初始化
        System.out.println(Son.M);

    }


}
