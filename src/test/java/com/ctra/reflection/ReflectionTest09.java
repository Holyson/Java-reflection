package com.ctra.reflection;

import com.ctra.reflection.pojo.User;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

// 通过反射获取泛型
public class ReflectionTest09 {
    public void test01(Map<String,Object> map, List<User> list){
        System.out.println("test01");
    }

    public Map<String,Object> test02(){
        System.out.println("test02");
        return null;

    }

    public static void main(String[] args) throws NoSuchMethodException {

        // 获取泛型 参数 一
        Method method = ReflectionTest09.class.getMethod("test01", Map.class, List.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes();// 获取泛型
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println("#"+genericParameterType);
            // 判断 参数化类型
            if(genericParameterType instanceof ParameterizedType){
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments(); // 获得真实参数信息
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println(actualTypeArgument);
                }
            }
        }

        // 获取泛型 参数 二
        method = ReflectionTest09.class.getMethod("test01", Map.class, List.class);
        Type  getGenericReturnType  = method.getGenericReturnType();// 获取泛型
        if(getGenericReturnType instanceof ParameterizedType){
            Type[] actualTypeArguments = ((ParameterizedType) getGenericReturnType).getActualTypeArguments(); // 获得真实参数信息
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument);
            }
        }
    }
}
