package com.ctra.reflection;



import javax.xml.bind.SchemaOutputResolver;

public class ReflectionTest04 {
    public static void main(String[] args) {
        //  只要元素类型与围堵一样，就是同一个Class
        int[] a = new int[10];
        int[] b = new int[200];
        System.out.println(a.getClass().hashCode());
        System.out.println(b.getClass().hashCode());

    }
}
