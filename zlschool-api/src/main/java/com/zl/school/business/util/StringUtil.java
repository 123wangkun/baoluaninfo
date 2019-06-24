package com.zl.school.business.util;

public class StringUtil {
    /**
     * 是否是空字符串，空值
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.equals("") || str.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }
    /**
     * 判断非空字符串
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtil.isEmpty(str);
    }
}
