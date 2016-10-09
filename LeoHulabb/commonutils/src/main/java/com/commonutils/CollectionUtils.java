package com.commonutils;

import java.util.Collection;

 /**
  * @desc:         集合操作工具类
  * @author:       Leo
  * @date:         2016/10/9
  */
public class CollectionUtils {

    /**
     * 判断集合是否为null或者0个元素
     * @param c     集合
     * @return   布尔值
     */
    public static boolean isNullOrEmpty(Collection c) {
        if (null == c || c.isEmpty()) {
            return true;
        }
        return false;
    }
}
