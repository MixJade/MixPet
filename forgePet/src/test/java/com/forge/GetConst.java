package com.forge;

import com.forge.common.PhotoConst;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 通过反射获取接口常量值
 */
public class GetConst {
    public static void main(String[] args) {
        GetConst getConst = new GetConst();
        List<String> defList = getConst.myConst();
        System.out.println(defList);
    }

    private List<String> myConst() {
        List<String> defList = new ArrayList<>();
        Field[] fields = (PhotoConst.class).getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true); // 忽略权限修饰符的安全检查
                defList.add((String) field.get(String.class));
            }
        } catch (IllegalAccessException e) {
            System.out.println("不安全的反射");
            throw new RuntimeException(e);
        }
        return defList;
    }
}
