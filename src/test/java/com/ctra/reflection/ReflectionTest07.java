package com.ctra.reflection;

import com.ctra.reflection.pojo.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 利用class类操作类的属性和方法
public class ReflectionTest07 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        // 获得 Class 对象
        Class c1 = Class.forName("com.ctra.reflection.pojo.User");

        // 构造一个对象 （本质是调用了一个无参构造函数《在 jdk9.0》中更加明确）
        System.out.println("===========构造一个对象==========");
        Object obj1 = c1.newInstance();// jdk 8.0
        Object obj2 = c1.getDeclaredConstructor().newInstance(); //jdk 9.0

        System.out.println(obj1);
        System.out.println(obj2);

        //通过构造器创建对象 (调用有残构造函数）
        System.out.println("===========通过构造器创建对象==========");
        Constructor constructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        User user = (User) constructor.newInstance("ctra",1989,1201);
        System.out.println(user);

        // 通过反射 获取一个 User 对象
        User user1 = (User) c1.getDeclaredConstructor().newInstance();
        // 通过反射调用普通方法
        System.out.println("===========通过反射调用普通方法==========");
        System.out.println("------------- 方式一: 对象可以直接调用方法 --------------------");
        user1.setName("ctra");
        System.out.println(user1.getName());
        System.out.println("------------- 方式二: getDeclaredMethod + invoke--------------------");
        Method setName = c1.getDeclaredMethod("setName", String.class);
        setName.invoke(user1,"wanglei"); // invoke：激活 （对象，“方法的值”）
        System.out.println(user1.getName());


        // 通过反射操作属性
        System.out.println("===========通过反射操作属性==========");
        User user2 = (User) c1.getDeclaredConstructor().newInstance();
        Field name = c1.getDeclaredField("name");
        name.setAccessible(true); // 取消安全监测
        name.set(user2,"ctra_88"); //IllegalAccessException 报权限错误
        System.out.println(user2.getName());

    }
}
