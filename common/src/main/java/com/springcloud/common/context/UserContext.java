package com.springcloud.common.context;

public class UserContext {

    //真实项目中这个类型一般设置为用户实际类型例如:User
    private static ThreadLocal<String> userThreadLocal = new ThreadLocal<>();

    public static String getUser(){
        System.out.println("getUser==========="+ Thread.currentThread().getName());
        return userThreadLocal.get();
    }

    public static void setUser(String user){
        System.out.println("setUser==========="+ Thread.currentThread().getName());

        userThreadLocal.set(user);
    }
    //清空信息
    public static void remove() {
        userThreadLocal.remove();
    }
}
