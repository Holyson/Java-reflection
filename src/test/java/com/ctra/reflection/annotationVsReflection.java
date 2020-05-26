package com.ctra.reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

public class annotationVsReflection {
    public static void main(String[] args) throws NoSuchFieldException {
        Custom cs =  new Custom();
        Class c1 = cs.getClass();

        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation); //@com.ctra.reflection.wlClass(value=ctraClass) 找到了 外部 类的注解
        }

        // 获得注解的calue 的值
        wlClass wlClass = (wlClass) c1.getAnnotation(wlClass.class);
        String value = wlClass.value();
        System.out.println(value);

        // 获得类指定的注解
        Field name = c1.getField("name");
        wlField annotation = name.getAnnotation(wlField.class);
        System.out.println(annotation.column());
        System.out.println(annotation.sort());
        System.out.println(annotation.type());

    }
    


}


@wlClass("ctraClass")
class Custom {
    @wlField(column = "zhangsan", type = "tt", sort = 12)
    private String name;
    @wlField(column = "zhangsan", type = "tt", sort = 12)
    private int age;
    @wlField(column = "zhangsan", type = "tt", sort = 12)
    private String address;

    public Custom() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Custom(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }


}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface wlClass {
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface wlField {
    String column();

    String type();

    int sort();
}