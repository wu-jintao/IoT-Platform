package com.iotplatform.backend.utils;


import java.util.ArrayList;
import java.util.List;


public class CollectionUtil {
    /*判断List集合是否为空*/
    public static Boolean ListIsEmpty(List list){
        if( null!=list && list.size()>0){
            return false;
        }
        return true;
    }
}
