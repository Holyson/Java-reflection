package com.ctra.reflection.pojo;

public class Son extends Father {
    static {
        System.out.println("子类被加载");
        m = 300;
    }

    public static int m = 100;
    public static final int M = 1;
}
