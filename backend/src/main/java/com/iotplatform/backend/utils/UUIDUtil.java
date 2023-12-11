package com.iotplatform.backend.utils;
import java.util.UUID;


public class UUIDUtil {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString().replaceAll("-","");//用来生成数据库的主键id非常不错。。
        System.out.println(s);
    }
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString().replaceAll("-","");//用来生成数据库的主键id非常不错。。
        //System.out.println(s);
        return s;
    }

}
