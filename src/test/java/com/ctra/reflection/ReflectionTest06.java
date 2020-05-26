package com.ctra.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
// 通过反射获得 类的信息（属性、方法、构造器）
public class ReflectionTest06 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class c1 = Class.forName("com.ctra.reflection.pojo.User");

        // 获得类的名字
        System.out.println("===========获得类的名字==========");
        System.out.println(c1.getName()); // 报名 + 类名：com.ctra.reflection.pojo.User
        System.out.println(c1.getSimpleName()); // 类名：User

        // 获得类的属性
        System.out.println("============获得类的属性=========");
        Field[] fields = c1.getFields(); // 只能找到 public属性（Member.PUBLIC）

        fields = c1.getDeclaredFields(); // 找到全部属性 （Declared）
        for (Field field : fields) {
            System.out.println(field);
        }

        // 获得指定属性
        System.out.println("===========获得指定属性==========");
        //Field name = c1.getField("name"); // 私有无法访问 --- 报错
        Field name = c1.getDeclaredField("name");
        System.out.println(name);

        // 获得类的方法
        System.out.println("===========获得类的方法==========");
        Method[] methods = c1.getMethods(); // 只能找到 public 方法（本类+父类）
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("--------------------------------");
        methods = c1.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        // 获得指定方法
        System.out.println("===========获得指定方法==========");
        Method getName = c1.getMethod("getName");
        Method setName = c1.getMethod("setName", String.class);

        System.out.println(getName);
        System.out.println(setName);

        // 获得指定构造器
        System.out.println("===========获得类的构造器==========");
        Constructor[] constructors = c1.getConstructors(); // 获得本类的 public 构造方法
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        System.out.println("--------------------------------");
        constructors = c1.getDeclaredConstructors(); // 获得本来的全部构造方法
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        // 获得指定构造器
        System.out.println("===========获得类的指定构造器==========");
        Constructor declaredConstructor = c1.getDeclaredConstructor(String.class,int.class,int.class);
        System.out.println(declaredConstructor);

    }
}
