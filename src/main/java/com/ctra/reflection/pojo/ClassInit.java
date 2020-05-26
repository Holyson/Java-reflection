package com.ctra.reflection.pojo;

// ClassLoader 类初始化
public class ClassInit {
    public static void main(String[] args) {

        Test test = new Test();
//        System.out.println(test.age);
    }
}

class Test{

    static{
        System.out.println("静态代码块");
        age=100;
    }

    Test(){
        System.out.println("无参构造方法");
    }
    static int age =26;

}
