package com.ctra.reflection;

import com.ctra.reflection.pojo.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ReflectionTest08 {
    // 普通方式
    public static void test1(){
        User user = new User();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            user.getName();
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start)+"ms");
    }
    // 反射方式调用
    public static void test2() throws  NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        User user = new User();
        Class  c1 = user.getClass();
        Method getName = c1.getDeclaredMethod("getName",null);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user, null);
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start)+"ms");
    }

    // 反射方式调用 并且关闭监测
    public static void test3() throws  NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        User user = new User();
        Class  c1 = user.getClass();
        Method getName = c1.getDeclaredMethod("getName",null);
        getName.setAccessible(true);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {

            getName.invoke(user, null);
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start)+"ms");
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        test1();
        test2();
        test3(); // 理论上关闭监测 快于 反射方式
    }
}
